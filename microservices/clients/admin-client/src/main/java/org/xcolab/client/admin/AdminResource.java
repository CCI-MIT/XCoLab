package org.xcolab.client.admin;

import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.http.client.enums.ResourceEnum;

public enum AdminResource implements ResourceEnum {
    EMAIL_TEMPLATE("emailTemplates"),
    CONTEST_TYPE_ATTRIBUTE("contestTypeAttributes"),
    CONFIGURATION_ATTRIBUTE("attributes"),
    NOTIFICATIONS("notifications");

    private final String resourceName;

    AdminResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.ADMIN;
    }
}
