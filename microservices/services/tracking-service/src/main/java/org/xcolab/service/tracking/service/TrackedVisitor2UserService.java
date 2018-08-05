package org.xcolab.service.tracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.TrackedVisit;
import org.xcolab.model.tables.pojos.TrackedVisitor2User;
import org.xcolab.service.tracking.domain.trackedvisitor2user.TrackedVisitor2UserDao;

import java.util.UUID;

@Service
public class TrackedVisitor2UserService {

    private static final int UUID_GENERATION_MAX_ITERATIONS = 5;

    private final TrackedVisitor2UserDao trackedVisitor2UserDao;

    @Autowired
    public TrackedVisitor2UserService(TrackedVisitor2UserDao trackedVisitor2UserDao) {
        this.trackedVisitor2UserDao = trackedVisitor2UserDao;
    }

    public void getOrCreateTrackedVisitor(TrackedVisit trackedVisit, Long userId) {
        TrackedVisitor2User trackedVisitor;
        if (userId != null) {
            trackedVisitor = getOrCreate(userId);
        } else {
            trackedVisitor = createUnknownVisitor();
        }
        trackedVisit.setUuid_(trackedVisitor.getUuid_());
    }

    public TrackedVisitor2User getOrCreate(long userId) {
        return trackedVisitor2UserDao.getByuserId(userId).orElse(create(userId));
    }

    public TrackedVisitor2User createUnknownVisitor() {
        return create(null);
    }

    private TrackedVisitor2User create(Long userId) {
        TrackedVisitor2User trackedVisitor = new TrackedVisitor2User();
        trackedVisitor.setUserId(userId);
        trackedVisitor.setUuid_(generateUniqueUUID());
        return trackedVisitor2UserDao.create(trackedVisitor);
    }

    private String generateUniqueUUID() {
        String uuid;
        int counter = 0;
        do {
            uuid = UUID.randomUUID().toString();
            if (++counter > UUID_GENERATION_MAX_ITERATIONS) {
                throw new UUIDGenerationException();
            }
        } while (trackedVisitor2UserDao.getByUUID(uuid).isPresent());
        return uuid;
    }

    private static class UUIDGenerationException extends RuntimeException {
        public UUIDGenerationException() {
            super("Failed to generate UUID in " + UUID_GENERATION_MAX_ITERATIONS + " iterations.");
        }
    }
}
