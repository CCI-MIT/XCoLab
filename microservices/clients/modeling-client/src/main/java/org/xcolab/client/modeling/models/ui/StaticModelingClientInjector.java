package org.xcolab.client.modeling.models.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.modeling.IModelingClient;

@Component
public class StaticModelingClientInjector {

    @Autowired
    public StaticModelingClientInjector(IModelingClient modelingClient) {
        ModelUIFactory.setModelingClient(modelingClient);
    }
}
