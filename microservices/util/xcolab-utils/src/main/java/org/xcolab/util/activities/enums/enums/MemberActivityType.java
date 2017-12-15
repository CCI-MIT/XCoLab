package org.xcolab.util.activities.enums.enums;

public enum MemberActivityType implements ActivityType {

    REGISTERED,
    ;

    @Override
    public ActivityCategory getCategory() {
        return ActivityCategory.MEMBER;
    }
}
