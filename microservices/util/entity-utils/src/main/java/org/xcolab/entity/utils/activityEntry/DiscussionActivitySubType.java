package org.xcolab.entity.utils.activityEntry;

public enum DiscussionActivitySubType {
    DISCUSSION_PROPOSAL_COMMENT(1L),
    DISCUSSION_CATEGORY_ADDED(2L),
    DISCUSSION_ADDED(3L),
    DISCUSSION_FORUM_COMMENT(4L),
    DISCUSSION_ADDED_COMMENT(5L);

    private final Long secondaryTypeId;
    DiscussionActivitySubType(Long type) {
        this.secondaryTypeId = type;
    }

    public Long getSecondaryTypeId(){
        return this.secondaryTypeId;
    }
}
