package org.xcolab.portlets.feeds;

public enum FeedType {
    ACTIVITIES("Activities"),
    RECENTLY_ACTIVE("Recently active users"),
    RECENTLY_JOINED("Recently joined users"),
    MOST_ACTIVE("Most active users");
    
    private final String description;
    
    FeedType(String description) {
        this.description = description;
    }
    
    public String getDescription() { return description; } 

}
