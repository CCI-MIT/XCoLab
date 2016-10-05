package org.xcolab.enums;

import com.liferay.portal.kernel.exception.PortalException;

public enum ContestTier {
    NONE(0L, "None"),
    BASIC(1L, "Tier I"),
    REGION_SECTOR(2L, "Tier II (region-sector)"),
    REGION_AGGREGATE(3L, "Tier III (region)"),
    GLOBAL(4L, "Tier IV (global)");

    public long getTierType() {
        return tierType;
    }

    private final long tierType;

    public String getTierName() {
        return tierName;
    }

    private final String tierName;

    ContestTier (long tierType, String tierName) {
        this.tierType = tierType;
        this.tierName = tierName;
    }

    public static ContestTier getContestTierByTierType(Long tierType)  {
        for (ContestTier contestTier : ContestTier.values()) {
            if (contestTier.getTierType() == tierType) {
                return contestTier;
            }
        }
        return null;
    }
}
