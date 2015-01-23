package org.xcolab.portlets.proposals.permissions;

import javax.portlet.PortletRequest;

import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.*;
import com.ext.portlet.service.persistence.Proposal2PhasePK;
import com.liferay.portal.model.MembershipRequestConstants;
import org.xcolab.portlets.proposals.utils.ProposalsActions;

import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.ContestPhase;
import com.liferay.portal.model.MembershipRequest;
import com.ext.portlet.model.Proposal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.Date;
import java.util.List;

public class ProposalsPermissions {
    private final PermissionChecker permissionChecker;
    private final String portletId;
    private final long groupId;
    private final String primKey;
    
    
    private final boolean planIsEditable;
    
    private final User user;

    private final Proposal proposal;
    private final ContestPhase contestPhase;
    private final ContestStatus contestStatus;
    private final long scopeGroupId;
    
    public ProposalsPermissions(PortletRequest request, Proposal proposal, ContestPhase contestPhase) throws PortalException, SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        
        permissionChecker = themeDisplay.getPermissionChecker();
        portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
        primKey = themeDisplay.getPortletDisplay().getResourcePK();
        scopeGroupId = themeDisplay.getScopeGroupId();
        
        if (contestPhase != null) {
            String statusStr = 
                    ContestPhaseTypeLocalServiceUtil.getContestPhaseType(contestPhase.getContestPhaseType()).getStatus();
            contestStatus = ContestStatus.valueOf(statusStr);
        }
        else {
            contestStatus = null;
        }
        
