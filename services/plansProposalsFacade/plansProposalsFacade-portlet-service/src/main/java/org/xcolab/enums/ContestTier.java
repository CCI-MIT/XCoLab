package org.xcolab.enums;

/**
 * Created by kmang on 17/02/15.
 */
public enum ContestTier {
    NONE(0L, "None"),
    BASIC(1L, "Basic contest"),
    REGION_SECTOR(2L, "Region-sector contest"),
    REGION_AGGREGATE(3L, "Region-aggregate contest"),
    GLOBAL(4L, "Global contest");

    public long getTierType() {
        return tierType;
    }

    private long tierType;

    public String getTierName() {
        return tierName;
    }

    private String tierName;

    ContestTier (long tierType, String tierName) {
        this.tierType = tierType;
        this.tierName = tierName;
    }

    public static ContestTier getContestTierByTierType(Long tierType) {
        for (ContestTier contestTier : ContestTier.values()) {
            if (contestTier.getTierType() == tierType) {
                return contestTier;
            }
        }

        return null;
    }
}
