package org.xcolab.service.activities.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.service.activities.domain.activityEntry.ActivityEntryDao;
import org.xcolab.service.activities.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.service.activities.exceptions.NotFoundException;
import org.xcolab.service.activities.utils.Utils;
import org.xcolab.service.utils.PaginationHelper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class ActivitiesController {

    @Autowired
    private ActivityEntryDao activityEntryDao;

    @Autowired
    private ActivitySubscriptionDao activitySubscriptionDao;

    @RequestMapping(value = "/activityEntries", method = RequestMethod.POST)
    public ActivityEntry createActivity(@RequestBody ActivityEntry activityEntry) {
        java.util.Date date = new java.util.Date();
        activityEntry.setCreateDate(new Timestamp(date.getTime()));
        return this.activityEntryDao.create(activityEntry);
    }

    @RequestMapping(value = "/activityEntries/{activityEntryId}", method = RequestMethod.GET)
    public ActivityEntry getActivityEntry(@PathVariable("activityEntryId") Long activityEntryId)
            throws NotFoundException {
        if (activityEntryId == null || activityEntryId == 0) {
            throw new NotFoundException("No activityEntryId given");
        } else {
            return activityEntryDao.get(activityEntryId);
        }
    }

    @RequestMapping(value = "/activityEntries", method = RequestMethod.GET)
    public List<ActivityEntry> getActivities(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) List<Long> memberIdsToExclude,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String activitiesAfter
    ) {

        if (activitiesAfter != null) {

            return activityEntryDao.getActivitiesAfter(Utils.parseDate(activitiesAfter));
        } else {
            final PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord,
                    sort);
            return activityEntryDao.findByGiven(paginationHelper, memberId, memberIdsToExclude);
        }
    }

    @RequestMapping(value = "/activityEntries/count", method = RequestMethod.GET)
    public Integer getActivitiesCount(
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) List<Long> memberIdsToExclude) {
        return this.activityEntryDao.findByGivenCount(memberId, memberIdsToExclude);
    }

    @RequestMapping(value = "/activitySubscriptions", method = RequestMethod.POST)
    public ActivitySubscription createActivitySubscription(
            @RequestBody ActivitySubscription activitySubscription) {
        activitySubscription.setCreateDate(new Timestamp(new Date().getTime()));
        activitySubscription.setModifiedDate(new Timestamp(new Date().getTime()));

        return this.activitySubscriptionDao.create(activitySubscription);
    }

    @RequestMapping(value = "/activitySubscriptions/{activitySubscriptionId}", method = RequestMethod.GET)
    public ActivitySubscription getActivitySubscription(
            @PathVariable("activitySubscriptionId") Long activitySubscriptionId)
            throws NotFoundException {
        if (activitySubscriptionId == null || activitySubscriptionId == 0) {
            throw new NotFoundException("No activitySubscriptionId given");
        } else {
            return activitySubscriptionDao.get(activitySubscriptionId);
        }
    }

    @RequestMapping(value = "/activitySubscriptions/{pk}", method = RequestMethod.DELETE)
    public boolean deleteActivitySubscription(@PathVariable long pk)
            throws NotFoundException {
        this.activitySubscriptionDao.delete(pk);
        return true;
    }

    @RequestMapping(value = "/activitySubscriptions/deleteIfSubscribed", method = RequestMethod.DELETE)
    public boolean deleteIfSubscribed(
            @RequestParam(required = false) Long receiverId,
            @RequestParam(required = false) Long classNameId,
            @RequestParam(required = false) Long classPK,
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) String extraInfo) {
        return this.activitySubscriptionDao
                .deleteSubscription(receiverId, classNameId, classPK, type, extraInfo);
    }

    @RequestMapping(value = "/activitySubscriptions/isSubscribed", method = RequestMethod.GET)
    public boolean isSubscribed(
            @RequestParam(required = false) Long receiverId,
            @RequestParam(required = false) Long classNameId,
            @RequestParam(required = false) Long classPK,
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) String extraInfo) {
        return this.activitySubscriptionDao
                .isSubscribed(receiverId, classNameId, classPK, type, extraInfo);
    }

    @RequestMapping(value = "/activitySubscriptions", method = RequestMethod.GET)
    public List<ActivitySubscription> getActivitySubscribers(
            @RequestParam(required = false) Long classNameId,
            @RequestParam(required = false) Long classPK,
            @RequestParam(required = false) Long receiverId) {
        return this.activitySubscriptionDao
                .getActivitySubscribers(classNameId, classPK, receiverId);
    }

}
