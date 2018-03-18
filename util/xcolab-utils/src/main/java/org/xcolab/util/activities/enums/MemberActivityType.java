package org.xcolab.util.activities.enums;

public enum MemberActivityType implements ActivityType {

    REGISTERED,
    ;

    @Override
    public ActivityCategory getCategory() {
        return ActivityCategory.MEMBER;
    }
}
