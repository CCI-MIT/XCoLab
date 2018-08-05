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

    public ProposalDiscussionPermissions(HttpServletRequest request,
            ProposalContext proposalContext) {
        super(request);
        this.proposalContext = proposalContext;
        this.discussionTabName = getTabName(request);
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

    @Override
    public boolean getCanSeeAddCommentButton() {
        boolean isEvaluationTab = ProposalTab.EVALUATION.name().equals(discussionTabName);
        if (isEvaluationTab) {
            return isLoggedIn && isAllowedToAddCommentsToProposalEvaluationInContestPhase();
        }
        return super.getCanSeeAddCommentButton();
    }

    private boolean isAllowedToAddCommentsToProposalEvaluationInContestPhase() {
        try {
            Proposal proposal = proposalContext.getProposal();
            return isUserFellowOrJudgeOrAdvisor(proposal)
                    || isUserProposalAuthorOrTeamMember(proposal)
                    || getCanAdminAll();
        } catch (ProposalNotFoundException ignored) {
            return false;
        }
    }

    private boolean isUserFellowOrJudgeOrAdvisor(Proposal proposal) {
        ContestPhase contestPhase = proposalContext.getContestPhase();
        Proposal proposalWrapper = new Proposal(proposal, contestPhase);

        Contest contestWrapper =  proposalWrapper.getContest();

        boolean isJudge = proposalWrapper.getIsUserAmongSelectedJudges(userId);
        boolean isFellow = proposalWrapper.isUserAmongFellows(userId);
        boolean isAdvisor = contestWrapper.isUserAmongAdvisors(userId);

        return isFellow || isJudge || isAdvisor;
    }

    private boolean isUserProposalAuthorOrTeamMember(Proposal proposal) {
        boolean isAuthor = proposal.getauthorUserid() == userId;
        boolean isMember = UsersGroupsClientUtil.isMemberInGroup(userId, proposal.getProposalId());

        return isAuthor || isMember;
    }
}
