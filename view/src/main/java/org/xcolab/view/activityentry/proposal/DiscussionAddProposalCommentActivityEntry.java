package org.xcolab.view.activityentry.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.util.activities.enums.ActivityType;
import org.xcolab.util.activities.enums.ProposalActivityType;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class DiscussionAddProposalCommentActivityEntry extends ProposalBaseActivityEntry {

    private static final String MESSAGE_CODE = "activities.discussion.discussionaddproposal.message";

    @Autowired
    public DiscussionAddProposalCommentActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public ActivityType getActivityType() {
        return ProposalActivityType.COMMENT_ADDED;
    }

    @Override
    protected String getTitleTemplate() {
        return "Comment added to <proposal/>";
    }

    @Override
    protected String getBodyTemplate() {
        return MESSAGE_CODE;
    }

    @Override
    protected String getProposalLinkUrl() {
        return super.getProposalLinkUrl() + "/tab/COMMENTS";
    }
}
