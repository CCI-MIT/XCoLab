package org.xcolab.service.tracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.TrackedVisit;
import org.xcolab.model.tables.pojos.TrackedVisitor2User;
import org.xcolab.service.tracking.service.TrackedVisitService;
import org.xcolab.service.tracking.service.TrackedVisitor2UserService;

@RestController
public class TrackingController {

    private final TrackedVisitor2UserService trackedVisitor2UserService;
    private final TrackedVisitService trackedVisitService;

    @Autowired
    public TrackingController(TrackedVisitor2UserService trackedVisitor2UserService,
            TrackedVisitService trackedVisitService) {
        this.trackedVisitor2UserService = trackedVisitor2UserService;
        this.trackedVisitService = trackedVisitService;
    }

    @RequestMapping(value = "/trackedVisits", method = RequestMethod.POST)
    public TrackedVisit createTrackedVisit(@RequestBody TrackedVisit trackedVisit) {
        return trackedVisitService.createTrackedVisit(trackedVisit);
    }

    @RequestMapping(value = "/trackedVisitors", method = RequestMethod.POST)
    public TrackedVisitor2User getOrCreateTrackedVisitor(
            @RequestParam(required = false) Long memberId) {
        if (memberId != null) {
            return trackedVisitor2UserService.getOrCreate(memberId);
        } else {
            TrackedVisitor2User trackedVisitor = new TrackedVisitor2User();
            trackedVisitor.setUuid_(trackedVisitor2UserService.generateUniqueUUID());
            return trackedVisitor;
        }
    }
}
