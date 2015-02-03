package org.xcolab.jsp.tags.discussion;

import javax.portlet.PortletRequest;

import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;


public class DiscussionPermissions {

    private static final String RESOURCE_NAME = DiscussionCategoryGroup.class.getName();
    
    private User currentUser;
    private DiscussionCategoryGroup discussion;
    private PermissionChecker permissionChecker;
    private String primKey;
    private long groupId;
    private String discussionTabName;
    private Integer proposalId;
    private Integer contestPhaseId;


    public DiscussionPermissions(PortletRequest request, DiscussionCategoryGroup discussion) throws
            SystemException, PortalException{
        this.discussion = discussion;
        
         ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        this.permissionChecker = themeDisplay.getPermissionChecker();
        primKey = String.valueOf(discussion.getId());
        groupId = themeDisplay.getScopeGroupId();
        currentUser = themeDisplay.getUser();
        discussionTabName = getTabName(request);
        proposalId = getProposalId(request);
        contestPhaseId = getContestPhaseId(request);

    }
    private String getTabName(PortletRequest request){
        String discussionTabName;
        discussionTabName = request.getParameter("tab");
        if(discussionTabName == null) {
            try {
                discussionTabName = request.getParameter("pageToDisplay").replace("proposalDetails_", "");
            } catch (Exception e) {
            }
        }
        return discussionTabName;
    }
    private Integer getProposalId(PortletRequest request){
        Integer proposalId = null;
        try {
            String proposalIdParameter = request.getParameter("proposalId");
            if (proposalIdParameter != null) {
                proposalId = Integer.parseInt(proposalIdParameter);
            } else {
                proposalIdParameter = request.getParameter("planId");
                if (proposalIdParameter != null) {
                    proposalId = Integer.parseInt(proposalIdParameter);
                }
            }
        } catch (NumberFormatException e) {
        }
        return proposalId;
    }
    private Integer getContestPhaseId(PortletRequest request){
        Integer proposalId = null;
        try {
            String contestPhaseIdParameter = request.getParameter("phaseId");
            if (contestPhaseIdParameter != null) {
                proposalId = Integer.parseInt(contestPhaseIdParameter);
            }
        } catch (NumberFormatException e) {
        }
        return proposalId;
    }

    public boolean getCanSeeAddCommentButton(){
        boolean canSeeAddCommentButton = false;
        boolean isIdsInitialized = proposalId != null && contestPhaseId != null;
        boolean isDiscussionTabActive = discussionTabName != null && discussionTabName.equals("DISCUSSION");

        if(isDiscussionTabActive) {
            if(isIdsInitialized){
                canSeeAddCommentButton = isUserAllowedToAddCommentsToProposalDiscussionInContestPhase(currentUser, proposalId, contestPhaseId);
            }
        } else {
            canSeeAddCommentButton = true;
        }

        return canSeeAddCommentButton;
    }
    
    public boolean getCanAddComment() {
        return ! currentUser.isDefaultUser();
    }
    
    public boolean getCanAdminMessages() {
        return getCanAdmin() || permissionChecker.hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADMIN_MESSAGES.name());
    }

    public boolean getCanAdmin() {
        return permissionChecker.hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADMIN.name()) 
                || permissionChecker.isGroupAdmin(groupId) 
                || permissionChecker.isCompanyAdmin();
    }

    private boolean isUserAllowedToAddCommentsToProposalDiscussionInContestPhase
            (User user, Integer proposalId, Integer contestPhaseId){

        boolean isUserAllowed = false;
        try {
            Proposal proposal = ProposalLocalServiceUtil.getProposal(proposalId);
            isUserAllowed = isUserFellowOrJudgeOrAdvisor(user, proposal, contestPhaseId) ||
                    isUserProposalAuthorOrTeamMember(user, proposal);
        } catch (Exception e) {
        }
        return isUserAllowed;
    }

    private boolean isUserFellowOrJudgeOrAdvisor(User user, Proposal proposal,Integer contestPhaseId){
        boolean isFellow = false;
        boolean isJudge = false;
        boolean isAdvisor= false;

        try {
            ContestPhase contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(contestPhaseId);
            ProposalWrapper proposalWrapper = new ProposalWrapper(proposal,contestPhase);
            ContestWrapper contestWrapper = new ContestWrapper(proposalWrapper.getContest());

            isJudge = proposalWrapper.isUserAmongSelectedJudge(user);
            isFellow = proposalWrapper.isUserAmongFellows(user);
            isAdvisor = contestWrapper.isUserAmongAdvisors(user);
        } catch (SystemException | PortalException e) {
            e.printStackTrace();
        }

        return isFellow || isJudge || isAdvisor;
    }

    private boolean isUserProposalAuthorOrTeamMember(User user, Proposal proposal){
        boolean isAuthor = false;
        boolean isMember = false;

        try {
            isAuthor = proposal.getAuthorId() == user.getUserId();
            isMember = ProposalLocalServiceUtil.isUserAMember(proposal.getProposalId(), user.getUserId());
        } catch (PortalException | SystemException e) {
        }

        return isAuthor || isMember;
    }


}
