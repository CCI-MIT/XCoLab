package org.xcolab.view.util.clienthelpers;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import org.xcolab.client.tracking.ITrackingClient;
import org.xcolab.client.tracking.pojo.tables.pojos.TrackedVisit;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

public class TrackingClientMockerHelper {

    public static TrackedVisit getDefaultTrackedVisit(){
        TrackedVisit trackedVisit = new TrackedVisit();
        trackedVisit.setVisitorUuid("trackedVisitUuid");
        return trackedVisit;
    }

    public static void mockTrackingClient() throws Exception {
        ITrackingClient trackingClient = PowerMockito.mock(ITrackingClient.class);
        Mockito.when(trackingClient.addTrackedVisit(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyLong()))
                .thenAnswer(invocation -> getDefaultTrackedVisit());
    }
}
