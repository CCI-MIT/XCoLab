package org.xcolab.view.pages.proposals.view.action;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalMemberRatingClient;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVote;
import org.xcolab.entity.utils.analytics.AnalyticsUtil;
import org.xcolab.entity.utils.email.notifications.proposal.ProposalVoteNotification;
import org.xcolab.entity.utils.email.notifications.proposal.ProposalVoteValidityConfirmation;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class VoteOnProposalActionController {

    private final ProposalsContext proposalsContext;

    private final static String VOTE_ANALYTICS_KEY = "VOTE_CONTEST_ENTRIES";
    private final static String VOTE_ANALYTICS_CATEGORY = "User";
    private final static String VOTE_ANALYTICS_ACTION = "Vote contest entry";
    private final static String VOTE_ANALYTICS_LABEL = "";

    @Autowired
    public VoteOnProposalActionController(ProposalsContext proposalsContext) {
        Assert.notNull(proposalsContext);
        this.proposalsContext = proposalsContext;
    }

    @GetMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/voteOnProposalAction")
    public void handleAction(HttpServletRequest request, Model model, HttpServletResponse response)
            throws ProposalsAuthorizationException, IOException {
        final Proposal proposal = proposalsContext.getProposal(request);
        final Contest contest = proposalsContext.getContest(request);
        final Member member = proposalsContext.getMember(request);
        final ClientHelper clients = proposalsContext.getClients(request);
        ProposalMemberRatingClient proposalMemberRatingClient = clients
                .getProposalMemberRatingClient();

        boolean hasVoted = false;
        if (proposalsContext.getPermissions(request).getCanVote()) {
            long proposalId = proposal.getProposalId();
            long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
            long memberId = member.getUserId();
            if (proposalMemberRatingClient.hasUserVoted(proposalId, contestPhaseId, memberId)) {
                // User has voted for this proposal and would like to retract the vote
                proposalMemberRatingClient.deleteProposalVote(contestPhaseId, memberId);
            } else {
                if (proposalMemberRatingClient.hasUserVoted(contestPhaseId, memberId)) {
                    // User has voted for a different proposal. Vote will be retracted and converted to a vote of this proposal.
                    proposalMemberRatingClient.deleteProposalVote(contestPhaseId, memberId);
                }

                proposalMemberRatingClient.addProposalVote(proposalId, contestPhaseId, memberId);
                String portalUrl = ConfigurationAttributeKey.COLAB_URL.get();
                final boolean voteIsValid = validateVote(member, proposal, contest,portalUrl, request);
                if (voteIsValid) {
                    try {
                        Contest contestMicro = ContestClientUtil.getContest(contest.getContestPK());
                        new ProposalVoteNotification(proposal, contestMicro, member, portalUrl).sendMessage();
                    } catch (ContestNotFoundException ignored) {

                    }
                    hasVoted = true;
                }

                //publish event per contestPhaseId to allow voting on exactly one proposal per contest(phase)
                AnalyticsUtil.publishEvent(request, memberId, VOTE_ANALYTICS_KEY + contestPhaseId,
                        VOTE_ANALYTICS_CATEGORY,
                        VOTE_ANALYTICS_ACTION,
                        VOTE_ANALYTICS_LABEL,
                        1);
            }
        } else {
            if (member == null) {
                /* User is not logged in - don't count vote and let user log in*/
                request.setAttribute("promptLoginWindow", "true");
                return;
            } else {
                throw new ProposalsAuthorizationException("User isn't allowed to vote on proposal ");
            }
        }
        // Redirect to prevent page-refreshing from influencing the vote
        final String arguments = hasVoted ? "/voted" : "";
        response.sendRedirect(proposal.getProposalLinkUrl(contest) + arguments);
    }

    private boolean validateVote(Member member, Proposal proposal, Contest contest,
            String baseUrl, HttpServletRequest request) {
        if (member.isVerifiedAccount() || isEmailWhitelisted(member.getEmailAddress())){
            return true;
        }

        final ClientHelper clients = proposalsContext.getClients(request);
        ProposalMemberRatingClient proposalMemberRatingClient = clients
                .getProposalMemberRatingClient();

        List<Member> usersWithSharedIP = MembersClient.findMembersByIp(request.getRemoteAddr());
        usersWithSharedIP.remove(member);
        if (!usersWithSharedIP.isEmpty()) {
            final ProposalVote vote = proposalMemberRatingClient
                    .getProposalVoteByProposalIdUserId(proposal.getProposalId(), member.getUserId());
            if (vote == null) {
                throw new InternalException("Could not retrieve vote");
            }
            int recentVotesFromSharedIP = 0;
            for (Member otherUser : usersWithSharedIP) {
                    final ProposalVote otherVote = proposalMemberRatingClient
                            .getProposalVoteByProposalIdUserId(proposal.getProposalId(), otherUser.getUserId());
                    //check if vote is less than 12 hours old
                if(otherVote!=null&&otherUser.getId_() != member.getId_() ) {
                    if (new DateTime(otherVote.getCreateDate()).plusHours(12).isAfterNow()) {
                        recentVotesFromSharedIP++;
                    }
                    if (StringUtils.getLevenshteinDistance(member.getFirstName(), otherUser.getFirstName()) < 3
                            && StringUtils.getLevenshteinDistance(member.getLastName(), otherUser.getLastName()) < 3) {
                        vote.setIsValid(false);
                        break;
                    }
                }

            }
            if (vote.getIsValid() && recentVotesFromSharedIP > 7) {
                vote.setIsValid(false);
                sendConfirmationMail(vote, proposal, contest, member, baseUrl);
            }
            proposalMemberRatingClient.updateProposalVote(vote);
            return vote.getIsValid();
        }
        return true;
    }

    private boolean isEmailWhitelisted(String email) {
        final List<Pattern> emailWhitelist =
                ConfigurationAttributeKey.VOTING_EMAIL_VERIFICATION_WHITELIST.get();
        return emailWhitelist.stream()
                .anyMatch(pattern -> pattern.matcher(email).find());
    }

    private void sendConfirmationMail(ProposalVote vote, Proposal proposal, Contest contest, Member member, String baseUrl) {
        String confirmationToken = UUID.randomUUID().toString();
        vote.setConfirmationToken(confirmationToken);
        vote.setConfirmationEmailSendDate(new Timestamp(new Date().getTime()));
            new ProposalVoteValidityConfirmation(proposal, contest, member, baseUrl,
                    confirmationToken).sendEmailNotification();
    }

    @GetMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/confirmVote/{proposalId}/{userId}/{confirmationToken}")
    public String confirmVote(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable long proposalId, @PathVariable long userId,
            @PathVariable String confirmationToken) {
        boolean success = false;
        try {
            ProposalVote vote = ProposalMemberRatingClientUtil
                    .getProposalVoteByProposalIdUserId(proposalId, userId);
            if (vote != null && !vote.getConfirmationToken().isEmpty()
                    && vote.getConfirmationToken().equalsIgnoreCase(confirmationToken)) {
                vote.setIsValid(true);
                ProposalMemberRatingClientUtil.updateProposalVote(vote);
                Proposal proposal = new Proposal(
                        ProposalsContextUtil.getClients(request).getProposalClient().getProposal(proposalId));
                model.addAttribute("proposal", proposal);
                success = true;
            } else {
                model.addAttribute("error", "TokenError");
            }
        } catch (ProposalNotFoundException  e) {
            throw new DatabaseAccessException(e);
        }
        model.addAttribute("success", success);
        return "proposals/confirmVote";
    }
}
