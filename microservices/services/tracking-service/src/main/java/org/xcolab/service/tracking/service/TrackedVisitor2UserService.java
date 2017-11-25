package org.xcolab.service.tracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.TrackedVisitor2User;
import org.xcolab.service.tracking.domain.trackedvisitor2user.TrackedVisitor2UserDao;

import java.util.UUID;

@Service
public class TrackedVisitor2UserService {

    public static final int UUID_GENERATION_MAX_ITERATIONS = 5;
    private final TrackedVisitor2UserDao trackedVisitor2UserDao;

    @Autowired
    public TrackedVisitor2UserService(TrackedVisitor2UserDao trackedVisitor2UserDao) {
        this.trackedVisitor2UserDao = trackedVisitor2UserDao;
    }

    public TrackedVisitor2User getOrCreate(long memberId) {
        return trackedVisitor2UserDao.getByMemberId(memberId)
                .orElseGet(() -> {
                    TrackedVisitor2User newInstance = new TrackedVisitor2User();
                    newInstance.setUserId(memberId);
                    newInstance.setUuid_(generateUniqueUUID());
                    return trackedVisitor2UserDao.create(newInstance);
                });
    }

    public String generateUniqueUUID() {
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
