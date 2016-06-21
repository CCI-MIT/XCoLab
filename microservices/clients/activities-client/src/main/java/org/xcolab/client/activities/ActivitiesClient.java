package org.xcolab.client.activities;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.activities.exceptions.ActivityEntryNotFoundException;
import org.xcolab.client.activities.exceptions.ActivitySubscriptionNotFoundException;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class ActivitiesClient {

    private static final RestService activitiesService = new RestService("activities-service");
    private static final RestResource activityEntryResource =
            new RestResource(activitiesService, "activityEntries");
    private static final RestResource activitySubscriptionResource =
            new RestResource(activitiesService, "activitySubscriptions");

    public static ActivityEntry createActivityEntry(ActivityEntry activityEntry) {
        final UriBuilder uriBuilder = activityEntryResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, activityEntry, ActivityEntry.class);
    }

    public static ActivityEntry getActivityEntry(Long activityEntryId)
            throws ActivityEntryNotFoundException {
        final UriBuilder uriBuilder = activityEntryResource.getResourceUrl(activityEntryId);
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
        final UriBuilder uriBuilder = activityEntryResource.getResourceUrl()
                .optionalQueryParam("startRecord", startRecord)
                .optionalQueryParam("limitRecord", limitRecord)
                .optionalQueryParam("memberId", memberId)
                .optionalQueryParam("folderId", memberIdsToExclude);

        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<ActivityEntry>>() {
                });
    }

    public static List<ActivityEntry> getActivityEntriesAfter(Date afterDate) {
        final UriBuilder uriBuilder = activityEntryResource.getResourceUrl();

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
        final UriBuilder uriBuilder = activityEntryResource.getResourceUrl().path("/count")
                .optionalQueryParam("memberId", memberId)
                .optionalQueryParam("folderId", memberIdsToExclude);
        try {
            return RequestUtils.get(uriBuilder, Integer.class, "activities_count_");
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static ActivitySubscription getActivitySubscription(long activitySubscriptionId)
            throws ActivitySubscriptionNotFoundException {
        final UriBuilder uriBuilder = activitySubscriptionResource
                .getResourceUrl(activitySubscriptionId);
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
        final UriBuilder uriBuilder = activitySubscriptionResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, activitySubscription, ActivitySubscription.class);
    }

    public static void deleteSubscription(Long pk) {
        final UriBuilder uriBuilder = activitySubscriptionResource.getResourceUrl(pk);
        RequestUtils.delete(uriBuilder);
    }

    public static ActivitySubscription addSubscription(long memberId,
            ActivityEntryType activityEntryType, long classPK,
            String extraInfo) {
        final UriBuilder uriBuilder = activitySubscriptionResource.getResourceUrl()
                .path("/subscribe")
                .queryParam("receiverId", memberId)
                .queryParam("activityEntryType", activityEntryType)
                .queryParam("classPK", classPK)
                .queryParam("extraInfo", extraInfo);
        return RequestUtils.post(uriBuilder, null, ActivitySubscription.class);
    }

    public static void deleteSubscription(Long receiverId, ActivityEntryType activityEntryType,
            Long classPK, String extraInfo) {
        final UriBuilder uriBuilder = activitySubscriptionResource.getResourceUrl()
                .path("deleteIfSubscribed")
                .queryParam("receiverId", receiverId)
                .queryParam("activityEntryType", activityEntryType)
                .queryParam("classPK", classPK)
                .queryParam("extraInfo", extraInfo);
        RequestUtils.delete(uriBuilder);
    }

    public static void deleteSubscriptionById(Long subscriptionId) {
        final UriBuilder uriBuilder = activitySubscriptionResource.getResourceUrl(subscriptionId);
        RequestUtils.delete(uriBuilder);
    }

    public static boolean isSubscribedToActivity(Long receiverId, Long classNameId, Long classPK,
            Integer type, String extraInfo) {
        final UriBuilder uriBuilder = activitySubscriptionResource.getResourceUrl()
                .path("/isSubscribed")
                .queryParam("receiverId", receiverId)
                .queryParam("classNameId", classNameId)
                .queryParam("classPK", classPK)
                .queryParam("extraInfo", extraInfo)
                .queryParam("type", type);
        return RequestUtils.getUnchecked(uriBuilder, Boolean.class);
    }

    public static List<ActivitySubscription> getActivitySubscription(Long classNameId, Long classPK,
            Long receiverId) {
        final UriBuilder uriBuilder = activitySubscriptionResource.getResourceUrl()
                .queryParam("classNameId", classNameId)
                .queryParam("classPK", classPK)
                .queryParam("receiverId", receiverId);

        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<ActivitySubscription>>() {
                });
    }
}
