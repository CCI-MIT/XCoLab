package org.xcolab.client.activities;

import org.xcolab.commons.http.client.CoLabService;
import org.xcolab.commons.http.client.enums.ResourceEnum;

public enum ActivityResource implements ResourceEnum {

    ACTIVITY_ENTRY("activityEntries"),
    ACTIVITY_SUBSCRIPTION("activitySubscriptions");

    private final String resourceName;

    ActivityResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.ACTIVITY;
    }
}
