package org.xcolab.service.tracking.domain.trackedvisitor;


import org.xcolab.model.tables.pojos.TrackedVisitor;

import java.util.Optional;

public interface TrackedVisitorDao {

    Optional<TrackedVisitor> getByUuid(String uuid);

    Optional<TrackedVisitor> getByUserId(long userId);

    TrackedVisitor create(TrackedVisitor trackedVisitor);

    boolean update(TrackedVisitor pojo);
}
