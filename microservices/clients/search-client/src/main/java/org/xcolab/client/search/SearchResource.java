package org.xcolab.client.search;

import org.xcolab.commons.http.client.CoLabService;
import org.xcolab.commons.http.client.enums.ResourceEnum;

public enum SearchResource implements ResourceEnum {
    SEARCH("search");

    private final String resourceName;

    SearchResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.SEARCH;
    }
}
