package org.xcolab.client.activities.enums;

public enum MemberActivityType implements ActivityType {
    REGISTERED, //MemberJoinedActivityEntry(4,10038L,  1L),
    ;

    @Override
    public ActivityCategory getCategory() {
        return ActivityCategory.MEMBER;
    }
}
