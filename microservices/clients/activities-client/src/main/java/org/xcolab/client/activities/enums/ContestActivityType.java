package org.xcolab.client.activities.enums;

public enum ContestActivityType implements ActivityType {

    ADD_COMMENT, //DiscussionAddContestCommentActivityEntry(15,39202L, 6L),
    ;


    @Override
    public ActivityCategory getCategory() {
        return ActivityCategory.CONTEST;
    }
}
