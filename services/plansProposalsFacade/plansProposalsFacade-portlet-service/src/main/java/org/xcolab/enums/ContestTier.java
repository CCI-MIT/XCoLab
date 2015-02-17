package org.xcolab.enums;

/**
 * Created by kmang on 17/02/15.
 */
public enum ContestTier {
    NONE(0L),
    BASIC(1L),
    REGION_SECTOR(2L),
    REGION_AGGREGATE(3L),
    GLOBAL(4L);

    public long getTierType() {
        return tierType;
    }

    private long tierType;

    ContestTier (long tierType) {
        this.tierType = tierType;
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
