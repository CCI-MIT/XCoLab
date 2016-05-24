package org.xcolab.client.activities;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.util.RequestUtils;
import org.xcolab.util.exceptions.EntityNotFoundException;

import java.util.List;

public final class ActivitiesClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/activities-service";

    public static ActivityEntry createActivityEntry(ActivityEntry activityEntry) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/activityEntries");
        return RequestUtils.post(uriBuilder, activityEntry, ActivityEntry.class);
    }
    public static List<ActivityEntry> getActivityEntries(Integer startRecord,
                                                                        Integer limitRecord, Long memberId, List<Long> memberIdsToExclude) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/activityEntries");

        if (startRecord != null) {
            uriBuilder.queryParam("startRecord", startRecord);
        }
        if (limitRecord != null) {
            uriBuilder.queryParam("limitRecord", limitRecord);
        }
        if (memberId != null) {
            uriBuilder.queryParam("memberId", memberId);
        }
        if (memberIdsToExclude != null) {
            uriBuilder.queryParam("folderId", memberIdsToExclude);
        }

        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<ActivityEntry>>() {
                });
    }

    public static Integer countActivities(Long memberId, List<Long> memberIdsToExclude) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/activityEntries/count");
        if (memberId != null) {
            uriBuilder.queryParam("memberId", memberId);
        }
        if (memberIdsToExclude != null) {
            uriBuilder.queryParam("folderId", memberIdsToExclude);
        }
        try {
            return RequestUtils.get(uriBuilder, Integer.class, "activities_count_");
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }
}
