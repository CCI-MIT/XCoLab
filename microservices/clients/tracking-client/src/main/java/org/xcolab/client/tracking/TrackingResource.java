package org.xcolab.client.tracking;

import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.http.client.enums.ResourceEnum;

public enum TrackingResource implements ResourceEnum {
    TRACKED_VISITS("trackedVisits"),
    LOCATIONS("locations");

    private final String resourceName;

    TrackingResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.TRACKING;
    }
}
