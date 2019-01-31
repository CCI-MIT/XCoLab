package org.xcolab.client.moderation;

import org.springframework.util.Assert;

public class StaticModerationContext {

    private static IModerationClient moderationClient;

    private StaticModerationContext() {}

    public static void setClients(IModerationClient moderationClient) {
        Assert.notNull(moderationClient, "moderationClient must not be null!");
        StaticModerationContext.moderationClient = moderationClient;
    }

    public static IModerationClient getModerationClient() {
        return moderationClient;
    }
}
