package org.xcolab.service.activities.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.service.activities.domain.activityEntry.ActivityEntryDao;
import org.xcolab.service.utils.PaginationHelper;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class ActivitiesController {

    @Autowired
    private ActivityEntryDao activityEntryDao;

    @RequestMapping(value = "/activityEntries", method = RequestMethod.POST)
    public ActivityEntry createActivity(@RequestBody ActivityEntry activityEntry) {
        java.util.Date date = new java.util.Date();
        activityEntry.setCreateDate(new Timestamp(date.getTime()));
        return this.activityEntryDao.create(activityEntry);
    }

    @RequestMapping(value = "/activityEntries", method = RequestMethod.GET)
    public List<ActivityEntry> getActivties(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) List<Long> memberIdsToExclude,
            @RequestParam(required = false) String sort) {
        final PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord,
                sort);
        return activityEntryDao.findByGiven(paginationHelper,memberId, memberIdsToExclude);
    }

}
