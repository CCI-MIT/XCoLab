package org.xcolab.client.tracking;

import org.springframework.util.Assert;

public class StaticTrackingContext {

    private static ITrackingClient trackingClient;
    private static IBalloonClient balloonClient;

    private StaticTrackingContext() {}

    public static void setClients(ITrackingClient trackingClient, IBalloonClient balloonClient) {
        Assert.notNull(trackingClient, "trackingClient must not be null!");
        Assert.notNull(balloonClient, "balloonClient must not be null!");
        StaticTrackingContext.trackingClient = trackingClient;
        StaticTrackingContext.balloonClient = balloonClient;
    }

    public static ITrackingClient getTrackingClient() {
        return trackingClient;
    }

    public static IBalloonClient getBalloonClient() {
        return balloonClient;
    }
}
