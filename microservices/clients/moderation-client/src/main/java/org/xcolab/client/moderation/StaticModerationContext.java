package org.xcolab.client.moderation;

public class StaticModerationContext {

    private static IModerationClient moderationClient;

    public static IModerationClient getModerationClient() {
        return moderationClient;
    }

    public static void setModerationClient(IModerationClient moderationClient) {
        StaticModerationContext.moderationClient = moderationClient;
    }
}
