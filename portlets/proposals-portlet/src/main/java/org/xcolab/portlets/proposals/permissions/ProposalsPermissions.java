package org.xcolab.portlets.proposals.permissions;

import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.enums.MemberRole;
import org.xcolab.enums.MemberRoleChoiceAlgorithm;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.ReferenceResolutionException;

import java.util.Date;

import javax.portlet.PortletRequest;

public class ProposalsPermissions {

    private final long groupId;

    private final boolean planIsEditable;

    private final User user;

    private final Proposal proposal;
    private final ContestPhase contestPhase;
    private final ContestStatus contestStatus;

    public ProposalsPermissions(PortletRequest request, Proposal proposal, ContestPhase contestPhase) {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        if (contestPhase != null) {
            final long contestPhaseTypeId = contestPhase.getContestPhaseType();
            try {
                final ContestPhaseType contestPhaseType = ContestPhaseTypeLocalServiceUtil
                        .getContestPhaseType(contestPhaseTypeId);
                String statusStr = contestPhaseType.getStatus();
                contestStatus = ContestStatus.valueOf(statusStr);
            } catch (SystemException e) {
                throw new DatabaseAccessException(e);
            } catch (PortalException e) {
                throw ReferenceResolutionException
                        .toObject(ContestPhaseType.class, contestPhaseTypeId)
                        .fromObject(ContestPhase.class, contestPhase.getContestPK());
            }
        } else {
            contestStatus = null;
        }

        // set proper context group id
        if (proposal == null) {
            groupId = themeDisplay.getScopeGroupId();
            planIsEditable = false;
        } else {
            groupId = proposal.getGroupId();
            planIsEditable = contestStatus != null && contestStatus.isCanEdit()
                    && ContestPhaseLocalServiceUtil.getPhaseActive(contestPhase);

        }
        user = themeDisplay.getUser();
        this.contestPhase = contestPhase;
        this.proposal = proposal;
    }

    public boolean getCanReport() {
        return ConfigurationAttributeKey.FLAGGING_ALLOW_MEMBERS.getBooleanValue()
                || getCanAdminAll();
    }

    public boolean getCanReportProposal() throws SystemException {
        return getCanReport() && !isProposalMember();
    }

    /**
     * <p>Returns true if user is allowed to edit a proposal.</p>
     *
     * @return true if user is allowed to edit a proposal, false otherwise
     */
    public boolean getCanEdit() throws SystemException, PortalException {
        return !user.isDefaultUser()
                && (getCanAdminAll() || planIsEditable
                && (isProposalOpen() || isProposalMember())
        );
    }

    public boolean getCanDelete() throws SystemException {
        return !user.isDefaultUser()
                && (getCanAdminAll() || planIsEditable && isProposalMember());
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

    public boolean getCanPublicRating() throws SystemException, PortalException {
        return !user.isDefaultUser(); // && !getCanJudgeActions() && !getIsTeamMember();
    }

    public boolean getCanManageUsers() throws SystemException, PortalException {
        return getCanAdminProposal();
    }

    public boolean getCanSupportProposal() throws PortalException, SystemException {
        return !user.isDefaultUser() && !isVotingEnabled();
    }

    public boolean getCanSubscribeContest() {
        return !user.isDefaultUser();
    }

    public boolean getCanSubscribeProposal() {
        return !user.isDefaultUser();
    }

    public boolean isVotingEnabled() {
        return contestPhase != null && ContestPhaseLocalServiceUtil.getPhaseActive(contestPhase)
                && contestStatus.isCanVote();
    }

    public boolean getCanVote() {
        return !user.isDefaultUser() && isVotingEnabled()
                && (proposal != null && proposal.getProposalId() > 0);
    }

    public boolean getCanAdminProposal() {
        return getCanAdminAll() || isOwner();
    }

    public boolean getIsTeamMember() throws SystemException, PortalException {
        return proposal != null && proposal.getProposalId() > 0
                && ProposalLocalServiceUtil.isUserAMember(proposal.getProposalId(), user.getUserId())
                && !user.isDefaultUser();
    }

    private boolean isOwner() {
        return !user.isDefaultUser() && (proposal == null || user.getUserId() == proposal.getAuthorId());
    }

    private boolean isProposalOpen() throws SystemException, PortalException {
        return proposal != null && proposal.getProposalId() > 0
                && ProposalLocalServiceUtil.isOpen(proposal.getProposalId());
    }

    /**
     * Returns true if user is admin (not only proposal contributor)
     */
    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(user.getUserId());
    }

