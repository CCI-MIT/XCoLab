package org.xcolab.client.filtering.enums;

public enum FilteringSource {
    PROPOSAL("PROPOSAL", 1l),
    DISCUSSION("DISCUSSION",2l);

    private String filteringString;
    private Long id;

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
