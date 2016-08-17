package org.xcolab.portlets.proposals.permissions;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.MembershipRequestConstants;
import com.liferay.portal.model.User;
import org.xcolab.enums.MembershipRequestStatus;

/**
 * Helper class to decide whether a certain user should see things in the UI
 */
public class ProposalsDisplayPermissions {

    private final ProposalsPermissions proposalsPermissions;
    private final Proposal proposal;
    private final ContestPhase contestPhase;
    private final User user;

    public ProposalsDisplayPermissions(ProposalsPermissions proposalsPermissions,
                                       Proposal proposal, ContestPhase contestPhase) {
        this.proposalsPermissions = proposalsPermissions;
        this.proposal = proposal;
        this.contestPhase = contestPhase;
        this.user = proposalsPermissions.getUser();
    }

    public boolean getCanSeeRequestMembershipButton() throws SystemException, PortalException {
        return !proposalsPermissions.getIsTeamMember()
                && !getUserHasOpenMembershipRequest();
    }

    private boolean hasVotedOnThisProposal() throws SystemException {
        return proposal != null && proposal.getProposalId() > 0
                && ProposalLocalServiceUtil.hasUserVoted(
                    proposal.getProposalId(), contestPhase.getContestPhasePK(), user.getUserId());
    }

    public boolean getCanSeeVoteButton() throws SystemException {
        return user.isDefaultUser() || ! hasVotedOnThisProposal();
    }

    boolean isSubscribedToContest() throws PortalException, SystemException {
        return contestPhase != null
                && ContestLocalServiceUtil.isSubscribed(contestPhase.getContestPK(), user.getUserId());
    }

    public boolean getCanSeeUnsubscribeProposalButton() throws PortalException, SystemException {
        return !user.isDefaultUser() && isSubscribedToProposal();
    }

    boolean isSubscribedToProposal() throws PortalException, SystemException {
        return proposal != null && proposal.getProposalId() > 0
                && ProposalLocalServiceUtil.isSubscribed(proposal.getProposalId(), user.getUserId());
    }

    public boolean getCanSeeUnsubscribeContestButton() throws PortalException, SystemException {
        return !user.isDefaultUser() && isSubscribedToContest();
    }

    public boolean getCanSeeSubscribeProposalButton() throws PortalException, SystemException {
        return user.isDefaultUser() || !isSubscribedToProposal();
    }

    public boolean getCanSeeSubscribeContestButton() throws PortalException, SystemException {
        return user.isDefaultUser() || !isSubscribedToContest();
    }

    boolean isSupporter() throws PortalException, SystemException {
        return proposal != null && proposal.getProposalId() > 0
                && ProposalLocalServiceUtil.isSupporter(proposal.getProposalId(), user.getUserId());
    }

    public boolean getCanSeeUnsupportButton() throws PortalException, SystemException {
        return (!user.isDefaultUser() && isSupporter())
                && !proposalsPermissions.isVotingEnabled();
    }

    public boolean getCanSeeSupportButton() throws PortalException, SystemException {
        return (user.isDefaultUser() || !isSupporter())
                && !proposalsPermissions.isVotingEnabled();
    }

    public boolean getUserHasOpenMembershipRequest() throws PortalException, SystemException {
        for (MembershipRequest mr : ProposalLocalServiceUtil.getMembershipRequests(proposal.getProposalId())){
            if (mr.getUserId() == user.getUserId() && ((mr.getStatusId() == MembershipRequestConstants.STATUS_PENDING)
                    ||mr.getStatusId() == MembershipRequestStatus.STATUS_PENDING_REQUESTED)) {
                return true;
            }
        }
        return false;
    }
}