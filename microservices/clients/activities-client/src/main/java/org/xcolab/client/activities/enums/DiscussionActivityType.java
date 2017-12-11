package org.xcolab.client.activities.enums;

public enum DiscussionActivityType implements ActivitySubType {

    THREAD_ADDED, //DiscussionAddedActivityEntry(2,39202L, 3L),
    COMMENT_ADDED, //DiscussionAddCommentActivityEntry(1, 39202L, 5L),
    ;

    @Override
    public ActivityType getParentType() {
        return ActivityType.DISCUSSION;
    }
}
