package org.xcolab.portlets.proposals.view.action;



import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.SecureRandomUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;


import org.xcolab.analytics.AnalyticsUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;

import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVote;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.utils.emailnotification.proposal.ProposalVoteNotification;
import org.xcolab.utils.emailnotification.proposal.ProposalVoteValidityConfirmation;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class VoteOnProposalActionController {

    private final static Log _log = LogFactoryUtil.getLog(VoteOnProposalActionController.class);

    @Autowired
    private ProposalsContext proposalsContext;

    private final static String VOTE_ANALYTICS_KEY = "VOTE_CONTEST_ENTRIES";
    private final static String VOTE_ANALYTICS_CATEGORY = "User";
    private final static String VOTE_ANALYTICS_ACTION = "Vote contest entry";
    private final static String VOTE_ANALYTICS_LABEL = "";


    @RequestMapping(params = {"action=voteOnProposalAction"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {
        boolean hasVoted = false;
        final Proposal proposal = proposalsContext.getProposal(request);
        final Contest contest = proposalsContext.getContest(request);
        final Member member = proposalsContext.getMember(request);
        if (proposalsContext.getPermissions(request).getCanVote()) {
            long proposalId = proposal.getProposalId();
            long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
            long memberId = member.getUserId();
            if (ProposalMemberRatingClientUtil.hasUserVoted(proposalId, contestPhaseId, memberId)) {
                // User has voted for this proposal and would like to retract the vote
                ProposalMemberRatingClientUtil.deleteProposalVote(contestPhaseId, memberId);
            } else {
                if (ProposalMemberRatingClientUtil.hasUserVoted(contestPhaseId, memberId)) {
                    // User has voted for a different proposal. Vote will be retracted and converted to a vote of this proposal.
                    ProposalMemberRatingClientUtil.deleteProposalVote(contestPhaseId, memberId);
                }
                ServiceContext serviceContext = new ServiceContext();
                ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
                serviceContext.setPortalURL(themeDisplay.getPortalURL());

                ProposalMemberRatingClientUtil.addProposalVote(proposalId, contestPhaseId, memberId);

                final boolean voteIsValid = validateVote(proposalsContext.getUser(request),
                        member, proposal, contest, serviceContext);
                if (voteIsValid) {
                    try {
                        org.xcolab.client.contest.pojo.Contest contestMicro = ContestClientUtil.getContest(contest.getContestPK());
                        new ProposalVoteNotification(proposal, contestMicro, member, serviceContext).sendMessage();
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
            if (member == null || member.getUserId() == 10115) {
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

    private boolean validateVote(User user, Member member, Proposal proposal, Contest contest, ServiceContext serviceContext) throws SystemException, PortalException {

        List<Member> usersWithSharedIP = MembersClient.findMembersByIp(user.getLastLoginIP());
        usersWithSharedIP.remove(user);
        if (!usersWithSharedIP.isEmpty()) {
            final ProposalVote vote = ProposalMemberRatingClientUtil
                    .getProposalVoteByProposalIdUserId(proposal.getProposalId(), member.getUserId());
            int recentVotesFromSharedIP = 0;
            for (Member otherUser : usersWithSharedIP) {
                    final ProposalVote otherVote = ProposalMemberRatingClientUtil
                            .getProposalVoteByProposalIdUserId(proposal.getProposalId(), otherUser.getUserId());
                    //check if vote is less than 12 hours old
                if(otherVote!=null) {
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
                sendConfirmationMail(vote, proposal, contest, member, serviceContext);
            }
            ProposalMemberRatingClientUtil.updateProposalVote(vote);
            return vote.getIsValid();
        }
        return true;
    }

    private void sendConfirmationMail(ProposalVote vote, Proposal proposal, Contest contest, Member member, ServiceContext serviceContext) throws PortalException, SystemException {
        String confirmationToken = Long.toHexString(SecureRandomUtil.nextLong());
        vote.setConfirmationToken(confirmationToken);
        vote.setConfirmationEmailSendDate(new Timestamp(new Date().getTime()));
        try {
                org.xcolab.client.contest.pojo.Contest contestMicro = ContestClientUtil.getContest(contest.getContestPK());
            new ProposalVoteValidityConfirmation(proposal, contestMicro, member, serviceContext,
                    confirmationToken).sendEmailNotification();
        }catch (ContestNotFoundException ignored){

        }
    }

    @RequestMapping(params = "pageToDisplay=confirmVote")
    public String confirmVote(PortletRequest request,
                              PortletResponse response,
                              Model model,
                              @RequestParam long proposalId,
                              @RequestParam long userId,
                              @RequestParam String confirmationToken) {
        boolean success = false;
        try {
            ProposalVote vote = ProposalMemberRatingClientUtil
                    .getProposalVoteByProposalIdUserId(proposalId, userId);
            if (vote!=null&&!vote.getConfirmationToken().isEmpty()
                    && vote.getConfirmationToken().equalsIgnoreCase(confirmationToken)) {
                vote.setIsValid(true);
                ProposalMemberRatingClientUtil.updateProposalVote(vote);
                ProposalWrapper proposal = new ProposalWrapper(
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
        return "confirmVote";
    }
}
