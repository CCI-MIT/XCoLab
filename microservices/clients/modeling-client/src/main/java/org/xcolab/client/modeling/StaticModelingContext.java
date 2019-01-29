package org.xcolab.client.modeling;

import org.springframework.util.Assert;

public class StaticModelingContext {

    private static IModelingClient modelingClient;

    private StaticModelingContext() {}

    public static void setClients(IModelingClient modelingClient) {
        Assert.notNull(modelingClient, "modelingClient must not be null!");
        StaticModelingContext.modelingClient = modelingClient;
    }

    public static IModelingClient getModelingClient() {
        return modelingClient;
    }
}
