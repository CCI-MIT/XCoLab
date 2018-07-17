package org.xcolab.client.activities;

import org.xcolab.util.activities.enums.ActivityType;
import org.xcolab.client.activities.exceptions.ActivityEntryNotFoundException;
import org.xcolab.client.activities.exceptions.ActivitySubscriptionNotFoundException;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.commons.IdListUtil;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ActivitiesClient {

    private static final Map<ServiceNamespace, ActivitiesClient> instances = new HashMap<>();

    private  final RestResource<ActivityEntry, Long> activityEntryResource;

    private  final RestResource<ActivitySubscription, Long> activitySubscriptionResource;


    private ActivitiesClient(ServiceNamespace serviceNamespace) {
        activityEntryResource = new RestResource1<>(ActivityResource.ACTIVITY_ENTRY,
                ActivityEntry.TYPES, serviceNamespace);
        activitySubscriptionResource = new RestResource1<>(ActivityResource.ACTIVITY_SUBSCRIPTION,
                ActivitySubscription.TYPES, serviceNamespace);
    }

    public ActivityEntry createActivityEntry(ActivityType activityType, long memberId,
            long categoryId) {
        return createActivityEntry(activityType, memberId, categoryId, null);
    }

    public ActivityEntry createActivityEntry(ActivityType activityType, long memberId,
            long categoryId, Long additionalId) {
        ActivityEntry activityEntry = new ActivityEntry();
        activityEntry.setActivityCategory(activityType.getCategory().name());
        activityEntry.setActivityType(activityType.name());
        activityEntry.setMemberId(memberId);
        activityEntry.setCategoryId(categoryId);
        activityEntry.setAdditionalId(additionalId);
        return activityEntryResource.create(activityEntry).execute();
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

    public int countActivities(Long memberId, List<Long> memberIdsToExclude) {
        try {
            return activityEntryResource.<ActivityEntry, Integer>collectionService("count", Integer.class)
                    .optionalQueryParam("memberId", memberId)
                    .optionalQueryParam("memberIdsToExclude", memberIdsToExclude)
                    .withCache(CacheKeys.withClass(ActivityEntry.class)
                                    .withParameter("memberId", memberId)
                                    .withParameter("memberIdsToExclude", memberIdsToExclude)
                                    .asCount(),
                            CacheName.MISC_MEDIUM)
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

    public ActivitySubscription addSubscription(long memberId, ActivityCategory activityCategory,
            long categoryId, String extraInfo) {
        return addSubscription(memberId, activityCategory, categoryId);
    }

    public ActivitySubscription addSubscription(long memberId, ActivityCategory activityCategory, long categoryId) {
        return activitySubscriptionResource.collectionService("subscribe", ActivitySubscription.class)
                .queryParam("receiverId", memberId)
                .queryParam("activityCategory", activityCategory)
                .queryParam("categoryId", categoryId)
                .post();
    }

    public boolean deleteSubscription(Long receiverId, ActivityCategory activityCategory,
            Long categoryId) {
        return activitySubscriptionResource.collectionService("deleteIfSubscribed", Boolean.class)
                .queryParam("receiverId", receiverId)
                .queryParam("activityCategory", activityCategory)
                .queryParam("categoryId", categoryId)
                .delete();
    }

    public boolean deleteSubscriptionById(Long subscriptionId) {
        return activitySubscriptionResource.delete(subscriptionId).execute();
    }

    public boolean batchDelete(ActivityCategory activityCategory, List<Long> categoryIds) {
        return activitySubscriptionResource.collectionService("batchDelete", Boolean.class)
                .queryParam("activityCategory", activityCategory)
                .post(categoryIds);
    }

    public boolean isSubscribedToActivity(Long receiverId, ActivityCategory activityCategory,
            Long categoryId) {
        return activitySubscriptionResource.collectionService("isSubscribed", Boolean.class)
                .queryParam("receiverId", receiverId)
                .queryParam("activityCategory", activityCategory)
                .queryParam("categoryId", categoryId)
                .get();
    }

    public List<ActivitySubscription> getActivitySubscriptions(ActivityCategory activityCategory,
            Long categoryId, Long receiverId) {
        return activitySubscriptionResource.list()
                .optionalQueryParam("activityCategory", activityCategory)
                .optionalQueryParam("categoryId", categoryId)
                .optionalQueryParam("receiverId", receiverId)
                .execute();
    }

    public List<ActivitySubscription> getActivitySubscriptionsForMember(Long memberId) {
        return getActivitySubscriptions(null, null, memberId);
    }

    public static ActivitiesClient fromNamespace(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, ActivitiesClient::new);
    }
}
