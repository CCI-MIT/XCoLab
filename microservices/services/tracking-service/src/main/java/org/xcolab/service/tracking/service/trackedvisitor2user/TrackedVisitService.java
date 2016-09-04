package org.xcolab.service.tracking.service.trackedvisitor2user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.TrackedVisit;
import org.xcolab.service.tracking.domain.trackedvisit.TrackedVisitDao;
import org.xcolab.service.tracking.service.iptranslation.IpTranslationService;
import org.xcolab.service.tracking.service.iptranslation.IpTranslationService.IpFormatException;

@Service
public class TrackedVisitService {

    private static final Logger log = LoggerFactory.getLogger(TrackedVisitService.class);

    @Autowired
    private TrackedVisitDao trackedVisitDao;

    @Autowired
    private IpTranslationService ipTranslationService;

    public TrackedVisit createTrackedVisit(TrackedVisit trackedVisit) {
        final String remoteIp = trackedVisit.getIp();
        try {
            ipTranslationService.getLocationForIp(remoteIp).ifPresent(location -> {
                trackedVisit.setCountry(location.getCountry());
                trackedVisit.setCity(location.getCity());
            });
        } catch (IpFormatException e) {
            log.warn("Failed to resolve location for IP from {}", trackedVisit);
        }
        return trackedVisitDao.create(trackedVisit);
    }
}
