package org.xcolab.client.sharedcolab;

import org.xcolab.commons.http.client.CoLabService;
import org.xcolab.commons.http.client.enums.ResourceEnum;

public enum SharedUserResource implements ResourceEnum {
    USERS("members");

    private final String resourceName;

    SharedUserResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.MEMBER;
    }
}
