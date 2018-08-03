package org.xcolab.view.pages.proposals.discussion;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
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
    private final Proposal proposal;

    public ProposalDiscussionPermissions(HttpServletRequest request,
            ProposalContext proposalContext) {
        super(request);
        this.proposalContext = proposalContext;
        this.discussionTabName = getTabName(request);
        proposal = proposalContext.getProposal();
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
        final Boolean isReadyOnly = ConfigurationAttributeKey.PROPOSALS_COMMENTS_READ_ONLY.get();
        if (isReadyOnly) {
            return getCanAdminAll();
        }

        boolean isEvaluationTab = ProposalTab.EVALUATION.name().equals(discussionTabName);
        if (isEvaluationTab) {
            return isLoggedIn && isAllowedToAddCommentsToProposalEvaluationInContestPhase();
        }
        return super.getCanSeeAddCommentButton();
    }

    @Override
    public boolean getCanAddComment() {
        final Boolean isReadyOnly = ConfigurationAttributeKey.PROPOSALS_COMMENTS_READ_ONLY.get();
        if (isReadyOnly && !getCanAdminAll()) {
            return false;
        }

        boolean isEvaluationTab = ProposalTab.EVALUATION.name().equals(discussionTabName);
        if (isEvaluationTab && !isAllowedToAddCommentsToProposalEvaluationInContestPhase()) {
            return false;
        }

        return super.getCanAddComment();
    }

    private boolean isAllowedToAddCommentsToProposalEvaluationInContestPhase() {
        try {
            return isUserFellowOrJudgeOrAdvisor()
                    || isUserProposalAuthorOrTeamMember()
                    || getCanAdminAll();
        } catch (ProposalNotFoundException ignored) {
            return false;
        }
    }

    private boolean isUserFellowOrJudgeOrAdvisor() {
        ContestPhase contestPhase = proposalContext.getContestPhase();
        Proposal proposalWrapper = new Proposal(proposal, contestPhase);

        Contest contestWrapper =  proposalWrapper.getContest();

        boolean isJudge = proposalWrapper.getIsUserAmongSelectedJudges(memberId);
        boolean isFellow = proposalWrapper.isUserAmongFellows(memberId);
        boolean isAdvisor = contestWrapper.isUserAmongAdvisors(memberId);

        return isFellow || isJudge || isAdvisor;
    }

    private boolean isUserProposalAuthorOrTeamMember() {
        boolean isAuthor = proposal.getAuthorId() == memberId;
        boolean isMember = UsersGroupsClientUtil.isMemberInGroup(memberId, proposal.getProposalId());

        return isAuthor || isMember;
    }
}
