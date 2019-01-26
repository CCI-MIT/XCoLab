package org.xcolab.client.activity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activity.exceptions.ActivityEntryNotFoundException;
import org.xcolab.client.activity.exceptions.ActivitySubscriptionNotFoundException;
import org.xcolab.client.activity.pojo.IActivityEntry;
import org.xcolab.client.activity.pojo.IActivitySubscription;
import org.xcolab.client.activity.pojo.tables.pojos.ActivityEntry;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.activities.enums.ActivityType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@FeignClient("xcolab-activity-service")
public interface IActivityClient {

    default IActivityEntry createActivityEntry(ActivityType activityType, Long userId,
            long categoryId) {
        return createActivityEntry(activityType, userId, categoryId, null);
    }

    default IActivityEntry createActivityEntry(ActivityType activityType, Long userId,
            Long categoryId, Long additionalId) {
        IActivityEntry activityEntry = new ActivityEntry();
        activityEntry.setActivityCategory(activityType.getCategory().name());
        activityEntry.setActivityType(activityType.name());
        activityEntry.setUserId(userId);
        activityEntry.setCategoryId(categoryId);
        activityEntry.setAdditionalId(additionalId);
        return createActivityEntry(activityEntry);
    }

    @PostMapping("/activityEntries")
    IActivityEntry createActivityEntry(@RequestBody IActivityEntry activityEntry);

    @GetMapping("/activityEntries/{activityEntryId}")
    IActivityEntry getActivityEntry(@PathVariable("activityEntryId") Long activityEntryId)
            throws ActivityEntryNotFoundException;

    default List<IActivityEntry> getActivityEntries(Integer startRecord,
            Integer limitRecord, Long userId, List<Long> userIdsToExclude) {
        return getActivities(startRecord, limitRecord, null, null, userId, userIdsToExclude, null,
                null);
    }

    default List<IActivityEntry> getActivityEntriesAfter(Date afterDate) {
        if (afterDate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return getActivities(null, null, null, null, null, null, null, sdf.format(afterDate));
    }

    @GetMapping("/activityEntries")
    List<IActivityEntry> getActivities(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "limitRecord", required = false) Integer limitRecord,
            @RequestParam(value = "activityCategory", required = false) String activityCategory,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "userIdsToExclude", required = false) List<Long> userIdsToExclude,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "activitiesAfter", required = false) String activitiesAfter);

    @GetMapping("/count/activityEntries")
    Integer countActivities(@RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "userIdsToExclude", required = false)
                    List<Long> userIdsToExclude);

    @GetMapping("/activitySubscriptions/{activitySubscriptionId}")
    IActivitySubscription getActivitySubscription(
            @PathVariable("activitySubscriptionId") Long activitySubscriptionId)
            throws ActivitySubscriptionNotFoundException;

    @PostMapping("/activitySubscriptions")
    IActivitySubscription createActivitySubscription(
            @RequestBody IActivitySubscription activitySubscription);

    default IActivitySubscription addSubscription(Long userId, ActivityCategory activityCategory,
            Long categoryId, String extraInfo) {
        return addSubscription(userId, activityCategory, categoryId);
    }

    @PostMapping("/activitySubscriptions/subscribe")
    IActivitySubscription addSubscription(@RequestParam("receiverId") Long receiverId,
            @RequestParam("activityCategory") ActivityCategory activityCategory,
            @RequestParam("categoryId") Long categoryId);

    @DeleteMapping("/activitySubscriptions/deleteIfSubscribed")
    public boolean deleteSubscription(
            @RequestParam(value = "receiverId", required = false) Long receiverId,
            @RequestParam(value = "activityCategory", required = false)
                    ActivityCategory activityCategory,
            @RequestParam(value = "categoryId", required = false) Long categoryId);

    @DeleteMapping("/activitySubscriptions/{subscriptionId}")
    boolean deleteActivitySubscription(@PathVariable("subscriptionId") Long subscriptionId);

    @PostMapping("/activitySubscriptions/batchDelete")
    boolean batchDelete(@RequestParam("activityCategory") ActivityCategory activityCategory,
            @RequestBody List<Long> categoryIds);

    @GetMapping("/activitySubscriptions/isSubscribed")
    boolean isSubscribed(@RequestParam("receiverId") Long receiverId,
            @RequestParam("activityCategory") ActivityCategory activityCategory,
            @RequestParam("categoryId") Long categoryId);

    @GetMapping("/activitySubscriptions")
    List<IActivitySubscription> getActivitySubscriptions(
            @RequestParam(value = "activityCategory", required = false)
                    ActivityCategory activityCategory,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "receiverId", required = false) Long receiverId);

    default List<IActivitySubscription> getActivitySubscriptionsForMember(Long userId) {
        return getActivitySubscriptions(null, null, userId);
    }

    default List<IActivityEntry> getActivitiesByCategoryId(String activityCategory,
            Long categoryId) {
        return getActivities(null, null, activityCategory, categoryId, null, null, null, null);
    }
}
