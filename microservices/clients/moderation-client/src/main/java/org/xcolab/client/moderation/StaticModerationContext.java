package org.xcolab.client.moderation;

import org.springframework.util.Assert;

public class StaticModerationContext {

    private static IModerationClient moderationClient;

    private StaticModerationContext() { }

    public static IModerationClient getModerationClient() {
        return moderationClient;
    }

    public static void setModerationClient(IModerationClient moderationClient) {
        Assert.notNull(moderationClient, "moderationClient must not be null!");
        StaticModerationContext.moderationClient = moderationClient;
    }
}
