package org.xcolab.client.activities;

import org.xcolab.client.activities.enums.ActivityType;
import org.xcolab.client.activities.exceptions.ActivityEntryNotFoundException;
import org.xcolab.client.activities.exceptions.ActivitySubscriptionNotFoundException;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.client.enums.ServiceNamespace;

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
            ActivityEntryType activityEntryType, long classPK, String extraInfo) {
        return activitiesClient.addSubscription(memberId, activityEntryType, classPK, extraInfo);
    }

    public static boolean deleteSubscription(Long receiverId, ActivityEntryType activityEntryType,
            Long classPK, String extraInfo) {
        return activitiesClient.deleteSubscription(receiverId, activityEntryType,
                classPK, extraInfo);
    }

    public static boolean batchDelete(ActivityEntryType activityEntryType, List<Long> classPKs) {
        return activitiesClient.batchDelete(activityEntryType, classPKs);
    }

    public static boolean deleteSubscriptionById(Long subscriptionId) {
        return activitiesClient.deleteSubscriptionById( subscriptionId);
    }

    public static boolean isSubscribedToActivity(Long receiverId, Long classNameId, Long classPK,
            Integer type, String extraInfo) {
        return activitiesClient.isSubscribedToActivity(receiverId, classNameId, classPK,
                type, extraInfo);
    }

    public static List<ActivitySubscription> getActivitySubscriptions(Long classNameId, Long classPK,
            Long receiverId) {
        return activitiesClient.getActivitySubscriptions(classNameId, classPK,
                receiverId);
    }

    public static List<ActivitySubscription> getActivitySubscriptionsForMember(Long memberId) {
        return activitiesClient.getActivitySubscriptionsForMember( memberId);
    }
}
