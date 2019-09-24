package org.xcolab.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.email.IEmailClient;
import org.xcolab.client.email.StaticEmailContext;

@Component
@Profile("!test")
public class StaticInjector {

    @Autowired
    public StaticInjector(IEmailClient emailClient) {
        StaticEmailContext.setClients(emailClient);
    }
}
