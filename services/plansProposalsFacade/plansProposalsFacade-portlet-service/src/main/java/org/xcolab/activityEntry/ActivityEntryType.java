package org.xcolab.activityEntry;

public enum ActivityEntryType {
    MEMBER(10038l),
    DISCUSSION(39202l),
    PROPOSOSAL(1368503l);
    private final Long primaryTypeId;
    ActivityEntryType(Long type) {
        this.primaryTypeId = type;
    }

    public Long getPrimaryTypeId(){
        return this.primaryTypeId;
    }
}
