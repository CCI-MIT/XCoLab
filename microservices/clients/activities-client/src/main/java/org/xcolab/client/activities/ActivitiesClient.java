package org.xcolab.client.activities;

import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.util.RequestUtils;

public final class ActivitiesClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/activities-service";

    public static ActivityEntry createActivityEntry(ActivityEntry activityEntry) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/activityEntries/");
        return RequestUtils.post(uriBuilder, activityEntry, ActivityEntry.class);
    }
}
