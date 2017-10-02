package org.xcolab.view.util.entity.activityEntry;

public enum MemberSubActivityType {
    MEMBER_JOINED(1L);
    private final Long secondaryTypeId;

    MemberSubActivityType(Long type) {
        this.secondaryTypeId = type;
    }

    public Long getSecondaryTypeId() {
        return this.secondaryTypeId;
    }
}
