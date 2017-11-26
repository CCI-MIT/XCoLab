package org.xcolab.service.tracking.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.TrackedVisit;
import org.xcolab.model.tables.pojos.TrackedVisitor2User;
import org.xcolab.service.tracking.domain.trackedvisit.TrackedVisitDao;
import org.xcolab.service.tracking.domain.trackedvisitor2user.TrackedVisitor2UserDao;
import org.xcolab.service.tracking.service.iptranslation.IpTranslationService;
import org.xcolab.service.tracking.service.iptranslation.IpTranslationService.IpFormatException;

@Service
public class TrackedVisitService {

    private static final Logger log = LoggerFactory.getLogger(TrackedVisitService.class);

    private final TrackedVisitDao trackedVisitDao;
    private final IpTranslationService ipTranslationService;
    private final TrackedVisitor2UserService trackedVisitor2UserService;
    private final TrackedVisitor2UserDao trackedVisitor2UserDao;

    @Autowired
    public TrackedVisitService(IpTranslationService ipTranslationService,
            TrackedVisitDao trackedVisitDao, TrackedVisitor2UserService trackedVisitor2UserService,
            TrackedVisitor2UserDao trackedVisitor2UserDao) {
        this.ipTranslationService = ipTranslationService;
        this.trackedVisitDao = trackedVisitDao;
        this.trackedVisitor2UserService = trackedVisitor2UserService;
        this.trackedVisitor2UserDao = trackedVisitor2UserDao;
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

        TrackedVisitor2User trackedVisitor = null;
        if (StringUtils.isNotBlank(trackedVisit.getUuid_())) {
            trackedVisitor = trackedVisitor2UserDao.getByUUID(trackedVisit.getUuid_())
                    .orElse(null);
        }

        if (trackedVisitor == null) {
            trackedVisitor = trackedVisitor2UserService.getOrCreate(userId);
            trackedVisit.setUuid_(trackedVisitor.getUuid_());
        } else {
            if (trackedVisitor.getUserId() == null && userId != null) {
                trackedVisitor.setUserId(userId);
                trackedVisitor2UserDao.update(trackedVisitor);
            }
        }
        return trackedVisitDao.create(trackedVisit);
    }

    private boolean isLocalhost(String remoteIp) {
        return "127.0.0.1".equals(remoteIp) || "0:0:0:0:0:0:0:1".equals(remoteIp);
    }
}
