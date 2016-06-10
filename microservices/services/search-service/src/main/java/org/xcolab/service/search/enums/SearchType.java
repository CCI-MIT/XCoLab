package org.xcolab.service.search.enums;

public enum SearchType {
    FULL_SITE(0l, "FULL_SITE"),
    CONTENT(1l, "Content"),
    BLOG(2l, "Blog"),
    MEMBER(10038l, "Users"),
    DISCUSSION(39202l, "Discussions"),
    PROPOSAL(1368503l, "Proposals"),
    CONTEST(39701l, "Contests");

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
