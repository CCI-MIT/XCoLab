package org.xcolab.view.pages.proposals.impact.adaptation;

public enum AdaptationCategory {
    ECONOMIC_DAMAGES("Economic damages", "$",
            "Total economic damage as often referred to in ‘damage summaries’ of hurricanes and similar extreme weather events."),
    FATALITIES("Fatalities", "ppl",
            "Number of human lives lost due to direct or indirect causes."),
    SEVERELY_AFFECTED("People severely affected", "ppl",
            "Number of humans affected by long-lasting or permanent loss of home, restriction to food access with risk of starvation, major injuries or sickness, long-lasting or permanent job loss or loss of income."),
    AFFECTED("People affected", "ppl",
            "Number of humans affected temporary loss of heat/electricity without danger to life, temporary restriction to food access (without risk of starvation), temporary loss of home (days to weeks), mild injuries or sickness from which full recovery follows within weeks, temporary job loss or loss of income."),
    CULTURAL_DAMAGES("Cultural damages","km<sup>2</sup>",
            "Total area of valuable ecosystems and culturally important sites affected by the event. Can include all UNESCO world heritage sites and similar sites. Can include loss of area attractive for tourism.")
    ;

    private final String title;
    private final String unit;
    private final String tooltip;

    AdaptationCategory(String title, String unit, String tooltip) {
        this.title = title;
        this.unit = unit;
        this.tooltip = tooltip;
    }

    public String getUnit() {
        return unit;
    }

    public String getTitle() {
        return title;
    }

    public String getTooltip() {
        return tooltip;
    }

    public String getAttributeName() {
        return "IMPACT_" + name();
    }
}
