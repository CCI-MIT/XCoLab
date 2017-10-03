package org.xcolab.view.activityentry.discussion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class DiscussionAddProposalCommentActivityEntry extends DiscussionBaseActivityEntry {

    private static final String MESSAGE_CODE = "activities.discussion.discussionaddproposal.message";

    @Autowired
    public DiscussionAddProposalCommentActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public Long getSecondaryType() {
        return DiscussionActivitySubType.DISCUSSION_PROPOSAL_COMMENT.getSecondaryTypeId();
    }

    @Override
    public String getBody() {
        String[] params = {getUserLink(), "Proposal", getProposalLink()};
        return resourceMessageResolver.getLocalizedMessage(MESSAGE_CODE, params);
    }

    @Override
    public String getTitle() {
        return getUserLink() + " added a comment to proposal";
    }

    @Override
    public String getName() {
        return "Comment to proposal";
    }
}
