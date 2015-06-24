package org.xcolab.portlets.proposals.utils;

/**
 * Created by Thomas on 6/22/2015.
 */
public enum RegionClimateImpact {
    US("US", 0.1595),
    EU("EU", 0.1140),
    CHINA("China", 0.2834),
    INDIA("India", 0.0691),
    OTHER_DEVELOPING("Other developing", 0.2828),
    OTHER_DEVELOPED("Other developed", 0.0912)
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
