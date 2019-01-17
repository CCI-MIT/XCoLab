package org.xcolab.view.pages.proposals.discussion;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.contest.pojo.Proposal;
import org.xcolab.client.contest.pojo.ProposalTeamMember;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;

import javax.servlet.http.HttpServletRequest;

public class ProposalDiscussionPermissions extends DiscussionPermissions {

    private final String discussionTabName;
    private final Proposal proposal;

    public ProposalDiscussionPermissions(HttpServletRequest request, Proposal proposal) {
        super();
        this.discussionTabName = getTabName(request);
        this.proposal = proposal;
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
        if (isReadOnly()) {
            return false;
        }

        boolean isEvaluationTab = ProposalTab.EVALUATION.name().equals(discussionTabName);
        if (isEvaluationTab) {
            return isLoggedIn && isAllowedToAddCommentsToProposalEvaluationInContestPhase();
        }
        return super.getCanSeeAddCommentButton();
    }

    @Override
    public boolean getCanAddComment() {
        if (isReadOnly()) {
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
        Contest contest =  proposal.getContest();

        boolean isJudge = proposal.getIsUserAmongSelectedJudges(userId);
        boolean isFellow = proposal.isUserAmongFellows(userId);
        boolean isAdvisor = contest.isUserAmongAdvisors(userId);

        return isFellow || isJudge || isAdvisor;
    }

    private boolean isUserProposalAuthorOrTeamMember() {
        boolean isAuthor = proposal.getAuthorUserId() == userId;
        boolean isMember = proposal.getMembers().stream()
                .map(ProposalTeamMember::getUserId)
                .anyMatch(id -> id == userId);

        return isAuthor || isMember;
    }

    private boolean isReadOnly() {
        boolean isReadOnly = ConfigurationAttributeKey.PROPOSALS_COMMENTS_READ_ONLY.get();
        if (isReadOnly) {
            return getCanAdminAll();
        }

        isReadOnly = proposal.getContest().getReadOnlyComments();
        if (isReadOnly && (isUserFellowOrJudgeOrAdvisor() || getCanAdminAll())) {
            return false;
        }
        return isReadOnly;
    }
}
