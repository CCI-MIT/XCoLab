package org.xcolab.client.tracking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.xcolab.client.tracking.pojo.ILocation;
import org.xcolab.client.tracking.pojo.ITrackedVisit;
import org.xcolab.client.tracking.pojo.tables.pojos.TrackedVisit;

@Configuration
@Profile("test")
public class TrackingClientConfig {

    @Bean
    public ITrackingClient trackingClient() {
        return new TrackingClientMock();
    }

    private static class TrackingClientMock implements ITrackingClient {

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
}
