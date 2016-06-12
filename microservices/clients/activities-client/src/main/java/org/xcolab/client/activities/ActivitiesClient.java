package org.xcolab.client.activities;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.activities.exceptions.ActivityEntryNotFoundException;
import org.xcolab.client.activities.exceptions.ActivitySubscriptionNotFoundException;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.util.RequestUtils;
import org.xcolab.util.exceptions.EntityNotFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class ActivitiesClient {

    private static final String EUREKA_APPLICATION_ID =
            "localhost:" + RequestUtils.getServicesPort() + "/activities-service";

    public static ActivityEntry createActivityEntry(ActivityEntry activityEntry) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/activityEntries");
        return RequestUtils.post(uriBuilder, activityEntry, ActivityEntry.class);
    }

    public static ActivityEntry getActivityEntry(Long activityEntryId)
            throws ActivityEntryNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/activityEntries/" + activityEntryId + "");
        try {
            return RequestUtils
                    .get(uriBuilder, ActivityEntry.class, "activityEntryId_" + activityEntryId);
        } catch (EntityNotFoundException e) {
            throw new ActivityEntryNotFoundException(
                    "ActivityEntry with id " + activityEntryId + " not found.");
        }
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

    public static List<ActivityEntry> getActivityEntriesAfter(Date afterDate) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/activityEntries");

        if (afterDate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        uriBuilder.queryParam("activitiesAfter", sdf.format(afterDate));

        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<ActivityEntry>>() {
                });
    }

    public static Integer countActivities(Long memberId, List<Long> memberIdsToExclude) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder
                        .fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/activityEntries/count");
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

    public static ActivitySubscription getActivitySubscription(Long activitySubscriptionId)
            throws ActivitySubscriptionNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/activitySubscriptions/" + activitySubscriptionId + "");
        try {
            return RequestUtils.get(uriBuilder, ActivitySubscription.class,
                    "activitySubscriptionId_" + activitySubscriptionId);
        } catch (EntityNotFoundException e) {
            throw new ActivitySubscriptionNotFoundException(
                    "ActivitySubscription with id " + activitySubscriptionId + " not found.");
        }
    }

    private static ActivitySubscription createActivitySubscription(
            ActivitySubscription activitySubscription) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/activitySubscriptions/");
        return RequestUtils.post(uriBuilder, activitySubscription, ActivitySubscription.class);
    }

    public static void deleteSubscription(Long pk) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/activitySubscriptions/" + pk);
        RequestUtils.delete(uriBuilder);
    }

    public static ActivitySubscription addSubscription(Long classNameId, Long classPK, Integer type,
            String extraInfo, Long receiverId) {

        ActivitySubscription activitySubscription = new ActivitySubscription();
        activitySubscription.setClassNameId(classNameId);
        activitySubscription.setClassPK(classPK);
        activitySubscription.setType_(type);
        activitySubscription.setExtraData(extraInfo);
        activitySubscription.setReceiverId(receiverId);

        return createActivitySubscription(activitySubscription);

    }

    public static void deleteSubscription(Long receiverId, Long classNameId, Long classPK,
            Integer type, String extraInfo) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/activitySubscriptions/deleteIfSubscribed")
                .queryParam("receiverId", receiverId)
                .queryParam("classNameId", classNameId)
                .queryParam("classPK", classPK)
                .queryParam("extraInfo", extraInfo)
                .queryParam("type", type);
        RequestUtils.getUnchecked(uriBuilder, Boolean.class);
    }

    public static void deleteSubscriptionById(Long subscriptionId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/activitySubscriptions/" + subscriptionId + "");
        RequestUtils.delete(uriBuilder);
    }

    public static boolean isSubscribedToActivity(Long receiverId, Long classNameId, Long classPK,
            Integer type, String extraInfo) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/activitySubscriptions/isSubscribed")
                .queryParam("receiverId", receiverId)
                .queryParam("classNameId", classNameId)
                .queryParam("classPK", classPK)
                .queryParam("extraInfo", extraInfo)
                .queryParam("type", type);
        return RequestUtils.getUnchecked(uriBuilder, Boolean.class);
    }

    public static List<ActivitySubscription> getActivitySubscription(Long classNameId, Long classPK,
            Long receiverId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/activitySubscriptions/");
        uriBuilder.queryParam("classNameId", classNameId);
        uriBuilder.queryParam("classPK", classPK);
        uriBuilder.queryParam("receiverId", receiverId);

        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<ActivitySubscription>>() {
                });
    }

}
