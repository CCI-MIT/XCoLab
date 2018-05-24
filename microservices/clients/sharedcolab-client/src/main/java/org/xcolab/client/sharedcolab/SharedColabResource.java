package org.xcolab.client.sharedcolab;

import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.http.client.enums.ResourceEnum;

public enum SharedColabResource implements ResourceEnum {
    CONTESTS("contests");

    private final String resourceName;

    SharedColabResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.SHARED;
    }
}
