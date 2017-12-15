package org.xcolab.util.enums.activity;

public enum ActivityEntryType {

    MEMBER(10038L),
    DISCUSSION(39202L),
    PROPOSAL(1368503L),
    CONTEST(39701L);

    private final Long primaryTypeId;

    ActivityEntryType(Long type) {
        this.primaryTypeId = type;
    }

    public Long getPrimaryTypeId(){
        return this.primaryTypeId;
    }
}
