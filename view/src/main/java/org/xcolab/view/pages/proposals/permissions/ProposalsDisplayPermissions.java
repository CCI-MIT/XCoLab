package org.xcolab.view.pages.proposals.permissions;


import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalTeamMembershipRequestWrapper;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;

/**
 * Helper class to decide whether a certain user should see things in the UI
 */
public class ProposalsDisplayPermissions {

    private final ProposalsPermissions proposalsPermissions;
    private final ClientHelper clientHelper;
    private final ProposalWrapper proposal;
    private final ContestPhaseWrapper contestPhase;
    private final long userId;
    private final boolean isGuest;
    private final boolean isLoggedIn;

    public ProposalsDisplayPermissions(ProposalsPermissions proposalsPermissions, ProposalWrapper proposal,
            ContestPhaseWrapper contestPhase, ClientHelper clientHelper, long userId) {
        this.proposalsPermissions = proposalsPermissions;
        this.proposal = proposal;
        this.contestPhase = contestPhase;
        this.userId = userId;
        this.isLoggedIn = userId > 0;
        this.isGuest = StaticUserContext.getPermissionClient().isGuest(userId);
        this.clientHelper = clientHelper;
    }

    public boolean getCanSeeCreateProposalButton() {
        final boolean canSeeCreateProposalButton = !isGuest
                && proposalsPermissions.getIsCreationAllowedByPhase();
        return canSeeCreateProposalButton || proposalsPermissions.getCanAdminAll();
    }

    public boolean getCanSeeRequestMembershipButton() {
        return !isGuest && !proposalsPermissions.getIsTeamMember()
                && !getUserHasOpenMembershipRequest();
    }

    public boolean getUserHasOpenMembershipRequest() {
        for (ProposalTeamMembershipRequestWrapper mr : clientHelper.getMembershipClient()
                .getMembershipRequests(proposal.getId())) {
            if (mr.getUserId() == userId && (
                    (mr.getStatusId() == MembershipRequestStatus.STATUS_PENDING)
                            || mr.getStatusId()
                            == MembershipRequestStatus.STATUS_PENDING_REQUESTED)) {
                return true;
            }
        }
        return false;
    }

    public boolean getCanSeeVoteButton() {
        return !isLoggedIn || (!isGuest && !hasVotedOnThisProposal());
    }

    public boolean getCanSeeVoteButtonForProposal(Long proposalId) {
        return !isLoggedIn || (!isGuest && !(clientHelper.getProposalMemberRatingClient()
                .hasUserVoted(proposalId, contestPhase.getId(), userId)));
    }
    public int countVotesByUserInPhase(Long proposalId) {
        return clientHelper.getProposalMemberRatingClient()
                .countVotesByUserInProposalAndPhase(userId, proposalId, contestPhase.getId());
    }

    private boolean hasVotedOnThisProposal() {
        return proposal != null && proposal.getId() > 0
                && clientHelper.getProposalMemberRatingClient().hasUserVoted(
                contestPhase.getId(), proposal.getId(), userId);
    }

    public boolean getCanSeeUnsubscribeProposalButton() {
        return isLoggedIn && isSubscribedToProposal();
    }

    private boolean isSubscribedToProposal() {
        return proposal != null && proposal.getId() > 0
                && (clientHelper.getProposalClient()
                .isMemberSubscribedToProposal(proposal.getId(),userId));
    }

    public boolean getCanSeeUnsubscribeContestButton() {
        return isLoggedIn && isSubscribedToContest();
    }

    private boolean isSubscribedToContest() {
        return contestPhase != null
                && StaticActivityContext.getActivityClient().isSubscribed(userId,
                        ActivityCategory.CONTEST, contestPhase.getContestId());
    }

    public boolean getCanSeeSubscribeProposalButton() {
        return !isLoggedIn || !isSubscribedToProposal();
    }

    public boolean getCanSeeSubscribeContestButton() {
        return !isLoggedIn || !isSubscribedToContest();
    }

    public boolean getCanSeeUnsupportButton() {
        return (isLoggedIn && isSupporter())
                && !proposalsPermissions.isVotingEnabled();
    }

    private boolean isSupporter() {
        return proposal != null && proposal.getId() > 0
                && clientHelper.getProposalMemberRatingClient()
                .isMemberProposalSupporter(proposal.getId(), userId);
    }

    public boolean getCanSeeSupportButton() {
        return (!isLoggedIn || !isSupporter())
                && !proposalsPermissions.isVotingEnabled();
    }

    public boolean getCanSeeReviewStatus() {
        return proposalsPermissions.getCanJudgeActions()
                || proposalsPermissions.getCanFellowActions()
                || proposalsPermissions.getCanIAFActions();
    }
}
