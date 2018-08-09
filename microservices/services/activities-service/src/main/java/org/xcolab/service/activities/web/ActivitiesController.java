package org.xcolab.service.activities.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.service.activities.domain.activityEntry.ActivityEntryDao;
import org.xcolab.service.activities.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.service.activities.exceptions.NotFoundException;
import org.xcolab.service.activities.service.ActivitiesService;
import org.xcolab.service.activities.utils.Utils;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.util.List;

@RestController
public class ActivitiesController {

    @Autowired
    private ActivityEntryDao activityEntryDao;

    @Autowired
    private ActivitySubscriptionDao activitySubscriptionDao;

    @Autowired
    private ActivitiesService activitiesService;

    @PostMapping("/activityEntries")
    public ActivityEntry createActivityEntry(@RequestBody ActivityEntry activityEntry) {
        return this.activityEntryDao.create(activityEntry);
    }

    @GetMapping("/activityEntries/{activityEntryId}")
    public ActivityEntry getActivityEntry(@PathVariable long activityEntryId)
            throws NotFoundException {
        return activityEntryDao.get(activityEntryId);
    }

    @GetMapping("/activityEntries")
    public List<ActivityEntry> getActivities(@RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) List<Long> userIdsToExclude,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String activitiesAfter) {

        if (activitiesAfter != null) {
            return activityEntryDao.getActivitiesAfter(Utils.parseDate(activitiesAfter));
        } else {
            final PaginationHelper paginationHelper =
                    new PaginationHelper(startRecord, limitRecord, sort);
            return activityEntryDao.findByGiven(paginationHelper, userId, userIdsToExclude);
        }
    }

    @GetMapping("/activityEntries/count")
    public Integer getActivitiesCount(@RequestParam(required = false) Long userId,
            @RequestParam(required = false) List<Long> userIdsToExclude) {
        return this.activityEntryDao.countByGiven(userId, userIdsToExclude);
    }

    @PostMapping("/activitySubscriptions")
    public ActivitySubscription createActivitySubscription(
            @RequestBody ActivitySubscription activitySubscription) {
        return this.activitySubscriptionDao.create(activitySubscription);
    }

    @PostMapping("/activitySubscriptions/subscribe")
    public ActivitySubscription subscribe(@RequestParam long receiverId,
            @RequestParam ActivityCategory activityCategory, @RequestParam long categoryId) {
        return activitiesService.subscribe(receiverId, activityCategory, categoryId);
    }

    @GetMapping("/activitySubscriptions/{activitySubscriptionId}")
    public ActivitySubscription getActivitySubscription(@PathVariable long activitySubscriptionId)
            throws NotFoundException {
        return activitySubscriptionDao.get(activitySubscriptionId)
                .orElseThrow(NotFoundException::new);
    }

    @DeleteMapping("/activitySubscriptions/{pk}")
    public boolean deleteActivitySubscription(@PathVariable long pk) {
        this.activitySubscriptionDao.delete(pk);
        return true;
    }

    @DeleteMapping("/activitySubscriptions/deleteIfSubscribed")
    public boolean deleteIfSubscribed(@RequestParam(required = false) Long receiverId,
            @RequestParam(required = false) ActivityCategory activityCategory,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Integer type) {
        return activitiesService.unsubscribe(receiverId, activityCategory, categoryId);
    }

    @PostMapping("/activitySubscriptions/batchDelete")
    public boolean batchDelete(@RequestParam ActivityCategory activityCategory,
            @RequestBody List<Long> categoryIds) {
        return activitySubscriptionDao.delete(activityCategory, categoryIds)
                && activityEntryDao.delete(activityCategory, categoryIds);
    }

    @GetMapping("/activitySubscriptions/isSubscribed")
    public boolean isSubscribed(@RequestParam ActivityCategory activityCategory,
            @RequestParam long receiverId, @RequestParam long categoryId) {
        return this.activitySubscriptionDao
                .isSubscribed(activityCategory, receiverId, categoryId);
    }

    @GetMapping("/activitySubscriptions")
    public List<ActivitySubscription> getActivitySubscribers(
            @RequestParam(required = false) ActivityCategory activityCategory,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long receiverId) {
        return this.activitySubscriptionDao
                .getActivitySubscribers(activityCategory, categoryId, receiverId);
    }
}
