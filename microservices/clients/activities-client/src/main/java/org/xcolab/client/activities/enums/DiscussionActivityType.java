package org.xcolab.client.activities.enums;

public enum DiscussionActivityType implements ActivityType {

    THREAD_ADDED,
    COMMENT_ADDED,
    ;

    @Override
    public ActivityCategory getCategory() {
        return ActivityCategory.DISCUSSION;
    }
}
