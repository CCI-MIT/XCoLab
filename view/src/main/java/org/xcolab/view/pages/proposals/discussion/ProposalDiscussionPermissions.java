package org.xcolab.view.pages.proposals.discussion;

import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.view.pages.proposals.wrappers.ProposalTab;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;

import javax.servlet.http.HttpServletRequest;

public class ProposalDiscussionPermissions extends DiscussionPermissions {

    private final String discussionTabName;
    private final Long proposalId;
    private final Long contestPhaseId;
    private final HttpServletRequest request;

    public ProposalDiscussionPermissions(HttpServletRequest request) {
        super(request);
        discussionTabName = getTabName(request);
        proposalId = getProposalId(request);
        contestPhaseId = getContestPhaseId(request);
        this.request = request;
    }

    private String getTabName(HttpServletRequest request) {
        String discussionTabName = request.getParameter("tab");
        if (discussionTabName == null) {
            final String pageToDisplay = request.getParameter("pageToDisplay");
            if (pageToDisplay != null && pageToDisplay.startsWith("proposalDetails_")) {
                discussionTabName = pageToDisplay.replace("proposalDetails_", "");
            }
        }
        return discussionTabName;
    }

    private Long getProposalId(HttpServletRequest request) {
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

    private Long getContestPhaseId(HttpServletRequest request) {
        Long phaseId = null;
        try {
            String contestPhaseIdParameter = request.getParameter("phaseId");
            if (contestPhaseIdParameter != null) {
                phaseId = Long.parseLong(contestPhaseIdParameter);
            } else if (proposalId != null && proposalId > 0) {
                phaseId = ProposalsContextUtil.getClients(request).getProposalClient().getLatestContestPhaseInProposal(proposalId).getContestPhasePK();
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
                if(isLoggedIn) {
                    canSeeAddCommentButton =
                            isAllowedToAddCommentsToProposalEvaluationInContestPhase();
                }else{
                    return false;
                }
            }
        } else {
            canSeeAddCommentButton = true;
        }
        return canSeeAddCommentButton;
    }

    @Override
    public boolean getCanAdminMessage(Comment comment) {
        if (comment.getAuthorId() == memberId && proposalId != null) {
            try {
                Proposal proposal = ProposalsContextUtil.getClients(request).getProposalClient().getProposal(proposalId);


                return proposal.isUserAmongFellows(memberId) || getCanAdminAll();
            } catch (ProposalNotFoundException ignored) {
            }
        }
        return comment.getAuthorId() == memberId || getCanAdminAll();
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
        ContestPhase contestPhase = ProposalsContextUtil.getClients(request).getContestClient().getContestPhase(contestPhaseId);
        Proposal proposalWrapper = new Proposal(proposal, contestPhase);


        Contest contestWrapper =  proposalWrapper.getContest();

        boolean isJudge = proposalWrapper.isUserAmongSelectedJudge(
                MembersClient.getMemberUnchecked(memberId));
        boolean isFellow = proposalWrapper.isUserAmongFellows(memberId);
        boolean isAdvisor = contestWrapper.isUserAmongAdvisors(memberId);

        return isFellow || isJudge || isAdvisor;
    }

    private boolean isUserProposalAuthorOrTeamMember(Proposal proposal) {
        boolean isAuthor = proposal.getAuthorId() == memberId;
        boolean isMember = MembersClient.isUserInGroup(memberId, proposal.getProposalId());

        return isAuthor || isMember;
    }
}
