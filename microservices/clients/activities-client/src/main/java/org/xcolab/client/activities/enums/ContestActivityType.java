package org.xcolab.client.activities.enums;

public enum ContestActivityType implements ActivityType {

    COMMENT_ADDED,
    ;

    @Override
    public ActivityCategory getCategory() {
        return ActivityCategory.CONTEST;
    }
}
