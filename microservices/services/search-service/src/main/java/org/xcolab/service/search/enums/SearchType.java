package org.xcolab.service.search.enums;

public enum SearchType {
    FULL_SITE(0l, "FULL_SITE"),
    CONTENT(1l, "CONTENT"),
    BLOG(2l, "BLOG"),
    MEMBER(10038l, "USER"),
    DISCUSSION(39202l, "DISCUSSION"),
    PROPOSAL(1368503l, "PROPOSAL"),
    CONTEST(39701l, "CONTEST");

    private Long id;
    private String searchType;

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
