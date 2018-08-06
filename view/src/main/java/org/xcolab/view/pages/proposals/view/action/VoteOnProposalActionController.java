package org.xcolab.view.pages.proposals.view.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalMemberRatingClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVote;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.entity.utils.notifications.proposal.ProposalVoteNotification;
import org.xcolab.util.activities.enums.ActivityType;
import org.xcolab.util.activities.enums.ProposalActivityType;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.utils.voting.VoteValidator;
import org.xcolab.view.pages.proposals.utils.voting.VoteValidator.ValidationResult;
import org.xcolab.view.pages.proposals.view.proposal.tabs.ProposalDescriptionTabController;
import org.xcolab.view.util.entity.analytics.AnalyticsUtil;
import org.xcolab.view.util.googleanalytics.GoogleAnalyticsEventType;
import org.xcolab.view.util.googleanalytics.GoogleAnalyticsUtils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}")
public class VoteOnProposalActionController {

    private static final String VOTE_ANALYTICS_KEY = "VOTE_CONTEST_ENTRIES";
    private static final String VOTE_ANALYTICS_CATEGORY = "User";
    private static final String VOTE_ANALYTICS_ACTION = "Vote contest entry";
    private static final String VOTE_ANALYTICS_LABEL = "";

    private final ProposalDescriptionTabController proposalDescriptionTabController;

    @Autowired
    public VoteOnProposalActionController(
            ProposalDescriptionTabController proposalDescriptionTabController) {
        this.proposalDescriptionTabController = proposalDescriptionTabController;
    }

    @PostMapping("vote")
    public String handleAction(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, Member member,
            @RequestParam(defaultValue = "1") int voteValue)
            throws ProposalsAuthorizationException {

        final long maxContestVotes = ConfigurationAttributeKey.PROPOSALS_MAX_VOTES_PER_CONTEST.get();
        final long maxProposalVotes = ConfigurationAttributeKey.PROPOSALS_MAX_VOTES_PER_PROPOSAL
                .get();

        final ClientHelper clients = proposalContext.getClients();
        final ProposalMemberRatingClient proposalMemberRatingClient =
                clients.getProposalMemberRatingClient();

        final Proposal proposal = proposalContext.getProposal();
        final Contest contest = proposalContext.getContest();
        final ContestType contestType = proposalContext.getContestType();
        final String proposalLinkUrl = proposal.getProposalLinkUrl(contest);

        if (!proposalContext.getPermissions().getCanVote()) {
            if (member == null) {
                /* User is not logged in - don't count vote and let user log in*/
                AlertMessage.danger("You must be logged in to vote.").flash(request);
                return "redirect:" + proposalLinkUrl;
            } else {
                throw new ProposalsAuthorizationException("User not allowed to vote on proposal.");
            }
        }

        boolean hasVoted = false;
        long proposalId = proposal.getId();
        long contestPhaseId = proposalContext.getContestPhase().getId();
        long userId = member.getId();
        ActivityType activitySubType = null;
        if (proposalMemberRatingClient.hasUserVoted(proposalId, contestPhaseId, userId)) {
            // User has voted for this proposal and would like to retract the vote
            proposalMemberRatingClient.deleteProposalVote(proposalId, contestPhaseId, userId);
            activitySubType = ProposalActivityType.VOTE_RETRACTED;
        } else {
            final int votesInContest = proposalMemberRatingClient
                    .countVotesByUserInPhase(userId, contestPhaseId);
            final boolean isSwitchingVote = votesInContest > 0 && maxContestVotes == 1
                    && voteValue == 1;
            if (isSwitchingVote) {
                final List<ProposalVote> userVotesInPhase = proposalMemberRatingClient
                        .getProposalVotesByUserInPhase(userId, contestPhaseId);
                final ProposalVote oldVote = userVotesInPhase.get(0);
                proposalMemberRatingClient.deleteProposalVote(oldVote.getProposalId(),
                        contestPhaseId, userId);
            } else if (voteValue > maxProposalVotes) {
                AlertMessage.danger(String.format("You cannot assign more than %d votes per %s.",
                        maxProposalVotes, contestType.getProposalNameLowercase()))
                        .flash(request);
                return "redirect:" + proposalLinkUrl;
            } else if (votesInContest + voteValue > maxContestVotes) {
                AlertMessage.danger(String.format("You cannot assign more than %d votes per %s.",
                        maxContestVotes, contestType.getContestNameLowercase()))
                        .flash(request);
                return "redirect:" + proposalLinkUrl;
            }

            ProposalVote vote = proposalMemberRatingClient.addProposalVote(proposalId,
                    contestPhaseId, userId, voteValue);

            //populate tracking fields
            vote.setVoterIp(request.getRemoteAddr());
            vote.setVoterUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
            proposalMemberRatingClient.updateProposalVote(vote);

            final boolean isVoteValidationActive = ConfigurationAttributeKey
                    .PROPOSALS_VOTING_VALIDATION_IS_ACTIVE.get();
            if (isVoteValidationActive) {
                VoteValidator voteValidator =
                        new VoteValidator(member, proposal, contest, request.getRemoteAddr(),
                                clients);
                final ValidationResult validationResult = voteValidator.validate();
                if (validationResult == ValidationResult.INVALID_BLACKLISTED
                        || validationResult == ValidationResult.INVALID_BOUNCED_EMAIL) {
                    //TODO COLAB-2494: decide if we want to inform users of this
                    //                    AlertMessage.danger("Your vote was NOT counted because
                    // it violates our email policy. "
                    //                            + "Please refer to the Voting Rules for
                    // additional information.")
                    //                            .flash(request);
                } else {
                    hasVoted = true;
                }
            } else {
                hasVoted = true;
            }

            if (hasVoted) {
                new ProposalVoteNotification(proposal, contest, member).sendMessage();
                //publish event per contestPhaseId to allow voting on exactly one proposal per
                // contest(phase)
                AnalyticsUtil.publishEvent(request, userId, VOTE_ANALYTICS_KEY + contestPhaseId,
                        VOTE_ANALYTICS_CATEGORY, VOTE_ANALYTICS_ACTION, VOTE_ANALYTICS_LABEL, 1);
				GoogleAnalyticsUtils.pushEventAsync(GoogleAnalyticsEventType.CONTEST_ENTRY_VOTE);
                if (isSwitchingVote) {
                    activitySubType = ProposalActivityType.VOTE_SWITCHED;
                } else {
                    activitySubType = ProposalActivityType.VOTE_ADDED;
                }
                hasVoted = true;
            }
        }

        if (activitySubType != null) {
            final ActivitiesClient activityClient = clients.getActivitiesClient();
            activityClient.createActivityEntry(activitySubType, userId, proposalId);
        }

        // Redirect to prevent page-refreshing from influencing the vote
        if (ConfigurationAttributeKey.PROPOSALS_VOTING_SUCCESS_MESSAGE_IS_ACTIVE.get()
                && hasVoted) {
            return "redirect:" + proposalLinkUrl + "/voted";
        }
        return "redirect:" + proposalLinkUrl;
    }

