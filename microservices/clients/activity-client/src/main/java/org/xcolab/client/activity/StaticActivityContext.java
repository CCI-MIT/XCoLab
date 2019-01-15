package org.xcolab.client.activity;

import org.springframework.util.Assert;

public class StaticActivityContext {

    private static ActivityClient activityClient;

    private StaticActivityContext() {}

    public static void setActivityClient(
            ActivityClient activityClient) {
        Assert.notNull(activityClient, "activityClient must not be null!");
        StaticActivityContext.activityClient = activityClient;
    }

    public static ActivityClient getActivityClient() {
        return activityClient;
    }
}
