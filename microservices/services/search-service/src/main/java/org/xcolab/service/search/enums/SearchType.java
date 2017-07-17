package org.xcolab.service.search.enums;

public enum SearchType {

    FULL_SITE(0L, "FULL_SITE"),
    CONTENT(1L, "CONTENT"),
    BLOG(2L, "BLOG"),
    MEMBER(10038L, "USER"),
    DISCUSSION(39202L, "DISCUSSION"),
    PROPOSAL(1368503L, "PROPOSAL"),
    CONTEST(39701L, "CONTEST");

    private final Long id;
    private final String searchType;

    SearchType(Long id, String searchType) {
        this.id = id;
        this.searchType = searchType;
    }

    public String getStringType(){
        return this.searchType;
    }
    public Long getId() {
        return id;
    }
}
