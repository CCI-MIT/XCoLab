package org.xcolab.client.email;

import org.springframework.util.Assert;

public class StaticEmailContext {

    private static IEmailClient emailClient;

    public static void setClients(IEmailClient emailClient) {
        Assert.notNull(emailClient, "emailClient must not be null!");
        StaticEmailContext.emailClient = emailClient;
    }

    public static IEmailClient getEmailClient() {
        return emailClient;
    }
}
