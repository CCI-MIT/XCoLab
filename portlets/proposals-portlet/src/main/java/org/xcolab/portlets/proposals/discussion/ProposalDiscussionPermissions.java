package org.xcolab.portlets.proposals.discussion;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.wrappers.DiscussionMessageWrapper;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.portlet.PortletRequest;

/**
 * Created by johannes on 12/5/15.
 */
public class ProposalDiscussionPermissions extends DiscussionPermissions {

    private final String discussionTabName;
    private final Integer proposalId;
    private final Integer contestPhaseId;

    public ProposalDiscussionPermissions(PortletRequest request, DiscussionCategoryGroup categoryGroup)
            throws SystemException, PortalException {

        super(request, categoryGroup);
        discussionTabName = getTabName(request);
        proposalId = getProposalId(request);
        contestPhaseId = getContestPhaseId(request);
    }

    private String getTabName(PortletRequest request){
        String discussionTabName = request.getParameter("tab");
        if(discussionTabName == null) {
            final String pageToDisplay = request.getParameter("pageToDisplay");
            if (pageToDisplay != null && pageToDisplay.startsWith("proposalDetails_")) {
                discussionTabName = pageToDisplay.replace("proposalDetails_", "");
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
        } catch (NumberFormatException ignored) { }
        return proposalId;
    }

    private Integer getContestPhaseId(PortletRequest request){
        Integer phaseId = null;
        try {
            String contestPhaseIdParameter = request.getParameter("phaseId");
            if (contestPhaseIdParameter != null) {
                phaseId = Integer.parseInt(contestPhaseIdParameter);
            }
        } catch (NumberFormatException ignored) { }
        return phaseId;
    }

    @Override
    public boolean getCanSeeAddCommentButton(){
        boolean canSeeAddCommentButton = false;
        boolean isIdsInitialized = proposalId != null && contestPhaseId != null;
        boolean isEvaluationTabActive = discussionTabName != null && discussionTabName.equals(ProposalTab.EVALUATION.name());

        if(isEvaluationTabActive) {
            if(isIdsInitialized){
                canSeeAddCommentButton = isUserAllowedToAddCommentsToProposalEvaluationInContestPhase(currentUser, proposalId, contestPhaseId);
            }
        } else {
        canSeeAddCommentButton = true;
        }
        return canSeeAddCommentButton;
    }

    @Override
    public boolean getCanAdminMessage(DiscussionMessageWrapper message) {
        if (message.getAuthorId() == currentUser.getUserId() && proposalId != null) {
            try {
                Proposal proposal = ProposalLocalServiceUtil.fetchProposal(proposalId);
                ProposalWrapper proposalWrapper = new ProposalWrapper(proposal);

                return proposalWrapper.isUserAmongFellows(currentUser) || getCanAdmin();
            } catch (PortalException | SystemException ignored) { }
        }
        return message.getAuthorId() == currentUser.getUserId() || getCanAdmin();
    }

    private boolean isUserAllowedToAddCommentsToProposalEvaluationInContestPhase
            (User user, Integer proposalId, Integer contestPhaseId){

        boolean isUserAllowed = false;
        try {
            Proposal proposal = ProposalLocalServiceUtil.getProposal(proposalId);
            isUserAllowed = isUserFellowOrJudgeOrAdvisor(user, proposal, contestPhaseId) ||
                    isUserProposalAuthorOrTeamMember(user, proposal);
        } catch (SystemException | PortalException ignored) { }
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
        } catch (PortalException | SystemException ignored) { }

        return isAuthor || isMember;
    }
}
