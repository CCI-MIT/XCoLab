package org.xcolab.client.activities;

import org.xcolab.commons.activities.enums.ActivityType;
import org.xcolab.client.activities.exceptions.ActivityEntryNotFoundException;
import org.xcolab.client.activities.exceptions.ActivitySubscriptionNotFoundException;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.commons.activities.enums.ActivityCategory;
import org.xcolab.commons.http.client.enums.ServiceNamespace;

import java.util.Date;
import java.util.List;

public class ActivitiesClientUtil {

    private static final ActivitiesClient activitiesClient = ActivitiesClient.fromNamespace(
            ServiceNamespace.instance());

    public static ActivitiesClient getClient() {
        return activitiesClient;
    }

    public static ActivityEntry createActivityEntry(ActivityType activityType, long memberId,
            long categoryId) {
        return activitiesClient.createActivityEntry(activityType, memberId, categoryId);
    }

    public static ActivityEntry createActivityEntry(ActivityType activityType, long memberId,
            long categoryId, Long additionalId) {
        return activitiesClient.createActivityEntry(activityType, memberId, categoryId,
                additionalId);
    }

    public static ActivityEntry getActivityEntry(Long activityEntryId)
            throws ActivityEntryNotFoundException {
        return activitiesClient.getActivityEntry(activityEntryId);
    }

    public static List<ActivityEntry> getActivityEntries(Integer startRecord,
            Integer limitRecord, Long memberId, List<Long> memberIdsToExclude) {
        return activitiesClient.getActivityEntries(startRecord,
                limitRecord, memberId, memberIdsToExclude);
    }

    public static List<ActivityEntry> getActivityEntriesAfter(Date afterDate) {
        return activitiesClient.getActivityEntriesAfter(afterDate);
    }

    public static int countActivities(Long memberId, List<Long> memberIdsToExclude) {
        return activitiesClient.countActivities(memberId, memberIdsToExclude);
    }

    public static ActivitySubscription getActivitySubscription(long activitySubscriptionId)
            throws ActivitySubscriptionNotFoundException {

        return activitiesClient.getActivitySubscription(activitySubscriptionId);
    }

    private static ActivitySubscription createActivitySubscription(
            ActivitySubscription activitySubscription) {
        return activitiesClient.createActivitySubscription(activitySubscription);
    }

    public static boolean deleteSubscription(Long pk) {
        return activitiesClient.deleteSubscription(pk);
    }

    public static ActivitySubscription addSubscription(long memberId,
            ActivityCategory activityCategory, long categoryId, String extraInfo) {
        return activitiesClient.addSubscription(memberId, activityCategory, categoryId, extraInfo);
    }

    public static boolean deleteSubscription(Long receiverId, ActivityCategory activityCategory,
            Long categoryId) {
        return activitiesClient.deleteSubscription(receiverId, activityCategory,
                categoryId);
    }

    public static boolean batchDelete(ActivityCategory activityCategory, List<Long> categoryIds) {
        return activitiesClient.batchDelete(activityCategory, categoryIds);
    }

    public static boolean deleteSubscriptionById(Long subscriptionId) {
        return activitiesClient.deleteSubscriptionById( subscriptionId);
    }

    public static boolean isSubscribedToActivity(Long receiverId,
            ActivityCategory activityCategory, Long categoryId, String extraInfo) {
        return isSubscribedToActivity(receiverId, activityCategory, categoryId);
    }

    public static boolean isSubscribedToActivity(Long receiverId, ActivityCategory activityCategory, Long categoryId) {
        return activitiesClient.isSubscribedToActivity(receiverId, activityCategory, categoryId);
    }

    public static List<ActivitySubscription> getActivitySubscriptions(ActivityCategory
            activityCategory, Long categoryId,
            Long receiverId) {
        return activitiesClient.getActivitySubscriptions(activityCategory, categoryId,
                receiverId);
    }

    public static List<ActivitySubscription> getActivitySubscriptionsForMember(Long memberId) {
        return activitiesClient.getActivitySubscriptionsForMember( memberId);
    }
}
