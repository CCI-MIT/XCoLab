package org.xcolab.view.pages.proposals.permissions;


import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.team.MembershipRequest;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Helper class to decide whether a certain user should see things in the UI
 */
public class ProposalsDisplayPermissions {

    private final ProposalsPermissions proposalsPermissions;
    private final ClientHelper clientHelper;
    private final Proposal proposal;
    private final ContestPhase contestPhase;
    private final long memberId;

    public ProposalsDisplayPermissions(ProposalsPermissions proposalsPermissions,
            Proposal proposal, ContestPhase contestPhase, HttpServletRequest request) {
        this.proposalsPermissions = proposalsPermissions;
        this.proposal = proposal;
        this.contestPhase = contestPhase;
        memberId = MemberAuthUtil.getMemberId(request);
        this.clientHelper = ProposalsContextUtil.getClients(request);
    }

    public boolean getCanSeeRequestMembershipButton() {
        return !proposalsPermissions.getIsTeamMember()
                && !getUserHasOpenMembershipRequest();
    }

    public boolean getUserHasOpenMembershipRequest() {
        for (MembershipRequest mr : clientHelper.getMembershipClient()
                .getMembershipRequests(proposal.getProposalId())) {
            if (mr.getUserId() == memberId && (
                    (mr.getStatusId() == MembershipRequestStatus.STATUS_PENDING)
                            || mr.getStatusId()
                            == MembershipRequestStatus.STATUS_PENDING_REQUESTED)) {
                return true;
            }
        }
        return false;
    }

    public boolean getCanSeeVoteButton() {
        return memberId == 0 || !hasVotedOnThisProposal();
    }

    private boolean hasVotedOnThisProposal() {
        return proposal != null && proposal.getProposalId() > 0
                && clientHelper.getProposalMemberRatingClient().hasUserVoted(
                proposal.getProposalId(), contestPhase.getContestPhasePK(), memberId);
    }

    public boolean getCanSeeUnsubscribeProposalButton() {
        return memberId > 0 && isSubscribedToProposal();
    }

    private boolean isSubscribedToProposal() {
        return proposal != null && proposal.getProposalId() > 0
                && clientHelper.getProposalMemberRatingClient()
                .isMemberProposalSupporter(proposal.getProposalId(), memberId);
    }

    public boolean getCanSeeUnsubscribeContestButton() {
        return memberId > 0 && isSubscribedToContest();
    }

    private boolean isSubscribedToContest() {




        return contestPhase != null
                &&
                clientHelper.getActivitiesClient().isSubscribedToActivity(memberId,
                        ActivityEntryType.CONTEST.getPrimaryTypeId(), contestPhase.getContestPK(),
                        0, "");
    }

    public boolean getCanSeeSubscribeProposalButton() {
        return memberId == 0 || !isSubscribedToProposal();
    }

    public boolean getCanSeeSubscribeContestButton() {
        return memberId == 0 || !isSubscribedToContest();
    }

    public boolean getCanSeeUnsupportButton() {
        return (memberId > 0 && isSupporter())
                && !proposalsPermissions.isVotingEnabled();
    }

    private boolean isSupporter() {
        return proposal != null && proposal.getProposalId() > 0
                && clientHelper.getProposalMemberRatingClient()
                .isMemberProposalSupporter(proposal.getProposalId(), memberId);
    }

    public boolean getCanSeeSupportButton() {
        return (memberId == 0 || !isSupporter())
                && !proposalsPermissions.isVotingEnabled();
    }
}