package org.xcolab.service.tracking.domain.trackedvisitor2user;


import org.xcolab.model.tables.pojos.TrackedVisitor2User;

import java.util.Optional;

public interface TrackedVisitor2UserDao {

    Optional<TrackedVisitor2User> getByUUID(String uuid);
    Optional<TrackedVisitor2User> getByuserId(long userId);

    TrackedVisitor2User create(TrackedVisitor2User trackedVisitor2User);

    boolean update(TrackedVisitor2User pojo);
}
