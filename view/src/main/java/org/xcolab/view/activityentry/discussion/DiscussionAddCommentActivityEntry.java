package org.xcolab.view.activityentry.discussion;

import org.springframework.stereotype.Component;

@Component
public class DiscussionAddCommentActivityEntry extends DiscussionBaseActivityEntry {

    @Override
    public Long getSecondaryType() {
        return DiscussionActivitySubType.DISCUSSION_ADDED_COMMENT.getSecondaryTypeId();
    }

    @Override
    public String getBody() {


        String[] params = { getUserLink(), getThreadLink()};
        return resourceMessageResolver.getLocalizedMessage("activities.discussion.discussionaddcomment.message",params);


    }

    @Override
    public String getTitle() {
        return getUserLink() + " added a comment to thread";
    }

    @Override
    public String getName() {
        return "Comment to thread";
    }
}
