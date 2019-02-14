package org.xcolab.service.tracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.TrackedVisit;
import org.xcolab.model.tables.pojos.TrackedVisitor;
import org.xcolab.service.tracking.domain.trackedvisitor.TrackedVisitorDao;

import java.util.UUID;

@Service
public class TrackedVisitorService {

    private static final int UUID_GENERATION_MAX_ITERATIONS = 5;

    private final TrackedVisitorDao trackedVisitorDao;

    @Autowired
    public TrackedVisitorService(TrackedVisitorDao trackedVisitorDao) {
        this.trackedVisitorDao = trackedVisitorDao;
    }

    public void getOrCreateTrackedVisitor(TrackedVisit trackedVisit, Long userId) {
        TrackedVisitor trackedVisitor;
        if (userId != null) {
            trackedVisitor = getOrCreate(userId);
        } else {
            trackedVisitor = createUnknownVisitor();
        }
        trackedVisit.setVisitorUuid(trackedVisitor.getUuid());
    }

    public TrackedVisitor getOrCreate(long userId) {
        return trackedVisitorDao.getByUserId(userId).orElse(create(userId));
    }

    public TrackedVisitor createUnknownVisitor() {
        return create(null);
    }

    private TrackedVisitor create(Long userId) {
        TrackedVisitor trackedVisitor = new TrackedVisitor();
        trackedVisitor.setUserId(userId);
        trackedVisitor.setUuid(generateUniqueUUID());
        return trackedVisitorDao.create(trackedVisitor);
    }

    private String generateUniqueUUID() {
        String uuid;
        int counter = 0;
        do {
            uuid = UUID.randomUUID().toString();
            if (++counter > UUID_GENERATION_MAX_ITERATIONS) {
                throw new UUIDGenerationException();
            }
        } while (trackedVisitorDao.getByUuid(uuid).isPresent());
        return uuid;
    }

    private static class UUIDGenerationException extends RuntimeException {
        public UUIDGenerationException() {
            super("Failed to generate UUID in " + UUID_GENERATION_MAX_ITERATIONS + " iterations.");
        }
    }
}
