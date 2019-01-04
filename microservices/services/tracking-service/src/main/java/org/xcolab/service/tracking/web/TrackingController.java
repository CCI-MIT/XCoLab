package org.xcolab.service.tracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.TrackedVisit;
import org.xcolab.service.tracking.service.TrackedVisitService;

@RestController
public class TrackingController {

    private final TrackedVisitService trackedVisitService;

    @Autowired
    public TrackingController(TrackedVisitService trackedVisitService) {
        this.trackedVisitService = trackedVisitService;
    }

    @RequestMapping(value = "/trackedVisits", method = RequestMethod.POST)
    public TrackedVisit createTrackedVisit(@RequestBody TrackedVisit trackedVisit,
            @RequestParam(required = false) Long userId) {
        return trackedVisitService.createTrackedVisit(trackedVisit, userId);
    }
}
