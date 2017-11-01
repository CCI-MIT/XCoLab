package org.xcolab.client.modeling;

import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.http.client.enums.ResourceEnum;

public enum ModelingResource implements ResourceEnum {

    MODELS("models"),
    MODEL_CATEGORIES("modelCategories"),
    MODEL_DISCUSSIONS("modelDiscussions"),
    MODEL_INPUT_GROUPS("modelInputGroups"),
    MODEL_INPUT_ITEMS("modelInputItems"),
    MODEL_OUTPUT_CHART_ORDERS("modelOutputChartOrders"),
    MODEL_OUTPUT_ITEMS("modelOutputItems"),
    MODEL_POSITIONS("modelPositions");

    private final String resourceName;

    ModelingResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.MODEL;
    }
}
