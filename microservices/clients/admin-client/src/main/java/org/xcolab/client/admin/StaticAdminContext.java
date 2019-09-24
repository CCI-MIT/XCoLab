package org.xcolab.client.admin;

import org.springframework.util.Assert;

public class StaticAdminContext {

    private static IAdminClient adminClient;
    private static IContestTypeClient contestTypeClient;
    private static IEmailTemplateClient emailTemplateClient;

    private StaticAdminContext() {}

    public static void setClients(IAdminClient adminClient, IContestTypeClient contestTypeClient,
            IEmailTemplateClient emailTemplateClient) {
        Assert.notNull(adminClient, "commentClient must not be null!");
        Assert.notNull(contestTypeClient, "contestTypeClient must not be null!");
        Assert.notNull(emailTemplateClient, "emailTemplateClient must not be null!");
        StaticAdminContext.adminClient = adminClient;
        StaticAdminContext.contestTypeClient = contestTypeClient;
        StaticAdminContext.emailTemplateClient = emailTemplateClient;
    }

    public static IAdminClient getAdminClient() {
        return adminClient;
    }

    public static IContestTypeClient getContestTypeClient() {
        return contestTypeClient;
    }

    public static IEmailTemplateClient getEmailTemplateClient() {
        return emailTemplateClient;
    }
}
