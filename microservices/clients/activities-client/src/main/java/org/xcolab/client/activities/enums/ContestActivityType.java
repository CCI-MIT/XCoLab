package org.xcolab.client.activities.enums;

public enum ContestActivityType implements ActivitySubType {

    ADD_COMMENT, //DiscussionAddContestCommentActivityEntry(15,39202L, 6L),
    ;


    @Override
    public ActivityType getParentType() {
        return ActivityType.CONTEST;
    }
}
