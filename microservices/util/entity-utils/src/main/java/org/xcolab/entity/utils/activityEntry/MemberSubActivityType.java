package org.xcolab.entity.utils.activityEntry;

public enum MemberSubActivityType{
    MEMBER_JOINED(1L);
    private final Long secondaryTypeId;
    MemberSubActivityType(Long type) {
        this.secondaryTypeId = type;
    }

    public Long getSecondaryTypeId(){
        return this.secondaryTypeId;
    }
}