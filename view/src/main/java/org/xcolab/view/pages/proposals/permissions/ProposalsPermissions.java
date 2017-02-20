package org.xcolab.view.pages.proposals.permissions;


import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.legacy.enums.MemberRole;
import org.xcolab.client.members.util.MemberRoleChoiceAlgorithm;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.proposals.utils.context.ProposalContextHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextImpl;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class ProposalsPermissions {

    private final long groupId;

    private final boolean planIsEditable;

    private final long memberId;
    private final boolean isLoggedIn;

    private final Proposal proposal;
    private final ContestPhase contestPhase;
    private final ContestStatus contestStatus;
    private final HttpServletRequest request;

    private final ProposalContextHelper proposalContextHelper;


    public ProposalsPermissions(HttpServletRequest request, Proposal proposal, ContestPhase contestPhase) {
        this.request = request;
        this.proposalContextHelper = (ProposalContextHelper) request.getAttribute(
                ProposalsContextImpl.PROPOSAL_CONTEST_HELPER);

        if (contestPhase != null) {
            final long contestPhaseTypeId = contestPhase.getContestPhaseType();

            final ContestPhaseType contestPhaseType = proposalContextHelper.getClientHelper().getContestClient()
                    .getContestPhaseType(contestPhaseTypeId);
            String statusStr = contestPhaseType.getStatus();
            contestStatus = ContestStatus.valueOf(statusStr);

        } else {
            contestStatus = null;
        }

        // set proper context group id
        if (proposal == null) {
            groupId = 0L;
            planIsEditable = false;
        } else {
            groupId = proposal.getGroupId();
            planIsEditable = contestStatus != null && contestStatus.isCanEdit()
                    && contestPhase.getPhaseActive();

        }
        memberId = MemberAuthUtil.getMemberId(request);
        isLoggedIn = memberId > 0;
        this.contestPhase = contestPhase;
        this.proposal = proposal;
    }

    public boolean getCanReport() {
        return ConfigurationAttributeKey.FLAGGING_ALLOW_MEMBERS.get()
                || getCanAdminAll();
    }

    public boolean getCanReportProposal() {
        return getCanReport() && !isProposalMember();
    }

    /**
     * <p>Returns true if user is allowed to edit a proposal.</p>
     *
     * @return true if user is allowed to edit a proposal, false otherwise
     */
    public boolean getCanEdit() {
        return isLoggedIn
                && (getCanAdminAll() || planIsEditable
                && (isProposalOpen() || isProposalMember())
        );
    }

    public boolean getCanDelete() {
        return isLoggedIn
                && (getCanAdminAll() || planIsEditable && isProposalMember());
    }

    public boolean getCanCreate() {
        return isLoggedIn && getIsCreationAllowedByPhase()
                || getCanAdminAll();
    }

    public boolean getIsCreationAllowedByPhase() {
        return (contestPhase.getPhaseEndDate() == null
                || contestPhase.getPhaseEndDate().after(new Date())
        ) && contestPhase.getPhaseStartDate() != null
                && contestPhase.getPhaseStartDate().before(new Date())
                && contestStatus.isCanCreate();
    }

    public boolean getCanAssignRibbon() {
        return isLoggedIn && getCanAdminAll();
    }

    public boolean getCanPublicRating() {
        return isLoggedIn; // && !getCanJudgeActions() && !getIsTeamMember();
    }

    public boolean getCanManageUsers() {
        return getCanAdminProposal();
    }

    public boolean getCanSupportProposal() {
        return isLoggedIn && !isVotingEnabled();
    }

    public boolean getCanSubscribeContest() {
        return isLoggedIn;
    }

    public boolean getCanSubscribeProposal() {
        return isLoggedIn;
    }

    public boolean isVotingEnabled() {
        return contestPhase != null && contestPhase.getPhaseActive()
                && contestStatus.isCanVote();
    }

    public boolean getCanVote() {
        return isLoggedIn && isVotingEnabled()
                && (proposal != null && proposal.getProposalId() > 0);
    }

    public boolean getCanAdminProposal() {
        return getCanAdminAll() || isOwner();
    }

    public boolean getIsTeamMember() {
        return proposal != null && proposal.getProposalId() > 0
                && ProposalsContextUtil.getClients(request).getProposalClient().isUserInProposalTeam(proposal.getProposalId(),memberId)
                && isLoggedIn;
    }

    private boolean isOwner() {
        return isLoggedIn && (proposal == null || memberId == proposal.getAuthorId());
    }

    private boolean isProposalOpen() {
        return proposal != null && proposal.getProposalId() > 0
                && proposal.isOpen();
    }

    /**
     * Returns true if user is admin (not only proposal contributor)
     */
    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(memberId);
    }

    private boolean isProposalMember() {
        return proposal != null && proposal.getProposalId() > 0 &&
                ProposalsContextUtil.getClients(request).getProposalClient().isUserInProposalTeam(proposal.getProposalId(),memberId);
    }

    public boolean getCanFellowActions() {
        if (contestPhase == null) {
            return getCanAdminAll();
        }

        return PermissionsClient.canFellow(memberId, contestPhase.getContestPK()) || getCanAdminAll();
    }

    public boolean getCanJudgeActions() {
        if (contestPhase == null) {
            return getCanAdminAll();
        }
        return PermissionsClient.canJudge(memberId, contestPhase.getContestPK())
                || getCanAdminAll();
    }

    public boolean getCanContestManagerActions() {
        if (!isLoggedIn) {
            return false;
        }
        final MemberRoleChoiceAlgorithm roleChoiceAlgorithm =
                MemberRoleChoiceAlgorithm.proposalImpactTabAlgorithm;
        MemberRole memberRole = roleChoiceAlgorithm.getHighestMemberRoleForUser(
                MembersClient.getMemberUnchecked(memberId));
        return memberRole == MemberRole.CONTEST_MANAGER || memberRole == MemberRole.STAFF;
    }

    public boolean getCanIAFActions() {
        if (!isLoggedIn) {
            return false;
        }
        final MemberRoleChoiceAlgorithm roleChoiceAlgorithm =
                MemberRoleChoiceAlgorithm.proposalImpactTabAlgorithm;
        MemberRole memberRole = roleChoiceAlgorithm.getHighestMemberRoleForUser(
                MembersClient.getMemberUnchecked(memberId));
        return memberRole == MemberRole.IMPACT_ASSESSMENT_FELLOW;
    }

    public boolean getCanSeeAdvancingTab() {
        return getCanFellowActions()  || getCanAdminAll()
                || getCanContestManagerActions();
    }

    public boolean getCanPromoteProposalToNextPhase() {
        return contestPhase != null && getCanPromoteProposalToNextPhase(contestPhase);
    }

    public boolean getCanPromoteProposalToNextPhase(ContestPhase contestPhase) {
        if (wasProposalMovedElsewhere()) {
            return false;
        }

        boolean onlyPromoteIfCurrentContestPhaseIsNotJudged = contestPhase.getFellowScreeningActive();
        if (onlyPromoteIfCurrentContestPhaseIsNotJudged) {
            return false;
        }

        try {
            Contest latestProposalContest = proposalContextHelper.getClientHelper().getProposalClient().getCurrentContestForProposal(proposal.getProposalId());
            ContestPhase activePhaseForContest = proposalContextHelper.getClientHelper().getContestClient().getActivePhase(latestProposalContest.getContestPK());
            boolean onlyPromoteIfThisIsNotTheLatestContestPhaseInContest = contestPhase.equals(activePhaseForContest);
            return !onlyPromoteIfThisIsNotTheLatestContestPhaseInContest && getCanAdminAll();
        }catch (ContestNotFoundException ignored){

        }
        return false;
    }

    public boolean getCanMoveProposal() {
        if (wasProposalMovedElsewhere()) {
            return false;
        }

        if (getIsCreationAllowedByPhase()) {
            return getCanAdminProposal();
        }

        return getCanAdminAll();
    }

    public boolean getCanCopyProposal() {
        return !wasProposalMovedElsewhere()
                && !getIsCreationAllowedByPhase() && getCanAdminProposal();
    }

    public boolean getCanForkProposal() {
        return !wasProposalMovedElsewhere()
                && getCanAdminProposal();
    }

    private boolean wasProposalMovedElsewhere() {

        try {
            final long currentContestId = proposalContextHelper.getClientHelper().getProposalClient()
                    .getCurrentContestForProposal(proposal.getProposalId()).getContestPK();
            return currentContestId != contestPhase.getContestPK();
        }catch(ContestNotFoundException ignored){
            return false;
        }

    }
}
