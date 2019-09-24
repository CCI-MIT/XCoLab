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
import org.springframework.web.bind.annotation.ResponseBody;

import org.xcolab.client.activity.IActivityClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.proposals.IProposalMemberRatingClient;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.IProposalVote;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.entity.utils.notifications.proposal.ProposalVoteNotification;
import org.xcolab.util.activities.enums.ProposalActivityType;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.utils.voting.VoteValidator;
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
    private final IActivityClient activityClient;

    private final IUserClient userClient;

    @Autowired
    public VoteOnProposalActionController(
            ProposalDescriptionTabController proposalDescriptionTabController,
            IActivityClient activityClient, IUserClient userClient) {
        this.proposalDescriptionTabController = proposalDescriptionTabController;
        this.activityClient = activityClient;
        this.userClient = userClient;
    }

    @PostMapping("voteInContestPage")
    public String voteInContestPage(
            HttpServletRequest request, HttpServletResponse response,
            ProposalContext proposalContext,
            Model model,
            UserWrapper member,@RequestParam(defaultValue = "1") int voteValue) throws ProposalsAuthorizationException {

            this.handleAction(request,response, model, proposalContext, member,voteValue);

        return "redirect:" + proposalContext.getContest().getContestUrl();

    }



    @PostMapping("vote")
    public String handleAction(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper member,
            @RequestParam(defaultValue = "1") int voteValue)
            throws ProposalsAuthorizationException {

        final long maxContestVotes = ConfigurationAttributeKey.PROPOSALS_MAX_VOTES_PER_CONTEST.get();
        final long maxProposalVotes = ConfigurationAttributeKey.PROPOSALS_MAX_VOTES_PER_PROPOSAL
                .get();

        final ClientHelper clients = proposalContext.getClients();
        final IProposalMemberRatingClient proposalMemberRatingClient =
                clients.getProposalMemberRatingClient();

        final ProposalWrapper proposal = proposalContext.getProposal();
        final ContestWrapper contest = proposalContext.getContest();
        final ContestType contestType = proposalContext.getContestType();
        final String proposalLinkUrl = proposal.getProposalLinkUrl(contest);

        if (!proposalContext.getPermissions().getCanVote()) {
            if (member == null) {
                // User is not logged in - don't count vote and let user log in
                AlertMessage.danger("You must be logged in to vote.").flash(request);
                return "redirect:" + proposalLinkUrl;
            } else {
                throw new ProposalsAuthorizationException("User not allowed to vote on proposal.");
            }
        }

        long proposalId = proposal.getId();
        long contestPhaseId = proposalContext.getContestPhase().getId();
        long userId = member.getId();

        ProposalActivityType activitySubType;
        if (proposalMemberRatingClient.hasUserVoted(contestPhaseId,proposalId, userId)) {
            // User has voted for this proposal and would like to retract the vote
            proposalMemberRatingClient.deleteProposalVote(proposalId, contestPhaseId, userId);
            activitySubType = ProposalActivityType.VOTE_RETRACTED;
        } else {
            final int votesInContest = proposalMemberRatingClient
                    .countVotesByUserInPhase(userId, contestPhaseId);

            final boolean isSingleVoteContest = maxContestVotes == 1;
            if (isSingleVoteContest) {
                if (voteValue > 1) {
                    // The UI should not allow this to happen
                    throw new IllegalArgumentException("This contest only allows a single vote.");
                }
                final boolean isSwitchingVote = votesInContest > 0;
                if (isSwitchingVote) {
                    final List<IProposalVote> userVotesInPhase = proposalMemberRatingClient
                            .getProposalVotesByUserInPhase(userId, contestPhaseId);
                    final IProposalVote oldVote = userVotesInPhase.get(0);
                    proposalMemberRatingClient.deleteProposalVote(oldVote.getProposalId(),
                            contestPhaseId, userId);
                    activitySubType = ProposalActivityType.VOTE_SWITCHED;
                } else {
                    activitySubType = ProposalActivityType.VOTE_ADDED;
                }
            } else {
                if (voteValue > maxProposalVotes) {
                    AlertMessage.danger(String.format("You cannot assign more than %d votes per %s.",
                            maxProposalVotes, contestType.getProposalNameLowercase()))
                            .flash(request);
                    return "redirect:" + proposalLinkUrl;
                } else if (votesInContest + voteValue > maxContestVotes) {
                    AlertMessage.danger(String.format("You cannot assign more than %d votes per %s.",
                            maxContestVotes, contestType.getContestNameLowercase()))
                            .flash(request);
                    return "redirect:" + proposalLinkUrl;
                } else {
                    activitySubType = ProposalActivityType.VOTE_ADDED;
                }
            }

            IProposalVote vote = proposalMemberRatingClient.addProposalVote(proposalId,
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
                voteValidator.validate();
                // regardless of the validation result, we will treat the vote as valid in the UI
            }

            new ProposalVoteNotification(proposal, contest, member).sendMessage();
            AnalyticsUtil.publishEvent(request, userId, VOTE_ANALYTICS_KEY + contestPhaseId,
                    VOTE_ANALYTICS_CATEGORY, VOTE_ANALYTICS_ACTION, VOTE_ANALYTICS_LABEL, 1);
            GoogleAnalyticsUtils.pushEventAsync(GoogleAnalyticsEventType.CONTEST_ENTRY_VOTE);
        }

        activityClient.createActivityEntry(activitySubType, userId, proposalId);

        // Redirect to prevent page-refreshing from influencing the vote
        boolean hasVoted = activitySubType != ProposalActivityType.VOTE_RETRACTED;
        if (ConfigurationAttributeKey.PROPOSALS_VOTING_SUCCESS_MESSAGE_IS_ACTIVE.get()
                && hasVoted) {
            return "redirect:" + proposalLinkUrl + "/voted";
        }
        return "redirect:" + proposalLinkUrl;
    }

    @GetMapping("vote")
    public String handleInvalidGetRequestToVotePage(HttpServletRequest request,
            HttpServletResponse response, Model model, ProposalContext proposalContext,
            UserWrapper member) {

        AlertMessage.warning(
                "Your vote hasn't been recorded, please make sure to click the button only once.")
                .flash(request);
        final ContestWrapper contest = proposalContext.getContest();
        final ProposalWrapper proposal = proposalContext.getProposal();
        return "redirect:" + proposal.getProposalLinkUrl(contest);
    }

    @GetMapping("voted")
    public String showVoteSuccess(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper currentMember,
            @PathVariable Long contestYear,
            @PathVariable String contestUrlName, @PathVariable Long proposalId) {
        return proposalDescriptionTabController.showProposalDetails(request, response, model,
                proposalContext, currentMember, true, false, null, null);
    }

    @GetMapping("confirmVote/{userId}/{confirmationToken}")
    public String confirmVote(HttpServletRequest request, HttpServletResponse response, Model model,
            ProposalContext proposalContext, @PathVariable long userId,
            @PathVariable String confirmationToken) {
        
        boolean success = false;
        final ClientHelper clients = proposalContext.getClients();
        ProposalWrapper proposal = proposalContext.getProposal();
        final IProposalMemberRatingClient proposalMemberRatingClient =
                clients.getProposalMemberRatingClient();

        IProposalVote vote = proposalMemberRatingClient.getProposalVoteByProposalIdUserId(
                        proposal.getId(), userId);

        if (vote != null && isValidToken(confirmationToken, vote)) {
            final UserWrapper member = userClient.getMemberUnchecked(vote.getUserId());
            member.setIsEmailConfirmed(true);
            try {
                userClient.updateUser(member);
            }catch (MemberNotFoundException e){

            }

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

    private boolean isValidToken(@PathVariable String confirmationToken, IProposalVote vote) {
        return StringUtils.isNotEmpty(vote.getConfirmationToken()) && vote.getConfirmationToken()
                .equalsIgnoreCase(confirmationToken);
    }
}
