package org.xcolab.client.filtering.enums;

public enum FilteringSource {

    PROPOSAL("PROPOSAL", 1L),
    DISCUSSION("DISCUSSION", 2L);

    private final String filteringString;
    private final Long id;

    FilteringSource(String filteringString, Long id){
        this.filteringString = filteringString;
        this.id= id;
    }

    public String getSource(){
        return filteringString;
    }
    public Long getId(){
        return id;
    }
}
