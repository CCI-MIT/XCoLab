package org.xcolab.view.pages.proposals.discussion;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.UsersGroupsClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;

import javax.servlet.http.HttpServletRequest;

public class ProposalDiscussionPermissions extends DiscussionPermissions {

    private final String discussionTabName;
    private final ProposalContext proposalContext;
    private  Long proposalId;
    private  Long contestPhaseId;

    public ProposalDiscussionPermissions(HttpServletRequest request,
            ProposalContext proposalContext) {
        super(request);
        this.proposalContext = proposalContext;
        this.discussionTabName = getTabName(request);
    }

    public void setProposalId(Long pId,Long cPId ){
        proposalId = pId;
        contestPhaseId = cPId;
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

    @Override
    public boolean getCanSeeAddCommentButton() {
        boolean canSeeAddCommentButton = false;
        boolean isEvaluationTabActive = discussionTabName != null
                && discussionTabName.equals(ProposalTab.EVALUATION.name());

        if (isEvaluationTabActive) {
            boolean isIdsInitialized = proposalId != null && contestPhaseId != null;
            if (isIdsInitialized) {
                if (isLoggedIn) {
                    canSeeAddCommentButton =
                            isAllowedToAddCommentsToProposalEvaluationInContestPhase();
                } else {
                    return false;
                }
            }
        } else {
            canSeeAddCommentButton = super.getCanSeeAddCommentButton();
        }
        return canSeeAddCommentButton;
    }

    private boolean isAllowedToAddCommentsToProposalEvaluationInContestPhase() {

        boolean isUserAllowed = false;
        try {
            Proposal proposal = proposalContext.getClients().getProposalClient().getProposal(proposalId);
            isUserAllowed = isUserFellowOrJudgeOrAdvisor(proposal)
                    || isUserProposalAuthorOrTeamMember(proposal)
                    || getCanAdminAll();
        } catch (ProposalNotFoundException ignored) {
        }
        return isUserAllowed;
    }

    private boolean isUserFellowOrJudgeOrAdvisor(Proposal proposal) {
        ContestPhase contestPhase = proposalContext.getClients().getContestClient().getContestPhase(contestPhaseId);
        Proposal proposalWrapper = new Proposal(proposal, contestPhase);


        Contest contestWrapper =  proposalWrapper.getContest();

        boolean isJudge = proposalWrapper.getIsUserAmongSelectedJudges(memberId);
        boolean isFellow = proposalWrapper.isUserAmongFellows(memberId);
        boolean isAdvisor = contestWrapper.isUserAmongAdvisors(memberId);

        return isFellow || isJudge || isAdvisor;
    }

    private boolean isUserProposalAuthorOrTeamMember(Proposal proposal) {
        boolean isAuthor = proposal.getAuthorId() == memberId;
        boolean isMember = UsersGroupsClientUtil.isMemberInGroup(memberId, proposal.getProposalId());

        return isAuthor || isMember;
    }
}
