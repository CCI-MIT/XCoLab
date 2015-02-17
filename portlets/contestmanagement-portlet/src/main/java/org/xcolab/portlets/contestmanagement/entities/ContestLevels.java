package org.xcolab.portlets.contestmanagement.entities;

/**
 * Created by Thomas on 2/17/2015.
 */
public enum ContestLevels {
    BASIC(0, "Base"),
    REGION_SECTOR(1, "Region Sector"),
    REGION_AGGREGATE(2, "Region Aggregate"),
    GLOBAL(3, "Gobal");

    private final String displayName;
    private final int level;

    private ContestLevels (int level, String displayName) {
        this.level = level;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getLevel() {
        return level;
    }
}
