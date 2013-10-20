package org.xcolab.portlets.proposals.permissions;

import javax.portlet.PortletRequest;

import org.xcolab.portlets.proposals.utils.ProposalsActions; 

import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

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
    
    public ProposalsPermissions(PortletRequest request, Proposal proposal, ContestPhase contestPhase) throws PortalException, SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        
        permissionChecker = themeDisplay.getPermissionChecker();
        portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
        primKey = themeDisplay.getPortletDisplay().getResourcePK();
        
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

        return planIsEditable && (isProposalOpen() || isProposalMember());         
    }
    
    public boolean getCanDelete() {
        if (user.isDefaultUser()) 
            return false;
        
        if (getCanAdminAll()) 
            return true;
        return false;
    }
    
    public boolean getCanCreate() {
        if (user.isDefaultUser()) 
            return false;
        
        return contestStatus.isCanCreate();
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
    
    public boolean getCanRequestMembership() throws SystemException, PortalException {
        return !user.isDefaultUser() && ! isProposalMember() && !getRequestedMembership();
    }
    
    public boolean getCanSeeSupportButton() throws PortalException, SystemException {
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
        return ! user.isDefaultUser() && isVotingEnabled();
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
        return proposal == null ? false :  ProposalLocalServiceUtil.isUserAMember(proposal.getProposalId(), user.getUserId()) && !user.isDefaultUser();
    }


    private boolean hasVotedOnThisProposal() throws SystemException {
        return ProposalLocalServiceUtil.hasUserVoted(proposal.getProposalId(), contestPhase.getContestPhasePK(), user.getUserId());
    }

    private boolean isOwner() {
        return !user.isDefaultUser() && (proposal == null || user.getUserId() == proposal.getAuthorId());
    }
    
    private boolean isSupporter() throws PortalException, SystemException {
        return proposal == null ? false :  ProposalLocalServiceUtil.isSupporter(proposal.getProposalId(), user.getUserId());
    }

    private boolean isProposalOpen() throws SystemException, PortalException {
        return proposal == null ? false : ProposalLocalServiceUtil.isOpen(proposal.getProposalId());
    }
    

    private boolean getCanAdminAll() {
        boolean adminAll =  permissionChecker.hasPermission(groupId, portletId, primKey, ProposalsActions.CAN_ADMIN_ALL);
        return adminAll;
    }
    
    private boolean isProposalMember() throws SystemException {
        return GroupLocalServiceUtil.hasUserGroup(user.getUserId(), groupId);
    }

    private boolean isSubscribedToProposal() throws PortalException, SystemException {
        return proposal == null ? false :  ProposalLocalServiceUtil.isSubscribed(proposal.getProposalId(), user.getUserId());
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
        return permissionChecker.hasPermission(groupId, portletId, primKey, ProposalsActions.CAN_FELLOW_ACTIONS);
    }

    public boolean getCanJudgeActions() {
        return permissionChecker.hasPermission(groupId, portletId, primKey, ProposalsActions.CAN_JUDGE_ACTIONS);
    }


}
