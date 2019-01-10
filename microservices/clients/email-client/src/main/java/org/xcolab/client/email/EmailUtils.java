package org.xcolab.client.email;

public class EmailUtils {

    private static IEmailClient emailClient;

    public static void setEmailClient(IEmailClient emailClient) {
        EmailUtils.emailClient = emailClient;
    }
    public static IEmailClient getEmailClient(){
        return emailClient;
    }

}
