package org.xcolab.service.tracking.domain.trackedvisitor;

import org.xcolab.client.tracking.pojo.ITrackedVisitor;

import java.util.Optional;

public interface TrackedVisitorDao {

    Optional<ITrackedVisitor> getByUuid(String uuid);

    Optional<ITrackedVisitor> getByUserId(long userId);

    ITrackedVisitor create(ITrackedVisitor trackedVisitor);

    boolean update(ITrackedVisitor pojo);
}
