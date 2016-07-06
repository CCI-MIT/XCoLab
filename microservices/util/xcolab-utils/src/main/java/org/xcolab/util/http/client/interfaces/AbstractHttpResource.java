package org.xcolab.util.http.client.interfaces;

import org.xcolab.util.http.UriBuilder;

public abstract class AbstractHttpResource implements HttpResource {

    private final String resourceName;

    public AbstractHttpResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public UriBuilder getResourceUrl() {
        return getBaseUrl().path("/" + resourceName);
    }

    @Override
    public UriBuilder getResourceUrl(long resourceId) {
        return getBaseUrl().path("/" + resourceName + "/" + resourceId);
    }

    @Override
    public UriBuilder getResourceUrl(String resourceId) {
        return getBaseUrl().path("/" + resourceName + "/" + resourceId);
    }
}
