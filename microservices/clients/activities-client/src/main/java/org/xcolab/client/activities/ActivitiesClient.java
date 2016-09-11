package org.xcolab.client.activities;

import org.xcolab.client.activities.exceptions.ActivityEntryNotFoundException;
import org.xcolab.client.activities.exceptions.ActivitySubscriptionNotFoundException;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class ActivitiesClient {

    private static final RestService activitiesService = new RestService("activities-service");
    private static final RestResource<ActivityEntry> activityEntryResource =
            new RestResource<>(activitiesService, "activityEntries", ActivityEntry.TYPES);
    private static final RestResource<ActivitySubscription> activitySubscriptionResource =
            new RestResource<>(activitiesService, "activitySubscriptions",
                    ActivitySubscription.TYPES);

    public static ActivityEntry createActivityEntry(ActivityEntry activityEntry) {
        return activityEntryResource.create(activityEntry).execute();
    }

    public static ActivityEntry getActivityEntry(Long activityEntryId)
            throws ActivityEntryNotFoundException {
        try {
            return activityEntryResource.get(activityEntryId)
                    .withCache(CacheKeys.of(ActivityEntry.class, activityEntryId),
                            CacheRetention.REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ActivityEntryNotFoundException(
                    "ActivityEntry with id " + activityEntryId + " not found.");
        }
    }

    public static List<ActivityEntry> getActivityEntries(Integer startRecord,
            Integer limitRecord, Long memberId, List<Long> memberIdsToExclude) {
        return activityEntryResource.list()
                .optionalQueryParam("startRecord", startRecord)
                .optionalQueryParam("limitRecord", limitRecord)
                .optionalQueryParam("memberId", memberId)
                .optionalQueryParam("folderId", memberIdsToExclude)
                .execute();
    }

    public static List<ActivityEntry> getActivityEntriesAfter(Date afterDate) {
        if (afterDate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return activityEntryResource.list()
                .queryParam("activitiesAfter", sdf.format(afterDate))
                .execute();
    }

    public static Integer countActivities(Long memberId, List<Long> memberIdsToExclude) {
        try {
            return activityEntryResource.<ActivityEntry, Integer>service("count", Integer.class)
                    .optionalQueryParam("memberId", memberId)
                    .optionalQueryParam("memberIdsToExclude", memberIdsToExclude)
                    .withCache(CacheKeys.withClass(ActivityEntry.class)
                                    .withParameter("memberId", memberId)
                                    .withParameter("memberIdsToExclude", memberIdsToExclude)
                                    .asCount(),
                            CacheRetention.REQUEST)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public static ActivitySubscription getActivitySubscription(long activitySubscriptionId)
            throws ActivitySubscriptionNotFoundException {

        try {
            return activitySubscriptionResource
                    .get(activitySubscriptionId)
                    .withCache(CacheKeys.of(ActivitySubscription.class, activitySubscriptionId),
                            CacheRetention.REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ActivitySubscriptionNotFoundException(
                    "ActivitySubscription with id " + activitySubscriptionId + " not found.");
        }
    }

    private static ActivitySubscription createActivitySubscription(
            ActivitySubscription activitySubscription) {
        return activitySubscriptionResource.create(activitySubscription).execute();
    }

    public static boolean deleteSubscription(Long pk) {
        return activitySubscriptionResource.delete(pk).execute();
    }

    public static ActivitySubscription addSubscription(long memberId,
            ActivityEntryType activityEntryType, long classPK, String extraInfo) {
        return activitySubscriptionResource.service("subscribe", ActivitySubscription.class)
                .queryParam("receiverId", memberId)
                .queryParam("activityEntryType", activityEntryType)
                .queryParam("classPK", classPK)
                .queryParam("extraInfo", extraInfo)
                .post();
    }

    public static boolean deleteSubscription(Long receiverId, ActivityEntryType activityEntryType,
            Long classPK, String extraInfo) {
        return activitySubscriptionResource.service("deleteIfSubscribed", Boolean.class)
                .queryParam("receiverId", receiverId)
                .queryParam("activityEntryType", activityEntryType)
                .queryParam("classPK", classPK)
                .queryParam("extraInfo", extraInfo)
                .delete();
    }

    public static boolean deleteSubscriptionById(Long subscriptionId) {
        return activitySubscriptionResource.delete(subscriptionId).execute();
    }

    public static boolean isSubscribedToActivity(Long receiverId, Long classNameId, Long classPK,
            Integer type, String extraInfo) {
        return activitySubscriptionResource.service("isSubscribed", Boolean.class)
                .queryParam("receiverId", receiverId)
                .queryParam("classNameId", classNameId)
                .queryParam("classPK", classPK)
                .queryParam("extraInfo", extraInfo)
                .queryParam("type", type)
                .get();
    }

    public static List<ActivitySubscription> getActivitySubscriptions(Long classNameId, Long classPK,
            Long receiverId) {
        return activitySubscriptionResource.list()
                .optionalQueryParam("classNameId", classNameId)
                .optionalQueryParam("classPK", classPK)
                .optionalQueryParam("receiverId", receiverId)
                .execute();
    }

    public static List<ActivitySubscription> getActivitySubscriptionsForMember(Long memberId) {
        return getActivitySubscriptions(null, null, memberId);
    }
}
