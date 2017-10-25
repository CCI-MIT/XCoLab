package org.xcolab.client.contest.resources;

import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.http.client.enums.ResourceEnum;

public enum ImpactResource implements ResourceEnum {
    IMPACT_ITERATION("impactIterations"),
    IMPACT_DEFAULT_SERIES("impactDefaultSeries"),
    IMPACT_DEFAULT_SERIES_DATA("impactDefaultSeriesDatas"),
    IMPACT_TEMPLATE_SERIES("impactTemplateSeries"),
    IMPACT_TEMPLATE_MAX_FOCUS_AREA("impactTemplateMaxFocusAreas"),
    IMPACT_TEMPLATE_FOCUS_AREA_LIST("impactTemplateFocusAreaLists");

    private final String resourceName;

    ImpactResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.CONTEST;
    }
}
