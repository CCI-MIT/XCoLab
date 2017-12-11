package org.xcolab.client.activities.enums;

public enum MemberActivityType implements ActivitySubType {
    REGISTERED, //MemberJoinedActivityEntry(4,10038L,  1L),
    ;

    @Override
    public ActivityType getParentType() {
        return ActivityType.MEMBER;
    }
}
