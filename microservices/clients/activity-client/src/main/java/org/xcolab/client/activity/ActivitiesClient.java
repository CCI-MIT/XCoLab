package org.xcolab.client.activity;

import org.xcolab.client.activity.exceptions.ActivityEntryNotFoundException;
import org.xcolab.client.activity.exceptions.ActivitySubscriptionNotFoundException;
import org.xcolab.client.activity.pojo.IActivityEntry;
import org.xcolab.client.activity.pojo.IActivitySubscription;
import org.xcolab.client.activity.pojo.tables.pojos.ActivityEntry;
import org.xcolab.commons.IdListUtil;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.activities.enums.ActivityType;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class ActivitiesClient {

    private final RestResource<IActivityEntry, Long> activityEntryResource = null;
            // activityEntries
    private final RestResource<IActivitySubscription, Long> activitySubscriptionResource = null;
            // activitySubscriptions

    public IActivityEntry createActivityEntry(ActivityType activityType, long userId,
            long categoryId) {
        return createActivityEntry(activityType, userId, categoryId, null);
    }

    public IActivityEntry createActivityEntry(ActivityType activityType, long userId,
            long categoryId, Long additionalId) {
        IActivityEntry activityEntry = new ActivityEntry();
        activityEntry.setActivityCategory(activityType.getCategory().name());
        activityEntry.setActivityType(activityType.name());
        activityEntry.setUserId(userId);
        activityEntry.setCategoryId(categoryId);
        activityEntry.setAdditionalId(additionalId);
        return activityEntryResource.create(activityEntry).execute();
    }

    public IActivityEntry getActivityEntry(Long activityEntryId)
            throws ActivityEntryNotFoundException {
        try {
            return activityEntryResource.get(activityEntryId)
                    .withCache(CacheKeys.of(IActivityEntry.class, activityEntryId),
                            CacheName.MISC_REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ActivityEntryNotFoundException(
                    "ActivityEntry with id " + activityEntryId + " not found.");
        }
    }

    public List<IActivityEntry> getActivityEntries(Integer startRecord,
            Integer limitRecord, Long userId, List<Long> userIdsToExclude) {
        return activityEntryResource.list()
                .optionalQueryParam("startRecord", startRecord)
                .optionalQueryParam("limitRecord", limitRecord)
                .optionalQueryParam("userId", userId)
                .optionalQueryParam("userIdsToExclude",
                        IdListUtil.getStringFromIds(userIdsToExclude))
                .execute();
    }

    public List<IActivityEntry> getActivityEntriesAfter(Date afterDate) {
        if (afterDate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return activityEntryResource.list()
                .queryParam("activitiesAfter", sdf.format(afterDate))
                .execute();
    }

    public int countActivities(Long userId, List<Long> userIdsToExclude) {
        try {
            return activityEntryResource.<IActivityEntry, Integer>collectionService("count",
                    Integer.class)
                    .optionalQueryParam("userId", userId)
                    .optionalQueryParam("userIdsToExclude", userIdsToExclude)
                    .withCache(CacheKeys.withClass(IActivityEntry.class)
                                    .withParameter("userId", userId)
                                    .withParameter("userIdsToExclude", userIdsToExclude)
                                    .asCount(),
                            CacheName.MISC_MEDIUM)
                    .getChecked();
        } catch (EntityNotFoundException e) {
            return 0;
        }
    }

    public IActivitySubscription getActivitySubscription(long activitySubscriptionId)
            throws ActivitySubscriptionNotFoundException {
        try {
            return activitySubscriptionResource
                    .get(activitySubscriptionId)
                    .withCache(CacheKeys.of(IActivitySubscription.class, activitySubscriptionId),
                            CacheName.MISC_REQUEST)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ActivitySubscriptionNotFoundException(
                    "ActivitySubscription with id " + activitySubscriptionId + " not found.");
        }
    }

    public IActivitySubscription createActivitySubscription(
            IActivitySubscription activitySubscription) {
        return activitySubscriptionResource.create(activitySubscription).execute();
    }

    public boolean deleteSubscription(Long pk) {
        return activitySubscriptionResource.delete(pk).execute();
    }

    public IActivitySubscription addSubscription(long userId, ActivityCategory activityCategory,
            long categoryId, String extraInfo) {
        return addSubscription(userId, activityCategory, categoryId);
    }

    public IActivitySubscription addSubscription(long userId, ActivityCategory activityCategory,
            long categoryId) {
        return activitySubscriptionResource
                .collectionService("subscribe", IActivitySubscription.class)
                .queryParam("receiverId", userId)
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

    public List<IActivitySubscription> getActivitySubscriptions(ActivityCategory activityCategory,
            Long categoryId, Long receiverId) {
        return activitySubscriptionResource.list()
                .optionalQueryParam("activityCategory", activityCategory)
                .optionalQueryParam("categoryId", categoryId)
                .optionalQueryParam("receiverId", receiverId)
                .execute();
    }

    public List<IActivitySubscription> getActivitySubscriptionsForMember(Long userId) {
        return getActivitySubscriptions(null, null, userId);
    }

    public List<IActivityEntry> getActivitiesByCategoryId(String activityCategory,
            Long categoryId) {
        return activityEntryResource.list()
                .queryParam("activityCategory", activityCategory)
                .queryParam("categoryId", categoryId)
                .execute();
    }
}
