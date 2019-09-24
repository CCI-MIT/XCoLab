package org.xcolab.client.activity;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.activity.exceptions.ActivityEntryNotFoundException;
import org.xcolab.client.activity.exceptions.ActivitySubscriptionNotFoundException;
import org.xcolab.client.activity.pojo.IActivityEntry;
import org.xcolab.client.activity.pojo.IActivitySubscription;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class ActivityClientMockImpl implements IActivityClient {

    @Override
    public IActivityEntry createActivityEntry(IActivityEntry activityEntry) {
        return null;
    }

    @Override
    public IActivityEntry getActivityEntry(Long activityEntryId)
            throws ActivityEntryNotFoundException {
        return null;
    }

    @Override
    public List<IActivityEntry> getActivities(Integer startRecord, Integer limitRecord,
            String activityCategory, Long categoryId, Long userId, List<Long> userIdsToExclude,
            String sort, String activitiesAfter) {
        return Collections.emptyList();
    }

    @Override
    public Integer countActivities(Long userId, List<Long> userIdsToExclude) {
        return null;
    }

    @Override
    public IActivitySubscription getActivitySubscription(Long activitySubscriptionId)
            throws ActivitySubscriptionNotFoundException {
        return null;
    }

    @Override
    public IActivitySubscription createActivitySubscription(
            IActivitySubscription activitySubscription) {
        return null;
    }

    @Override
    public IActivitySubscription addSubscription(Long receiverId, ActivityCategory activityCategory,
            Long categoryId) {
        return null;
    }

    @Override
    public boolean deleteSubscription(Long receiverId, ActivityCategory activityCategory,
            Long categoryId) {
        return false;
    }

    @Override
    public boolean deleteActivitySubscription(Long subscriptionId) {
        return false;
    }

    @Override
    public boolean batchDelete(ActivityCategory activityCategory, List<Long> categoryIds) {
        return false;
    }

    @Override
    public boolean isSubscribed(Long receiverId, ActivityCategory activityCategory,
            Long categoryId) {
        return false;
    }

    @Override
    public List<IActivitySubscription> getActivitySubscriptions(ActivityCategory activityCategory,
            Long categoryId, Long receiverId) {
        return Collections.emptyList();
    }
}
