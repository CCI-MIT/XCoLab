package org.xcolab.service.tracking.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.tracking.ITrackingClient;
import org.xcolab.client.tracking.pojo.ILocation;
import org.xcolab.client.tracking.pojo.ITrackedVisit;
import org.xcolab.service.tracking.service.TrackedVisitService;
import org.xcolab.service.tracking.service.iptranslation.IpTranslationService;

@RestController
public class TrackingController implements ITrackingClient {

    private static final Logger log = LoggerFactory.getLogger(TrackingController.class);

    @Autowired
    private TrackedVisitService trackedVisitService;

    @Autowired
    private IpTranslationService ipTranslationService;

    @Override
    @PostMapping("/trackedVisits")
    public ITrackedVisit addTrackedVisit(@RequestBody ITrackedVisit trackedVisit,
            @RequestParam Long userId) {
        return trackedVisitService.createTrackedVisit(trackedVisit, userId);
    }

    @Override
    @GetMapping("/locations")
    public ILocation getLocationForIp(@RequestParam String ipAddress) {
        try {
            return ipTranslationService.getLocationForIp(ipAddress).orElse(null);
        } catch (IpTranslationService.IpFormatException e) {
            log.warn("Could not process ip address {}: {}", ipAddress, e.toString());
            return null;
        }
    }
}
