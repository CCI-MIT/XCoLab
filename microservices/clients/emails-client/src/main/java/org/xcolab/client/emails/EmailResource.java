package org.xcolab.client.emails;

import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.http.client.enums.ResourceEnum;

public enum EmailResource implements ResourceEnum {
    EMAIL("emails");

    private final String resourceName;

    EmailResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.EMAIL;
    }
}
