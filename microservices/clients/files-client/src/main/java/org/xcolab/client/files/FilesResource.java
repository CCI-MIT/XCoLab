package org.xcolab.client.files;

import org.xcolab.commons.http.client.CoLabService;
import org.xcolab.commons.http.client.enums.ResourceEnum;

public enum FilesResource implements ResourceEnum {
    FILE_ENTRY("fileEntries");

    private final String resourceName;

    FilesResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.CONTENT;
    }
}
