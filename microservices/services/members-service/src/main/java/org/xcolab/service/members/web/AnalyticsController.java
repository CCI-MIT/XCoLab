package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.AnalyticsUserEvent;
import org.xcolab.service.members.domain.analyticsuserevent.AnalyticsUserEventDao;

@RestController
public class AnalyticsController {

    private final AnalyticsUserEventDao analyticsUserEventDao;

    @Autowired
    public AnalyticsController(AnalyticsUserEventDao analyticsUserEventDao) {
        this.analyticsUserEventDao = analyticsUserEventDao;
    }

    @RequestMapping(value = "/members/{memberId}/analyticsEvent/{idString}", method = RequestMethod.GET)
    public boolean exists(@PathVariable long memberId, @PathVariable String idString) {
        return analyticsUserEventDao.exists(memberId, idString);
    }

    @RequestMapping(value = "/members/{memberId}/analyticsEvent", method = RequestMethod.POST)
    public AnalyticsUserEvent create(@PathVariable long memberId,
            @RequestBody AnalyticsUserEvent analyticsUserEvent) {
        return analyticsUserEventDao.create(analyticsUserEvent);
    }
}
