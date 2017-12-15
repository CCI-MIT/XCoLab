package org.xcolab.view.activityentry.discussion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activities.enums.ActivityType;
import org.xcolab.client.activities.enums.DiscussionThreadActivityType;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class DiscussionAddCommentActivityEntry extends DiscussionBaseActivityEntry {

    private static final String MESSAGE_CODE = "activities.discussion.discussionaddcomment.message";

    @Autowired
    public DiscussionAddCommentActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public ActivityType getActivityType() {
        return DiscussionThreadActivityType.COMMENT_ADDED;
    }

    @Override
    public String getBody() {
        String[] params = {getUserLink(), getThreadLink()};
        return resourceMessageResolver.getLocalizedMessage(MESSAGE_CODE, params);
    }

    @Override
    public String getTitle() {
        return "Comment added to discussion " + getThread().getTitle();
    }
}