        // set proper context group id
        if (proposal == null) {
            groupId = themeDisplay.getScopeGroupId();
            planIsEditable = false;   
        }
        else {
            groupId = proposal.getGroupId();
            planIsEditable = contestStatus.isCanEdit() && ContestPhaseLocalServiceUtil.getPhaseActive(contestPhase);
            
        }
        user = themeDisplay.getUser();
        this.contestPhase = contestPhase;
        this.proposal = proposal;   
    }
    
    /**
     * <p>Returns true if user is allowed to edit a proposal.</p>
     * @return true if user is allowed to edit a proposal, false otherwise
     * @throws SystemException
     * @throws PortalException
     */
    public boolean getCanEdit() throws SystemException, PortalException {
        // guests aren't allowed to edit
        if (user.isDefaultUser()) 
            return false;
        
        if (getCanAdminAll()) 
            return true;

        return planIsEditable &&
                (isProposalOpen() || isProposalMember());
    }
    
    public boolean getCanDelete() throws SystemException {
        if (user.isDefaultUser()) 
            return false;
        
        if (getCanAdminAll()) 
            return true;

        return planIsEditable && isProposalMember();
    }
    
    public boolean getCanCreate() {
        // guests aren't allowed to edit
        if (user.isDefaultUser())
            return false;

        return getIsCreationAllowedByPhase();
    }

    public boolean getIsCreationAllowedByPhase() {
        if ((contestPhase.getPhaseEndDate() == null || contestPhase.getPhaseEndDate().after(new Date())) &&
                contestPhase.getPhaseStartDate() != null && contestPhase.getPhaseStartDate().before(new Date()))
            return contestStatus.isCanCreate();
        else return false;
    }


    
    public boolean getCanAssignRibbon() {
        if (user.isDefaultUser()) 
            return false;
        
        if (getCanAdminAll()) 
            return true;
        return false;
    }
    
    
    
    public boolean getCanSeeRequestMembershipButton() throws SystemException, PortalException {
        return user.isDefaultUser() || getCanRequestMembership();
    }
    
    public boolean getCanManageUsers() throws SystemException, PortalException {
    	return getCanAdmin();
    }
    
    public boolean getCanRequestMembership() throws SystemException, PortalException {
        return !user.isDefaultUser() && ! isProposalMember() && !getRequestedMembership();
    }
    
    public boolean getCanSeeSupportButton() throws PortalException, SystemException {
        if (contestPhase.getContestPhaseType() == 13 || contestPhase.getContestPhaseType() == 15) return false;   /* Hide Button in voting phase */
        return user.isDefaultUser() || !isSupporter();
    }

    public boolean getCanSeeUnsupportButton() throws PortalException, SystemException {
        return !user.isDefaultUser() && isSupporter();
    }
    
    public boolean getCanSupportProposal() throws PortalException, SystemException {

        return ! user.isDefaultUser();
    }

    public boolean getCanSubscribeContest() {
        return ! user.isDefaultUser();
    }
    
    public boolean getCanSeeSubscribeContestButton() throws PortalException, SystemException {
        return user.isDefaultUser() || ! isSubscribedToContest();
    }
    public boolean getCanSeeUnsubscribeContestButton() throws PortalException, SystemException {
        return ! user.isDefaultUser() && isSubscribedToContest();
    }

    public boolean getCanSubscribeProposal() {
        return ! user.isDefaultUser();
    }
    
    public boolean getCanSeeSubscribeProposalButton() throws PortalException, SystemException {
        return user.isDefaultUser() || ! isSubscribedToProposal();
    }

    public boolean getCanSeeUnsubscribeProposalButton() throws PortalException, SystemException {
        return ! user.isDefaultUser() && isSubscribedToProposal();
    }
    
    public boolean isVotingEnabled() {
        return contestPhase == null ? false : ContestPhaseLocalServiceUtil.getPhaseActive(contestPhase) && contestStatus.isCanVote(); 
    }
    
    public boolean getCanVote() {
        return ! user.isDefaultUser() && isVotingEnabled() && (proposal != null && proposal.getProposalId() > 0);
    }
    
    public boolean getCanAdmin() {
        return getCanAdminAll() || isOwner();
    }
    

    public boolean getCanSeeVoteButton() throws SystemException {
        return user.isDefaultUser() || ! hasVotedOnThisProposal();
    }

    public boolean getCanSeeUnvoteButton() throws SystemException {
        return !user.isDefaultUser() && hasVotedOnThisProposal();
    }

    public boolean getIsTeamMember() throws SystemException, PortalException {
        return proposal != null && proposal.getProposalId() > 0 ? ProposalLocalServiceUtil.isUserAMember(proposal.getProposalId(), user.getUserId()) && !user.isDefaultUser() : false;
    }


    private boolean hasVotedOnThisProposal() throws SystemException {
        return proposal != null && proposal.getProposalId() > 0 ? ProposalLocalServiceUtil.hasUserVoted(proposal.getProposalId(), contestPhase.getContestPhasePK(), user.getUserId()) : false;
    }

    private boolean isOwner() {
        return !user.isDefaultUser() && (proposal == null || user.getUserId() == proposal.getAuthorId());
    }
    
    private boolean isSupporter() throws PortalException, SystemException {
        return proposal != null && proposal.getProposalId() > 0 ? ProposalLocalServiceUtil.isSupporter(proposal.getProposalId(), user.getUserId()) : false;
    }

    private boolean isProposalOpen() throws SystemException, PortalException {
        return proposal != null && proposal.getProposalId() > 0 ? ProposalLocalServiceUtil.isOpen(proposal.getProposalId()) : false;
    }


    /**
     * Returns true if user is admin (not only proposal contributor)
     * @return
     */
    public boolean getCanAdminAll() {
        return permissionChecker.hasPermission(scopeGroupId, portletId, primKey, ProposalsActions.CAN_ADMIN_ALL);
    }
    
    private boolean isProposalMember() throws SystemException {
        return GroupLocalServiceUtil.hasUserGroup(user.getUserId(), groupId);
    }

    private boolean isSubscribedToProposal() throws PortalException, SystemException {
        return proposal != null && proposal.getProposalId() > 0 ? ProposalLocalServiceUtil.isSubscribed(proposal.getProposalId(), user.getUserId()) : false;
    }

    private boolean isSubscribedToContest() throws PortalException, SystemException {
        return contestPhase == null ? false : ContestLocalServiceUtil.isSubscribed(contestPhase.getContestPK(), user.getUserId());
    }

    private boolean getRequestedMembership() throws PortalException, SystemException {
        if (! user.isDefaultUser()) {
            return ProposalLocalServiceUtil.hasUserRequestedMembership(proposal.getProposalId(), user.getUserId());
        }
        return false;
    }

    public boolean getCanFellowActions() {
        long contestGroupId;
        try{
            contestGroupId = ContestLocalServiceUtil.getContest(contestPhase.getContestPK()).getGroupId();
        } catch (Exception e) { return false; }
        return permissionChecker.hasPermission(contestGroupId, portletId, primKey, ProposalsActions.CAN_FELLOW_ACTIONS) || getCanAdminAll();
    }

    public boolean getCanJudgeActions() {
        long contestGroupId;
        try{
            contestGroupId = ContestLocalServiceUtil.getContest(contestPhase.getContestPK()).getGroupId();
        } catch (Exception e) { return false; }
        return permissionChecker.hasPermission(contestGroupId, portletId, primKey, ProposalsActions.CAN_JUDGE_ACTIONS) || getCanAdminAll();
    }


    public boolean getCanAdminJudgeActions() {
        return contestPhase.getFellowScreeningActive();
    }


    public boolean getUserHasOpenMembershipRequest() throws PortalException, SystemException {
        for (MembershipRequest mr : ProposalLocalServiceUtil.getMembershipRequests(proposal.getProposalId())){
            if (mr.getUserId() == user.getUserId() && mr.getStatusId() == MembershipRequestConstants.STATUS_PENDING) return true;
        }
        return false;
    }
    
    public boolean getCanCopyProposal() throws SystemException {
    	return !ContestLocalServiceUtil.findByActive(true).isEmpty();
    }
    
    public boolean getCanMoveProposalAndHideInCurrentContest() throws SystemException, PortalException {
        if(Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId()).getContestPK() != contestPhase.getContestPK()){
            // Proposal is currently associated with a different contest and is active there (i.e. has been moved before) (3)
            return false;
        }
        // In Submission Phase, owner and admin should be able to move
        if (getIsCreationAllowedByPhase()){
            return isOwner() || getCanAdminAll();
        }
        // Otherwise just the admin should be able to move between contests
    	return getCanAdminAll();
    }

    public boolean getCanMoveProposalAndKeepInCurrentContest() throws SystemException, PortalException {
        /**
         * Allow this type of movement if:
         *   1) Proposal did not make it to the currently active or last phase
         *   2) User is trying to move this proposal away from the last phase it was advanced to (i.e. the last phase it shows up in)
         * Do not move if:
         *   3) Proposal has been moved before and is active in a different contest
         */

        if(Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId()).getContestPK() != contestPhase.getContestPK()){
            // Proposal is currently associated with a different contest and is active there (i.e. has been moved before) (3)
            return false;
        }

        ContestPhase lastPhase = ContestLocalServiceUtil.getActiveOrLastPhase(ContestLocalServiceUtil.getContest(contestPhase.getContestPK()));
        Proposal2Phase p2p;
        try{
            p2p = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposal.getProposalId(),contestPhase.getContestPhasePK());
        } catch (Exception e){
            // no phase found
            return false;
        }

        if(p2p == null || p2p.getVersionTo()>=0){
            // User is not viewing latest version/phase this proposal was advanced to (Violation of 2)
            return false;
        }
        if (p2p.getContestPhaseId() == lastPhase.getContestPhasePK()){
            // Check of this is the last phase in the contest (2)
            List <ContestPhase> phases = ContestPhaseLocalServiceUtil.getPhasesForContest(contestPhase.getContestPK());
            ContestPhase lastPhaseofContest = phases.get(phases.size()-1);
            if (lastPhaseofContest.getContestPhasePK() != p2p.getContestPhaseId()){
                // This is the current active phase (Violation of 1)
                return false;
            }
        }

        // allow copy only if the current contest is not in creation phase anymore, in this case "move" should be used instead of "copy"
        if (getIsCreationAllowedByPhase()){
            return false;
        }

        return  isOwner() || getCanAdminAll();
    }

}
