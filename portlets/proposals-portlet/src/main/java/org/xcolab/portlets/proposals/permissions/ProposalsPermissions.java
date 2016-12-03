package org.xcolab.portlets.proposals.permissions;

import com.ext.portlet.contests.ContestStatus;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.legacy.enums.MemberRole;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.util.MemberRoleChoiceAlgorithm;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.portlets.proposals.utils.context.ClientHelper;
import org.xcolab.portlets.proposals.utils.context.ProposalContextHelper;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextImpl;

import java.util.Date;

import javax.portlet.PortletRequest;

public class ProposalsPermissions {

    private final boolean planIsEditable;

    private final User user;
    private final Member member;

    private final Proposal proposal;
    private final ContestPhase contestPhase;
    private final ContestStatus contestStatus;

    private final ProposalClient proposalClient;
    private final ContestClient contestClient;

    public ProposalsPermissions(PortletRequest request, Proposal proposal, ContestPhase contestPhase) {

        ProposalContextHelper proposalContextHelper = (ProposalContextHelper) request
                .getAttribute(ProposalsContextImpl.PROPOSAL_CONTEXT_HELPER);

        if (proposalContextHelper != null) {
            ClientHelper clientHelper = proposalContextHelper.getClientHelper();
            proposalClient = clientHelper.getProposalClient();
            contestClient = clientHelper.getContestClient();
        } else {
            proposalClient = ProposalClientUtil.getClient();
            contestClient = ContestClientUtil.getClient();
        }

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        if (contestPhase != null) {
            final long contestPhaseTypeId = contestPhase.getContestPhaseType();

            final ContestPhaseType contestPhaseType = contestClient
                    .getContestPhaseType(contestPhaseTypeId);
            String statusStr = contestPhaseType.getStatus();
            contestStatus = ContestStatus.valueOf(statusStr);

        } else {
            contestStatus = null;
        }

        planIsEditable = proposal != null && contestStatus != null && contestStatus.isCanEdit()
                && contestPhase.getPhaseActive();
        user = themeDisplay.getUser();
        member = MembersClient.getMemberUnchecked(user.getUserId());
        this.contestPhase = contestPhase;
        this.proposal = proposal;
    }

    public boolean getCanReport() {
        return ConfigurationAttributeKey.FLAGGING_ALLOW_MEMBERS.get()
                || getCanAdminAll();
    }

    public boolean getCanReportProposal() {
        return getCanReport() && !getIsTeamMember();
    }

    /**
     * <p>Returns true if user is allowed to edit a proposal.</p>
     *
     * @return true if user is allowed to edit a proposal, false otherwise
     */
    public boolean getCanEdit() {
        return !user.isDefaultUser()
                && (getCanAdminAll() || planIsEditable
                && (isProposalOpen() || getIsTeamMember())
        );
    }

    public boolean getCanDelete() {
        return !user.isDefaultUser()
                && (getCanAdminAll() || planIsEditable && getIsTeamMember());
    }

    public boolean getCanCreate() {
        return !user.isDefaultUser() && getIsCreationAllowedByPhase()
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
        return !user.isDefaultUser() && getCanAdminAll();
    }

    public boolean getCanPublicRating() {
        return !user.isDefaultUser(); // && !getCanJudgeActions() && !getIsTeamMember();
    }

    public boolean getCanManageUsers() {
        return getCanAdminProposal();
    }

    public boolean getCanSupportProposal() {
        return !user.isDefaultUser() && !isVotingEnabled();
    }

    public boolean getCanSubscribeContest() {
        return !user.isDefaultUser();
    }

    public boolean getCanSubscribeProposal() {
        return !user.isDefaultUser();
    }

    public boolean isVotingEnabled() {
        return contestPhase != null && contestPhase.getPhaseActive()
                && contestStatus.isCanVote();
    }

    public boolean getCanVote() {
        return !user.isDefaultUser() && isVotingEnabled()
                && (proposal != null && proposal.getProposalId() > 0);
    }

    public boolean getCanAdminProposal() {
        return getCanAdminAll() || isOwner();
    }

    public boolean getIsTeamMember() {
        return proposal != null && proposal.getProposalId() > 0
                && proposalClient.isUserInProposalTeam(proposal.getProposalId(),user.getUserId())
                && !user.isDefaultUser();
    }

    private boolean isOwner() {
        return !user.isDefaultUser() && (proposal == null || user.getUserId() == proposal.getAuthorId());
    }

    private boolean isProposalOpen() {
        return proposal != null && proposal.getProposalId() > 0
                && proposal.isOpen();
    }

    /**
     * Returns true if user is admin (not only proposal contributor)
     */
    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(user.getUserId());
    }

    public boolean getCanFellowActions() {
        if (contestPhase == null) {
            return getCanAdminAll();
        }
        return PermissionsClient.canFellow(user.getUserId(), contestPhase.getContestPK()) || getCanAdminAll();
    }

    public boolean getCanJudgeActions() {
        if (contestPhase == null) {
            return getCanAdminAll();
        }
        return PermissionsClient.canJudge(user.getUserId(), contestPhase.getContestPK())
                || getCanAdminAll();
    }

    public boolean getCanContestManagerActions() {
        final MemberRoleChoiceAlgorithm roleChoiceAlgorithm =
                MemberRoleChoiceAlgorithm.proposalImpactTabAlgorithm;
        MemberRole memberRole = roleChoiceAlgorithm.getHighestMemberRoleForUser(member);
        return memberRole == MemberRole.CONTEST_MANAGER || memberRole == MemberRole.STAFF;
    }

    public boolean getCanIAFActions() {
        final MemberRoleChoiceAlgorithm roleChoiceAlgorithm =
                MemberRoleChoiceAlgorithm.proposalImpactTabAlgorithm;
        MemberRole memberRole = roleChoiceAlgorithm.getHighestMemberRoleForUser(member);
        return memberRole == MemberRole.IMPACT_ASSESSMENT_FELLOW;
    }

    public boolean getCanSeeAdvancingTab() {
        return getCanFellowActions() || getCanJudgeActions() || getCanAdminAll()
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
            Contest latestProposalContest = proposalClient.getCurrentContestForProposal(proposal.getProposalId());
            ContestPhase activePhaseForContest = contestClient.getActivePhase(latestProposalContest.getContestPK());
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
            final long currentContestId = proposalClient
                    .getCurrentContestForProposal(proposal.getProposalId()).getContestPK();
            return currentContestId != contestPhase.getContestPK();
        } catch(ContestNotFoundException ignored) {
            return false;
        }

    }

    public User getUser() {
        return user;
    }

    public Member getMember() {
        return member;
    }
}
