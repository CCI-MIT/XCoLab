package org.xcolab.client.activity;

import org.springframework.util.Assert;

public class StaticActivityContext {

    private static IActivityClient activityClient;

    private StaticActivityContext() {}

    public static void setClients(IActivityClient activityClient) {
        Assert.notNull(activityClient, "activityClient must not be null!");
        StaticActivityContext.activityClient = activityClient;
    }

    public static IActivityClient getActivityClient() {
        return activityClient;
    }
}
