package org.xcolab.service.modeling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.modeling.IModelingClient;
import org.xcolab.client.modeling.StaticModelingContext;

@Component
@Profile("!test")
public class StaticInjector {

    @Autowired
    public StaticInjector(IModelingClient modelingClient) {
        StaticModelingContext.setClients(modelingClient);
    }
}
