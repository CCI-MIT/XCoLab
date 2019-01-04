package org.xcolab.service.tracking.domain.trackedvisit;

import org.xcolab.client.tracking.pojo.ITrackedVisit;

public interface TrackedVisitDao {

    ITrackedVisit create(ITrackedVisit trackedVisit);
}
