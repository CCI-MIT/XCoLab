package org.xcolab.client.search.pojo;


public class SearchPojo {

    private Double relevance;

    private Long classPrimaryKey;

    private Long searchTypeId;


    public Double getRelevance() {
        return relevance;
    }

    public void setRelevance(Double relevance) {
        this.relevance = relevance;
    }

    public Long getClassPrimaryKey() {
        return classPrimaryKey;
    }

    public void setClassPrimaryKey(Long classPrimaryKey) {
        this.classPrimaryKey = classPrimaryKey;
    }

    public Long getSearchTypeId() {
        return searchTypeId;
    }

    public void setSearchTypeId(Long searchType) {
        searchTypeId = searchType;
    }
}
