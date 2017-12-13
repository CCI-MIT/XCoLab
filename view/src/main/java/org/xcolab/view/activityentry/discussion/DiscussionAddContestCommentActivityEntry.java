package org.xcolab.view.activityentry.discussion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activities.enums.ActivityType;
import org.xcolab.client.activities.enums.ContestActivityType;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class DiscussionAddContestCommentActivityEntry extends DiscussionBaseActivityEntry {

    private static final String MESSAGE_CODE = "activities.discussion.discussionaddcontest.message";

    @Autowired
    public DiscussionAddContestCommentActivityEntry(
            ResourceMessageResolver resourceMessageResolver) {
        super(resourceMessageResolver);
    }

    @Override
    public ActivityType getActivityType() {
        return ContestActivityType.COMMENT_ADDED;
    }

    @Override
    public String getBody() {
        String[] params = {getUserLink(), "Contest", getContestLink()};
        return resourceMessageResolver.getLocalizedMessage(MESSAGE_CODE, params);
    }

    @Override
    public String getTitle() {
        return getUserLink() + " added a comment to contest";
    }

    @Override
    public String getName() {
        return "Comment to contest";
    }
}

