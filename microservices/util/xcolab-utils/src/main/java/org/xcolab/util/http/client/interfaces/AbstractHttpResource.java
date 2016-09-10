package org.xcolab.util.http.client.interfaces;

import org.xcolab.util.http.UriBuilder;

public abstract class AbstractHttpResource implements HttpResource {

    private final String resourceName;

    public AbstractHttpResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public UriBuilder getResourceUrl() {
        return getBaseUrl().cloneUriBuilder().path("/" + resourceName);
    }

    @Override
    public UriBuilder getResourceUrl(Object resourceId) {
        return getBaseUrl().cloneUriBuilder().path("/" + resourceName + "/" + resourceId.toString());
    }
}
