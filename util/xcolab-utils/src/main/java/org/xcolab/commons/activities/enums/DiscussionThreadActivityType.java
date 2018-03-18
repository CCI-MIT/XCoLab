package org.xcolab.commons.activities.enums;

public enum DiscussionThreadActivityType implements ActivityType {

    CREATED,
    COMMENT_ADDED,
    ;

    @Override
    public ActivityCategory getCategory() {
        return ActivityCategory.DISCUSSION;
    }
}
