package org.xcolab.client.balloons;

import org.xcolab.commons.http.client.CoLabService;
import org.xcolab.commons.http.client.enums.ResourceEnum;

public enum BalloonResource implements ResourceEnum {

    BALLOON_LINK("balloonLinks"),
    BALLOON_TEXT("balloonTexts"),
    BALLOON_USER_TRACKING("balloonUserTrackings");

    private final String resourceName;

    BalloonResource(String resourceName) {
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
