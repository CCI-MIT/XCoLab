package org.xcolab.client.proposals.enums;

public enum ImpactSeriesType {
    BAU,
    IMPACT_REDUCTION,
    IMPACT_ADOPTION_RATE;

    public String getAttributeName(int year) {
        return name() + "_" + year;
    }
}
