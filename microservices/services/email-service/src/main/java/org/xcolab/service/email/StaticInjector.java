package org.xcolab.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.email.IEmailClient;
import org.xcolab.client.email.StaticEmailContext;

@Component
public class StaticInjector {

    @Autowired
    public StaticInjector(IEmailClient emailClient) {
        StaticEmailContext.setClients(emailClient);
    }
}
