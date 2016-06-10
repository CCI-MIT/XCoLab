package org.xcolab.service.search.enums;

public enum SearchType {
    MEMBER(10038l),
    DISCUSSION(39202l),
    PROPOSOSAL(1368503l),
    CONTEST(39701l);

    private Long id;

    SearchType(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }
}
