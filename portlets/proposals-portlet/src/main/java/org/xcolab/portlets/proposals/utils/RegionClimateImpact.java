package org.xcolab.portlets.proposals.utils;

/**
 * Created by Thomas on 6/22/2015.
 */
public enum RegionClimateImpact {
    US("US", 0.19),
    EU("EU", 0.14),
    CHINA("CHINA", 0.15),
    INDIA("INDIA", 0.15),
    OTHER_DEVELOPING("OTHER_DEVELOPING", 0.1),
    OTHER_DEVELOPED("OTHER_DEVELOPED", 0.1)
    ;

    private final String regionNameUsedInRoma;
    private final double regionClimateImpactFactor;

    RegionClimateImpact(String region, double regionClimateImpactFactor) {
        this.regionNameUsedInRoma = region;
        this.regionClimateImpactFactor = regionClimateImpactFactor;
    }

    public String getRegionNameUsedInRoma() {
        return regionNameUsedInRoma;
    }

    public double getRegionClimateImpactFactor() {
        return regionClimateImpactFactor;
    }

}
