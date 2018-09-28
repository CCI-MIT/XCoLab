package org.xcolab.view.util.clienthelpers;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import org.xcolab.client.tracking.TrackingClient;
import org.xcolab.client.tracking.pojo.TrackedVisit;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

public class TrackingClientMockerHelper {

    public static TrackedVisit getDefaultTrackedVisit(){
        TrackedVisit trackedVisit = new TrackedVisit();
        trackedVisit.setVisitorUuid("trackedVisitUuid");
        return trackedVisit;
    }

    public static void mockTrackingClient() throws Exception {
        TrackingClient trackingClient = PowerMockito.mock(TrackingClient.class);
        Mockito.when(trackingClient.addTrackedVisit(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyLong()))
                .thenAnswer(invocation -> getDefaultTrackedVisit());
    }
}
