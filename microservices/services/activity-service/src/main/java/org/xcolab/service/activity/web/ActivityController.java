package org.xcolab.service.activity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.activity.IActivityClient;
import org.xcolab.client.activity.exceptions.ActivityEntryNotFoundException;
import org.xcolab.client.activity.exceptions.ActivitySubscriptionNotFoundException;
import org.xcolab.client.activity.pojo.IActivityEntry;
import org.xcolab.client.activity.pojo.IActivitySubscription;
import org.xcolab.service.activity.domain.activityEntry.ActivityEntryDao;
import org.xcolab.service.activity.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.service.activity.exceptions.NotFoundException;
import org.xcolab.service.activity.service.ActivitiesService;
import org.xcolab.service.activity.utils.Utils;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.util.List;

@RestController
public class ActivityController implements IActivityClient {

    @Autowired
    private ActivityEntryDao activityEntryDao;

    @Autowired
    private ActivitySubscriptionDao activitySubscriptionDao;

    @Autowired
    private ActivitiesService activitiesService;

    @Override
    @PostMapping("/activityEntries")
    public IActivityEntry createActivityEntry(@RequestBody IActivityEntry activityEntry) {
        return this.activityEntryDao.create(activityEntry);
    }

    @Override
    @GetMapping("/activityEntries/{activityEntryId}")
    public IActivityEntry getActivityEntry(@PathVariable Long activityEntryId)
            throws ActivityEntryNotFoundException {
        try {
            return activityEntryDao.get(activityEntryId);
        } catch (NotFoundException e) {
            throw new ActivityEntryNotFoundException(activityEntryId);
        }
    }

    @Override
    @GetMapping("/activityEntries")
    public List<IActivityEntry> getActivities(@RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String activityCategory,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) List<Long> userIdsToExclude,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String activitiesAfter) {
        //TODO: this should not be an either or!
        if (activitiesAfter != null) {
            return activityEntryDao.getActivitiesAfter(Utils.parseDate(activitiesAfter));
        } else {
            final PaginationHelper paginationHelper =
                    new PaginationHelper(startRecord, limitRecord, sort);
            return activityEntryDao.findByGiven(paginationHelper, activityCategory, categoryId,
                    userId, userIdsToExclude);
        }
    }

    @Override
    @GetMapping("/count/activityEntries")
    public Integer countActivities(@RequestParam(required = false) Long userId,
            @RequestParam(required = false) List<Long> userIdsToExclude) {
        return this.activityEntryDao.countByGiven(userId, userIdsToExclude);
    }

    @Override
    @PostMapping("/activitySubscriptions")
    public IActivitySubscription createActivitySubscription(
            @RequestBody IActivitySubscription activitySubscription) {
        return this.activitySubscriptionDao.create(activitySubscription);
    }

    @Override
    @PostMapping("/activitySubscriptions/subscribe")
    public IActivitySubscription addSubscription(@RequestParam Long receiverId,
            @RequestParam ActivityCategory activityCategory, @RequestParam Long categoryId) {
        return activitiesService.subscribe(receiverId, activityCategory, categoryId);
    }

    @Override
    @GetMapping("/activitySubscriptions/{activitySubscriptionId}")
    public IActivitySubscription getActivitySubscription(@PathVariable Long activitySubscriptionId)
            throws ActivitySubscriptionNotFoundException {
        return activitySubscriptionDao
                .get(activitySubscriptionId).orElseThrow(
                        () -> new ActivitySubscriptionNotFoundException(activitySubscriptionId));
    }

    @Override
    @DeleteMapping("/activitySubscriptions/{subscriptionId}")
    public boolean deleteActivitySubscription(@PathVariable Long subscriptionId) {
        this.activitySubscriptionDao.delete(subscriptionId);
        return true;
    }

    @Override
    @DeleteMapping("/activitySubscriptions/deleteIfSubscribed")
    public boolean deleteSubscription(@RequestParam(required = false) Long receiverId,
            @RequestParam(required = false) ActivityCategory activityCategory,
            @RequestParam(required = false) Long categoryId) {
        return activitiesService.unsubscribe(receiverId, activityCategory, categoryId);
    }

    @Override
    @PostMapping("/activitySubscriptions/batchDelete")
    public boolean batchDelete(@RequestParam ActivityCategory activityCategory,
            @RequestBody List<Long> categoryIds) {
        return activitySubscriptionDao.delete(activityCategory, categoryIds)
                && activityEntryDao.delete(activityCategory, categoryIds);
    }

    @Override
    @GetMapping("/activitySubscriptions/isSubscribed")
    public boolean isSubscribed(@RequestParam Long receiverId,
            @RequestParam ActivityCategory activityCategory, @RequestParam Long categoryId) {
        return this.activitySubscriptionDao.isSubscribed(activityCategory, receiverId, categoryId);
    }

    @Override
    @GetMapping("/activitySubscriptions")
    public List<IActivitySubscription> getActivitySubscriptions(
            @RequestParam(required = false) ActivityCategory activityCategory,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long receiverId) {
        return this.activitySubscriptionDao
                .getActivitySubscribers(activityCategory, categoryId, receiverId);
    }
}
