package org.xcolab.portlets.proposals.discussion;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.InternalException;

import javax.portlet.PortletRequest;

public class ProposalDiscussionPermissions extends DiscussionPermissions {

    private final String discussionTabName;
    private final Long proposalId;
    private final Long contestPhaseId;
    private final PortletRequest request;

    public ProposalDiscussionPermissions(PortletRequest request) {
        super(request);
        discussionTabName = getTabName(request);
        proposalId = getProposalId(request);
        contestPhaseId = getContestPhaseId(request);
        this.request = request;
    }

    private String getTabName(PortletRequest request) {
        String discussionTabName = request.getParameter("tab");
        if (discussionTabName == null) {
            final String pageToDisplay = request.getParameter("pageToDisplay");
            if (pageToDisplay != null && pageToDisplay.startsWith("proposalDetails_")) {
                discussionTabName = pageToDisplay.replace("proposalDetails_", "");
            }
        }
        return discussionTabName;
    }

    private Long getProposalId(PortletRequest request) {
        Long proposalId = null;
        try {
            String proposalIdParameter = request.getParameter("proposalId");
            if (proposalIdParameter != null) {
                proposalId = Long.parseLong(proposalIdParameter);
            } else {
                proposalIdParameter = request.getParameter("planId");
                if (proposalIdParameter != null) {
                    proposalId = Long.parseLong(proposalIdParameter);
                }
            }
        } catch (NumberFormatException ignored) {
        }
        return proposalId;
    }

    private Long getContestPhaseId(PortletRequest request) {
        Long phaseId = null;
        try {
            String contestPhaseIdParameter = request.getParameter("phaseId");
            if (contestPhaseIdParameter != null) {
                phaseId = Long.parseLong(contestPhaseIdParameter);
            } else if (proposalId != null && proposalId > 0) {
                phaseId = ProposalsContextUtil.getClients(request).getProposalClient().getLatestContestPhaseInContest(proposalId).getContestPhasePK();
            }
        } catch (NumberFormatException  ignored) {
        }
        return phaseId;
    }

    @Override
    public boolean getCanSeeAddCommentButton() {
        boolean canSeeAddCommentButton = false;
        boolean isEvaluationTabActive = discussionTabName != null
                && discussionTabName.equals(ProposalTab.EVALUATION.name());

        if (isEvaluationTabActive) {
            boolean isIdsInitialized = proposalId != null && contestPhaseId != null;
            if (isIdsInitialized) {
                canSeeAddCommentButton = isAllowedToAddCommentsToProposalEvaluationInContestPhase();
            }
        } else {
            canSeeAddCommentButton = true;
        }
        return canSeeAddCommentButton;
    }

    @Override
    public boolean getCanAdminMessage(Comment comment) {
        if (comment.getAuthorId() == currentUser.getUserId() && proposalId != null) {
            try {
                Proposal proposal = ProposalsContextUtil.getClients(request).getProposalClient().getProposal(proposalId);
                ProposalWrapper proposalWrapper = new ProposalWrapper(proposal);

                return proposalWrapper.isUserAmongFellows(currentMember) || getCanAdminAll();
            } catch (ProposalNotFoundException ignored) {
            }
        }
        return comment.getAuthorId() == currentUser.getUserId() || getCanAdminAll();
    }

    private boolean isAllowedToAddCommentsToProposalEvaluationInContestPhase() {

        boolean isUserAllowed = false;
        try {
            Proposal proposal = ProposalsContextUtil.getClients(request).getProposalClient().getProposal(proposalId);
            isUserAllowed = isUserFellowOrJudgeOrAdvisor(proposal)
                    || isUserProposalAuthorOrTeamMember(proposal)
                    || getCanAdminAll();
        } catch (ProposalNotFoundException ignored) {
        }
        return isUserAllowed;
    }

    private boolean isUserFellowOrJudgeOrAdvisor(Proposal proposal) {

        try {
            ContestPhase contestPhase = ContestClientUtil.getContestPhase(contestPhaseId);
            ProposalWrapper proposalWrapper = new ProposalWrapper(proposal, contestPhase);


            ContestWrapper contestWrapper = new ContestWrapper(proposalWrapper.getContest());

            boolean isJudge = proposalWrapper.isUserAmongSelectedJudge(
                    MembersClient.getMemberUnchecked(currentMember.getUserId()));
            boolean isFellow = proposalWrapper.isUserAmongFellows(currentMember);
            boolean isAdvisor = contestWrapper.isUserAmongAdvisors(currentMember);

            return isFellow || isJudge || isAdvisor;
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw new InternalException(e);
        }

    }

    private boolean isUserProposalAuthorOrTeamMember(Proposal proposal) {
        boolean isAuthor = proposal.getAuthorId() == currentMember.getUserId();
        boolean isMember = MembersClient.isUserInGroup(currentMember.getUserId(), proposal.getProposalId());

        return isAuthor || isMember;
    }
}
