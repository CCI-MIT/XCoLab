package org.xcolab.client.tracking;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.tracking.pojo.ILocation;
import org.xcolab.client.tracking.pojo.ITrackedVisit;
import org.xcolab.client.tracking.pojo.tables.pojos.TrackedVisit;

@Component
@Profile("test")
public class TrackingClientMockImpl implements ITrackingClient {

    @Override
    public ITrackedVisit addTrackedVisit(ITrackedVisit trackedVisit, Long userId) {
        ITrackedVisit tv = new TrackedVisit();
        tv.setVisitorUuid("trackedVisitUuid");
        return tv;
    }

    @Override
    public ILocation getLocationForIp(String ipAddress) {
        return null;
    }
}
