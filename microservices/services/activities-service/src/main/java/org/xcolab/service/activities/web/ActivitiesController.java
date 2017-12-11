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
import org.xcolab.util.enums.activity.ActivityEntryType;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class ActivitiesController {

    @Autowired
    private ActivityEntryDao activityEntryDao;

    @Autowired
    private ActivitySubscriptionDao activitySubscriptionDao;

    @Autowired
    private ActivitiesService activitiesService;

    @PostMapping("/activityEntries/createActivityEntry")
    public ActivityEntry createActivity(@RequestParam Long memberId,
            @RequestParam Long classPrimaryKey, @RequestParam String extraData,
            @RequestParam Long primaryType, @RequestParam Long secondaryType) {
        java.util.Date date = new java.util.Date();

        ActivityEntry activityEntry = new ActivityEntry();
        activityEntry.setMemberId(memberId);
        activityEntry.setClassPrimaryKey(classPrimaryKey);
        activityEntry.setExtraData(extraData);

        activityEntry.setCreateDate(new Timestamp(date.getTime()));

        //ActivityEntryContentProvider provider = ActivityProvidersImpl.getActivityEntryContentProviderByType(providerType);
        //provider.setActivityEntry(activityEntry);

        activityEntry.setPrimaryType(primaryType);
        activityEntry.setSecondaryType(secondaryType);

        //activityEntry.setActivityEntryBody(provider.getBody());
        //activityEntry.setActivityEntryTitle(provider.getTitle());
        //activityEntry.setActivityEntryName(provider.getName());

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
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) List<Long> memberIdsToExclude,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String activitiesAfter) {

        if (activitiesAfter != null) {
            return activityEntryDao.getActivitiesAfter(Utils.parseDate(activitiesAfter));
        } else {
            final PaginationHelper paginationHelper =
                    new PaginationHelper(startRecord, limitRecord, sort);
            return activityEntryDao.findByGiven(paginationHelper, memberId, memberIdsToExclude);
        }
    }

    @GetMapping("/activityEntries/count")
    public Integer getActivitiesCount(@RequestParam(required = false) Long memberId,
            @RequestParam(required = false) List<Long> memberIdsToExclude) {
        return this.activityEntryDao.countByGiven(memberId, memberIdsToExclude);
    }

    @PostMapping("/activitySubscriptions")
    public ActivitySubscription createActivitySubscription(
            @RequestBody ActivitySubscription activitySubscription) {
        return this.activitySubscriptionDao.create(activitySubscription);
    }

    @PostMapping("/activitySubscriptions/subscribe")
    public ActivitySubscription subscribe(@RequestParam long receiverId,
            @RequestParam ActivityEntryType activityEntryType, @RequestParam long classPK,
            @RequestParam String extraInfo) {
        return activitiesService.subscribe(receiverId, activityEntryType, classPK, extraInfo);
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
            @RequestParam(required = false) ActivityEntryType activityEntryType,
            @RequestParam(required = false) Long classPK,
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) String extraInfo) {
        return activitiesService.unsubscribe(receiverId, activityEntryType, classPK, extraInfo);
    }

    @PostMapping("/activitySubscriptions/batchDelete")
    public boolean batchDelete(@RequestParam ActivityEntryType activityEntryType,
            @RequestBody List<Long> classPKs) {
        return activitySubscriptionDao.delete(activityEntryType, classPKs) && activityEntryDao
                .delete(activityEntryType, classPKs);
    }

    @GetMapping("/activitySubscriptions/isSubscribed")
    public boolean isSubscribed(@RequestParam long receiverId, @RequestParam long classNameId,
            @RequestParam long classPK, @RequestParam int type,
            @RequestParam(required = false) String extraInfo) {
        return this.activitySubscriptionDao
                .isSubscribed(receiverId, classNameId, classPK, type, extraInfo);
    }

    @GetMapping("/activitySubscriptions")
    public List<ActivitySubscription> getActivitySubscribers(
            @RequestParam(required = false) Long classNameId,
            @RequestParam(required = false) Long classPK,
            @RequestParam(required = false) Long receiverId) {
        return this.activitySubscriptionDao
                .getActivitySubscribers(classNameId, classPK, receiverId);
    }
}
