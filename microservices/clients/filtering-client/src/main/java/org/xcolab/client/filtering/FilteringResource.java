package org.xcolab.client.filtering;

import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.http.client.enums.ResourceEnum;

public enum FilteringResource implements ResourceEnum {
    FILTERED_ENTRY("filteredEntries")
    ;

    private final String resourceName;

    FilteringResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.MODERATION;
    }
}
