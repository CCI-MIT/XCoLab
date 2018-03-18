package org.xcolab.util.activities.enums;

public enum DiscussionThreadActivityType implements ActivityType {

    CREATED,
    COMMENT_ADDED,
    ;

    @Override
    public ActivityCategory getCategory() {
        return ActivityCategory.DISCUSSION;
    }
}