    private boolean isProposalMember() throws SystemException {
        return GroupLocalServiceUtil.hasUserGroup(user.getUserId(), groupId);
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
        boolean canContestManagerActions = false;
        try {
            MemberRole memberRole = MemberRoleChoiceAlgorithm.proposalImpactTabAlgorithm.getHighestMemberRoleForUser(user);
            canContestManagerActions = (memberRole == MemberRole.CONTEST_MANAGER || memberRole == MemberRole.STAFF);
        } catch (SystemException ignored) {
        }
        return canContestManagerActions;
    }

    public boolean getCanIAFActions() {
        boolean canIAFAction = false;
        try {
            MemberRole memberRole = MemberRoleChoiceAlgorithm.proposalImpactTabAlgorithm.getHighestMemberRoleForUser(user);
            canIAFAction = (memberRole == MemberRole.IMPACT_ASSESSMENT_FELLOW);
        } catch (SystemException ignored) {
        }
        return canIAFAction;
    }

    public boolean getCanSeeAdvancingTab() {
        return getCanFellowActions() || getCanJudgeActions() || getCanAdminAll()
                || getCanContestManagerActions();
    }

    public boolean getCanPromoteProposalToNextPhase() throws PortalException, SystemException {
        return contestPhase != null && getCanPromoteProposalToNextPhase(contestPhase);
    }

    public boolean getCanPromoteProposalToNextPhase(ContestPhase contestPhase) throws PortalException, SystemException {
        if (wasProposalMovedElsewhere()) {
            return false;
        }

        boolean onlyPromoteIfCurrentContestPhaseIsNotJudged = contestPhase.getFellowScreeningActive();
        if (onlyPromoteIfCurrentContestPhaseIsNotJudged) {
            return false;
        }

        Contest latestProposalContest = ProposalLocalServiceUtil.getLatestProposalContest(proposal.getProposalId());
        ContestPhase activePhaseForContest = ContestPhaseLocalServiceUtil.getActivePhaseForContest(latestProposalContest);
        boolean onlyPromoteIfThisIsNotTheLatestContestPhaseInContest = contestPhase.equals(activePhaseForContest);

        return !onlyPromoteIfThisIsNotTheLatestContestPhaseInContest && getCanAdminAll();
    }

    public boolean getCanMoveProposal() throws SystemException, PortalException {
        if (wasProposalMovedElsewhere()) {
            return false;
        }

        if (getIsCreationAllowedByPhase()) {
            return getCanAdminProposal();
        }

        return getCanAdminAll();
    }

    public boolean getCanCopyProposal() throws SystemException, PortalException {
        return !wasProposalMovedElsewhere()
                && !getIsCreationAllowedByPhase() && getCanAdminProposal();
    }

    public boolean getCanForkProposal() throws SystemException, PortalException {
        return !wasProposalMovedElsewhere()
                && getCanAdminProposal();
    }

    private boolean wasProposalMovedElsewhere() throws SystemException, PortalException {
        final long currentContestId = Proposal2PhaseLocalServiceUtil
                .getCurrentContestForProposal(proposal.getProposalId()).getContestPK();
        return currentContestId != contestPhase.getContestPK();
    }

    public User getUser() {
        return user;
    }
}
