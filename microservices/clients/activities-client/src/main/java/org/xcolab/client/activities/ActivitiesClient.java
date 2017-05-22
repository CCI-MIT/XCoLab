package org.xcolab.client.activities;

import org.xcolab.client.activities.exceptions.ActivityEntryNotFoundException;
import org.xcolab.client.activities.exceptions.ActivitySubscriptionNotFoundException;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.activities.pojo.ActivitySubscription;

import org.xcolab.util.IdListUtil;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ActivitiesClient {

    private static final Map<RestService, ActivitiesClient> instances = new HashMap<>();

    private final RestService activitiesService;

    private  final RestResource<ActivityEntry, Long> activityEntryResource;

    private  final RestResource<ActivitySubscription, Long> activitySubscriptionResource;


    private ActivitiesClient(RestService activityService) {
        this.activitiesService = activityService;
        activityEntryResource = new RestResource1<>(activitiesService, "activityEntries", ActivityEntry.TYPES);
        activitySubscriptionResource =
                new RestResource1<>(activitiesService, "activitySubscriptions",
                        ActivitySubscription.TYPES);
    }
    public  ActivityEntry createActivityEntry(Long memberId,
                                                    Long classPrimaryKey,
                                                    String extraData,
                                                    Integer providerType) {
        return activityEntryResource.service("createActivityEntry", ActivityEntry.class)
                .queryParam("providerType", providerType)
                .queryParam("memberId", memberId)
                .queryParam("classPrimaryKey",classPrimaryKey)
                .queryParam("extraData",extraData)
                .post();
    }

    public  ActivityEntry getActivityEntry(Long activityEntryId)
            throws ActivityEntryNotFoundException {
        try {
            return activityEntryResource.get(activityEntryId)
                    .withCache(CacheKeys.of(ActivityEntry.class, activityEntryId),
                            CacheName.MISC_REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ActivityEntryNotFoundException(
                    "ActivityEntry with id " + activityEntryId + " not found.");
        }
    }

    public  List<ActivityEntry> getActivityEntries(Integer startRecord,
            Integer limitRecord, Long memberId, List<Long> memberIdsToExclude) {
        return activityEntryResource.list()
                .optionalQueryParam("startRecord", startRecord)
                .optionalQueryParam("limitRecord", limitRecord)
                .optionalQueryParam("memberId", memberId)
                .optionalQueryParam("memberIdsToExclude", IdListUtil.getStringFromIds(memberIdsToExclude))
                .execute();
    }

    public  List<ActivityEntry> getActivityEntriesAfter(Date afterDate) {
        if (afterDate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return activityEntryResource.list()
                .queryParam("activitiesAfter", sdf.format(afterDate))
                .execute();
    }

    public  Integer countActivities(Long memberId, List<Long> memberIdsToExclude) {
        try {
            return activityEntryResource.<ActivityEntry, Integer>service("count", Integer.class)
                    .optionalQueryParam("memberId", memberId)
                    .optionalQueryParam("memberIdsToExclude", memberIdsToExclude)
                    .withCache(CacheKeys.withClass(ActivityEntry.class)
                                    .withParameter("memberId", memberId)
                                    .withParameter("memberIdsToExclude", memberIdsToExclude)
                                    .asCount(),
                            CacheName.MISC_REQUEST)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public  ActivitySubscription getActivitySubscription(long activitySubscriptionId)
            throws ActivitySubscriptionNotFoundException {

        try {
            return activitySubscriptionResource
                    .get(activitySubscriptionId)
                    .withCache(CacheKeys.of(ActivitySubscription.class, activitySubscriptionId),
                            CacheName.MISC_REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ActivitySubscriptionNotFoundException(
                    "ActivitySubscription with id " + activitySubscriptionId + " not found.");
        }
    }

    public  ActivitySubscription createActivitySubscription(
            ActivitySubscription activitySubscription) {
        return activitySubscriptionResource.create(activitySubscription).execute();
    }

    public  boolean deleteSubscription(Long pk) {
        return activitySubscriptionResource.delete(pk).execute();
    }

    public ActivitySubscription addSubscription(long memberId,
            ActivityEntryType activityEntryType, long classPK, String extraInfo) {
        return activitySubscriptionResource.service("subscribe", ActivitySubscription.class)
                .queryParam("receiverId", memberId)
                .queryParam("activityEntryType", activityEntryType)
                .queryParam("classPK", classPK)
                .queryParam("extraInfo", extraInfo)
                .post();
    }

    public boolean deleteSubscription(Long receiverId, ActivityEntryType activityEntryType,
            Long classPK, String extraInfo) {
        return activitySubscriptionResource.service("deleteIfSubscribed", Boolean.class)
                .queryParam("receiverId", receiverId)
                .queryParam("activityEntryType", activityEntryType)
                .queryParam("classPK", classPK)
                .queryParam("extraInfo", extraInfo)
                .delete();
    }

    public boolean deleteSubscriptionById(Long subscriptionId) {
        return activitySubscriptionResource.delete(subscriptionId).execute();
    }

    public boolean isSubscribedToActivity(Long receiverId, Long classNameId, Long classPK,
            Integer type, String extraInfo) {
        return activitySubscriptionResource.service("isSubscribed", Boolean.class)
                .queryParam("receiverId", receiverId)
                .queryParam("classNameId", classNameId)
                .queryParam("classPK", classPK)
                .queryParam("extraInfo", extraInfo)
                .queryParam("type", type)
                .get();
    }

    public List<ActivitySubscription> getActivitySubscriptions(Long classNameId, Long classPK,
            Long receiverId) {
        return activitySubscriptionResource.list()
                .optionalQueryParam("classNameId", classNameId)
                .optionalQueryParam("classPK", classPK)
                .optionalQueryParam("receiverId", receiverId)
                .execute();
    }

    public List<ActivitySubscription> getActivitySubscriptionsForMember(Long memberId) {
        return getActivitySubscriptions(null, null, memberId);
    }

    public static ActivitiesClient fromService(RestService activitiesService) {
        return instances.computeIfAbsent(activitiesService, ActivitiesClient::new);
    }
}
