package org.xcolab.view.activityentry.contest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activities.enums.ActivityType;
import org.xcolab.client.activities.enums.ContestActivityType;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class DiscussionAddContestCommentActivityEntry extends ContestBaseActivityEntry {

    private static final String MESSAGE_CODE = "activities.discussion.discussionaddcontest.message";

    private final ResourceMessageResolver resourceMessageResolver;

    @Autowired
    public DiscussionAddContestCommentActivityEntry(
            ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
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
}

