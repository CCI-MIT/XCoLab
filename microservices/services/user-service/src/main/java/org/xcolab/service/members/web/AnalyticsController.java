package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.AnalyticsUserEvent;
import org.xcolab.service.members.domain.analyticsuserevent.AnalyticsUserEventDao;

@RestController
@RequestMapping("/members/{userId}/analyticsEvents")
public class AnalyticsController {

    private final AnalyticsUserEventDao analyticsUserEventDao;

    @Autowired
    public AnalyticsController(AnalyticsUserEventDao analyticsUserEventDao) {
        Assert.notNull(analyticsUserEventDao, "AnalyticsUserEventDao bean is required");
        this.analyticsUserEventDao = analyticsUserEventDao;
    }

    @GetMapping("/{idString}/exists")
    public boolean exists(@PathVariable long userId, @PathVariable String idString) {
        return analyticsUserEventDao.exists(userId, idString);
    }

    @PostMapping
    public AnalyticsUserEvent create(@PathVariable long userId,
            @RequestBody AnalyticsUserEvent analyticsUserEvent) {
        return analyticsUserEventDao.create(analyticsUserEvent);
    }
}
