package org.xcolab.client.admin;

import org.springframework.util.Assert;

public class StaticAdminContext {

    private static AdminClient adminClient;
    private static ContestTypeClient contestTypeClient;
    private static EmailTemplateClient emailTemplateClient;

    private StaticAdminContext() {}

    public static void setClients(AdminClient adminClient, ContestTypeClient contestTypeClient,
            EmailTemplateClient emailTemplateClient) {
        Assert.notNull(adminClient, "commentClient must not be null!");
        Assert.notNull(contestTypeClient, "categoryClient must not be null!");
        Assert.notNull(emailTemplateClient, "threadClient must not be null!");
        StaticAdminContext.adminClient = adminClient;
        StaticAdminContext.contestTypeClient = contestTypeClient;
        StaticAdminContext.emailTemplateClient = emailTemplateClient;
    }

    public static AdminClient getAdminClient() {
        return adminClient;
    }

    public static ContestTypeClient getContestTypeClient() {
        return contestTypeClient;
    }

    public static EmailTemplateClient getEmailTemplateClient() {
        return emailTemplateClient;
    }
}
