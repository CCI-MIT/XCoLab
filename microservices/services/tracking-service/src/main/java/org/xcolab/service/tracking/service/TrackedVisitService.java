package org.xcolab.service.tracking.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.TrackedVisit;
import org.xcolab.model.tables.pojos.TrackedVisitor2User;
import org.xcolab.service.tracking.domain.trackedvisit.TrackedVisitDao;
import org.xcolab.service.tracking.service.iptranslation.IpTranslationService;
import org.xcolab.service.tracking.service.iptranslation.IpTranslationService.IpFormatException;

@Service
public class TrackedVisitService {

    private static final Logger log = LoggerFactory.getLogger(TrackedVisitService.class);

    private final TrackedVisitDao trackedVisitDao;
    private final IpTranslationService ipTranslationService;
    private final TrackedVisitor2UserService trackedVisitor2UserService;

    @Autowired
    public TrackedVisitService(IpTranslationService ipTranslationService,
            TrackedVisitDao trackedVisitDao, TrackedVisitor2UserService
            trackedVisitor2UserService) {
        this.ipTranslationService = ipTranslationService;
        this.trackedVisitDao = trackedVisitDao;
        this.trackedVisitor2UserService = trackedVisitor2UserService;
    }

    public TrackedVisit createTrackedVisit(TrackedVisit trackedVisit, Long userId) {
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

        if (StringUtils.isBlank(trackedVisit.getUuid_())) {
            final TrackedVisitor2User trackedVisitor;
            if (userId != null) {
                trackedVisitor = trackedVisitor2UserService.getOrCreate(userId);
            } else {
                trackedVisitor = trackedVisitor2UserService.create();
            }
            trackedVisit.setUuid_(trackedVisitor.getUuid_());
        }
        return trackedVisitDao.create(trackedVisit);
    }

    private boolean isLocalhost(String remoteIp) {
        return "127.0.0.1".equals(remoteIp) || "0:0:0:0:0:0:0:1".equals(remoteIp);
    }
}
