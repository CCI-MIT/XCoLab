package org.xcolab.view.activityentry.discussion;

import org.springframework.stereotype.Component;

@Component
public class DiscussionAddProposalCommentActivityEntry extends DiscussionBaseActivityEntry {



    @Override
    public Long getSecondaryType() {
        return DiscussionActivitySubType.DISCUSSION_PROPOSAL_COMMENT.getSecondaryTypeId();
    }

    @Override
    public String getBody() {

        String [] params = { getUserLink(), "Proposal", getProposalLink()};
        return resourceMessageResolver.getLocalizedMessage("activities.discussion.discussionaddproposal.message",params);

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
