package org.xcolab.service.tracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.tracking.pojo.ITrackedVisit;
import org.xcolab.client.tracking.pojo.ITrackedVisitor;
import org.xcolab.model.tables.pojos.TrackedVisitorImpl;
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

    public void getOrCreateTrackedVisitor(ITrackedVisit trackedVisit, Long userId) {
        ITrackedVisitor trackedVisitor;
        if (userId != null) {
            trackedVisitor = getOrCreate(userId);
        } else {
            trackedVisitor = createUnknownVisitor();
        }
        trackedVisit.setVisitorUuid(trackedVisitor.getUuid());
    }

    public ITrackedVisitor getOrCreate(long userId) {
        return trackedVisitorDao.getByUserId(userId).orElse(create(userId));
    }

    public ITrackedVisitor createUnknownVisitor() {
        return create(null);
    }

    private ITrackedVisitor create(Long userId) {
        ITrackedVisitor trackedVisitor = new TrackedVisitorImpl();
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
