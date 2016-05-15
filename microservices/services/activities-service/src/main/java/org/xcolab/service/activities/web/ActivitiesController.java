package org.xcolab.service.activities.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.service.activities.domain.activityEntry.ActivityEntryDao;

import java.sql.Timestamp;

@RestController
public class ActivitiesController {

    @Autowired
    private ActivityEntryDao activityEntryDao;

    @RequestMapping(value = "/activityEntries/", method = RequestMethod.POST)
    public ActivityEntry createContentArticle(@RequestBody ActivityEntry activityEntry) {
        java.util.Date date = new java.util.Date();
        activityEntry.setCreateDate(new Timestamp(date.getTime()));
        return this.activityEntryDao.create(activityEntry);
    }

}
