package org.xcolab.service.tracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.tracking.IBalloonClient;
import org.xcolab.client.tracking.ITrackingClient;
import org.xcolab.client.tracking.StaticTrackingContext;

@Component
public class StaticInjector {

    @Autowired
    public StaticInjector(ITrackingClient trackingClient, IBalloonClient balloonClient) {
        StaticTrackingContext.setClients(trackingClient, balloonClient);
    }
}
