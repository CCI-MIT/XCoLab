package org.xcolab.client.email;

public class StaticEmailContext {

    private static IEmailClient emailClient;

    public static void setEmailClient(IEmailClient emailClient) {
        StaticEmailContext.emailClient = emailClient;
    }

    public static IEmailClient getEmailClient() {
        return emailClient;
    }
}
