package org.xcolab.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.admin.IAdminClient;
import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.IEmailTemplateClient;
import org.xcolab.client.admin.StaticAdminContext;

@Component
@Profile("!test")
public class StaticInjector {

    @Autowired
    public StaticInjector(IAdminClient adminClient, IContestTypeClient contestTypeClient,
            IEmailTemplateClient emailTemplateClient) {
        StaticAdminContext.setClients(adminClient, contestTypeClient, emailTemplateClient);
    }
}