    @GetMapping("vote")
    public String handleInvalidGetRequestToVotePage(HttpServletRequest request,
            HttpServletResponse response, Model model, ProposalContext proposalContext,
            Member member) {

        AlertMessage.warning(
                "Your vote hasn't been recorded, please make sure to click the button only once.")
                .flash(request);
        final Contest contest = proposalContext.getContest();
        final Proposal proposal = proposalContext.getProposal();
        return "redirect:" + proposal.getProposalLinkUrl(contest);
    }

    @GetMapping("voted")
    public String showVoteSuccess(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, Member currentMember,
            @PathVariable Long contestYear,
            @PathVariable String contestUrlName, @PathVariable Long proposalId) {
        return proposalDescriptionTabController.showProposalDetails(request, model,
                proposalContext, currentMember, true, false, null, null);
    }

    @GetMapping("confirmVote/{userId}/{confirmationToken}")
    public String confirmVote(HttpServletRequest request, HttpServletResponse response, Model model,
            ProposalContext proposalContext, @PathVariable long userId,
            @PathVariable String confirmationToken) {
        
        boolean success = false;
        final ClientHelper clients = proposalContext.getClients();
        Proposal proposal = proposalContext.getProposal();
        final ProposalMemberRatingClient proposalMemberRatingClient =
                clients.getProposalMemberRatingClient();

        ProposalVote vote = proposalMemberRatingClient.getProposalVoteByProposalIdUserId(
                        proposal.getId(), userId);

        if (vote != null && isValidToken(confirmationToken, vote)) {
            final Member member = MembersClient.getMemberUnchecked(vote.getUserId());
            member.setIsEmailConfirmed(true);
            MembersClient.updateMember(member);

            vote.setIsValid(true);
            vote.setLastValidationResult("USER_CONFIRMED_VOTE");
            vote.setConfirmationToken(null);
            proposalMemberRatingClient.updateProposalVote(vote);

            model.addAttribute("proposal", proposal);
            success = true;
        } else {
            model.addAttribute("error", "TokenError");
        }
        model.addAttribute("success", success);
        return "proposals/confirmVote";
    }

    private boolean isValidToken(@PathVariable String confirmationToken, ProposalVote vote) {
        return StringUtils.isNotEmpty(vote.getConfirmationToken()) && vote.getConfirmationToken()
                .equalsIgnoreCase(confirmationToken);
    }
}
