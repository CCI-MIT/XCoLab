package org.xcolab.service.tracking.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.tracking.pojo.ITrackedVisit;
import org.xcolab.client.tracking.pojo.ITrackedVisitor;
import org.xcolab.service.tracking.domain.trackedvisitor.TrackedVisitorDao;
import org.xcolab.service.tracking.domain.trackedvisit.TrackedVisitDao;
import org.xcolab.service.tracking.service.iptranslation.IpTranslationService;
import org.xcolab.service.tracking.service.iptranslation.IpTranslationService.IpFormatException;

@Service
@RestController
public class TrackedVisitService {

    private static final Logger log = LoggerFactory.getLogger(TrackedVisitService.class);

    private final TrackedVisitDao trackedVisitDao;
    private final IpTranslationService ipTranslationService;
    private final TrackedVisitorService trackedVisitorService;
    private final TrackedVisitorDao trackedVisitorDao;

    @Autowired
    public TrackedVisitService(IpTranslationService ipTranslationService,
            TrackedVisitDao trackedVisitDao, TrackedVisitorService trackedVisitorService,
            TrackedVisitorDao trackedVisitorDao) {
        this.ipTranslationService = ipTranslationService;
        this.trackedVisitDao = trackedVisitDao;
        this.trackedVisitorService = trackedVisitorService;
        this.trackedVisitorDao = trackedVisitorDao;
    }

    public ITrackedVisit createTrackedVisit(ITrackedVisit trackedVisit, Long userId) {
        final String remoteIp = trackedVisit.getIp();
        try {
            ipTranslationService.getLocationForIp(remoteIp).ifPresent(location -> {
                trackedVisit.setCountry(location.getCountry());
                trackedVisit.setCity(location.getCity());
            });
        } catch (IpFormatException e) {
            if (!isLocalhost(remoteIp)) {
                log.warn("Failed to resolve location for IP {}", remoteIp);
            }
        }

        ITrackedVisitor trackedVisitor = null;
        if (StringUtils.isNotBlank(trackedVisit.getVisitorUuid())) {
            trackedVisitor = trackedVisitorDao.getByUuid(trackedVisit.getVisitorUuid())
                    .orElse(null);
        }

        if (trackedVisitor == null) {
            trackedVisitorService.getOrCreateTrackedVisitor(trackedVisit, userId);
        } else if (trackedVisitor.getUserId() == null && userId != null) {
            trackedVisitor.setUserId(userId);
            trackedVisitorDao.update(trackedVisitor);
        }
        return trackedVisitDao.create(trackedVisit);
    }

    private boolean isLocalhost(String remoteIp) {
        return "127.0.0.1".equals(remoteIp) || "0:0:0:0:0:0:0:1".equals(remoteIp);
    }
}
