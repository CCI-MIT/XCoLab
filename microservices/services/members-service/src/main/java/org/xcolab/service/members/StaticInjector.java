package org.xcolab.service.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.admin.IAdminClient;
import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.IEmailTemplateClient;
import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.email.IEmailClient;
import org.xcolab.client.email.StaticEmailContext;
import org.xcolab.client.tracking.IBalloonClient;
import org.xcolab.client.tracking.ITrackingClient;
import org.xcolab.client.tracking.StaticTrackingContext;

@Component
public class StaticInjector {

    @Autowired
    public StaticInjector(ITrackingClient trackingClient, IBalloonClient balloonClient,
            IAdminClient adminClient, IContestTypeClient contestTypeClient,
            IEmailTemplateClient emailTemplateClient, IEmailClient emailClient) {
        StaticTrackingContext.setClients(trackingClient, balloonClient);
        StaticEmailContext.setClients(emailClient);
        StaticAdminContext.setClients(adminClient, contestTypeClient, emailTemplateClient);
    }
}
