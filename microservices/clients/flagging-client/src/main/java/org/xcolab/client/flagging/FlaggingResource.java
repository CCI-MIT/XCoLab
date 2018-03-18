package org.xcolab.client.flagging;

import org.xcolab.commons.http.client.CoLabService;
import org.xcolab.commons.http.client.enums.ResourceEnum;

public enum FlaggingResource implements ResourceEnum {
    REPORT("reports"),
    AGGREGATED_REPORT("aggregatedReports"),
    REPORT_TARGET("reportTargets")
    ;

    private final String resourceName;

    FlaggingResource(String resourceName) {
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
