package org.xcolab.portlets.contestmanagement.entities;

/**
 * Created by Thomas on 2/17/2015.
 */
public enum ContestLevels {
    BASIC(0, "Tier I"),
    REGION_SECTOR(1, "Tier II (region-sector)"),
    REGION_AGGREGATE(2, "Tier III (region)"),
    GLOBAL(3, "Tier IV (global)");

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
