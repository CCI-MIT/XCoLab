package org.xcolab.service.tracking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.TrackedVisit;
import org.xcolab.model.tables.pojos.TrackedVisitor2User;
import org.xcolab.service.tracking.domain.trackedvisit.TrackedVisitDao;
import org.xcolab.service.tracking.domain.trackedvisitor2user.TrackedVisitor2UserDao;
import org.xcolab.service.tracking.exceptions.NotFoundException;
import org.xcolab.service.tracking.service.trackedvisitor2user.TrackedVisitor2UserService;

@RestController
public class TrackingController {

    private final TrackedVisitDao trackedVisitDao;
    private final TrackedVisitor2UserDao trackedVisitor2UserDao;
    private final TrackedVisitor2UserService trackedVisitor2UserService;

    @Autowired
    public TrackingController(TrackedVisitDao trackedVisitDao,
            TrackedVisitor2UserDao trackedVisitor2UserDao,
            TrackedVisitor2UserService trackedVisitor2UserService) {
        this.trackedVisitDao = trackedVisitDao;
        this.trackedVisitor2UserDao = trackedVisitor2UserDao;
        this.trackedVisitor2UserService = trackedVisitor2UserService;
    }

    @RequestMapping(value = "/trackedVisits", method = RequestMethod.POST)
    public TrackedVisit createTrackedVisit(TrackedVisit trackedVisit) {
        return trackedVisitDao.create(trackedVisit);
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

    @RequestMapping(value = "/trackedVisitors/{uuid}", method = RequestMethod.GET)
    public TrackedVisitor2User getTrackedVisitorByUUID(@RequestParam String uuid)
            throws NotFoundException {
        return trackedVisitor2UserDao.getByUUID(uuid).orElseThrow(NotFoundException::new);
    }
}
