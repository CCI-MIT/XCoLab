package org.xcolab.client.filtering.enums;

public enum FilteringStatus {

    CREATED(0),
    REQUEST_SENT(1),
    RESPONSE_RECEIVED(2),
    APPROVED(3),
    REJECTED(4);

    private final Integer id;

    FilteringStatus(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }
}
