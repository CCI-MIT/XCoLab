package org.xcolab.view.util.clienthelpers;

import org.xcolab.client.tracking.ITrackingClient;
import org.xcolab.client.tracking.pojo.ILocation;
import org.xcolab.client.tracking.pojo.ITrackedVisit;
import org.xcolab.client.tracking.pojo.tables.pojos.TrackedVisit;

public class TrackingClientMock implements ITrackingClient {

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
