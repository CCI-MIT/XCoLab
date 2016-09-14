package org.xcolab.util.http.client;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.UriProvider;
import org.xcolab.util.http.client.interfaces.AbstractServiceResource;
import org.xcolab.util.http.client.interfaces.HttpEndpoint;

public class ServiceResource1 extends AbstractServiceResource {

    private final HttpEndpoint serviceOrParent;
    private final String resourceName;

    public ServiceResource1(HttpEndpoint serviceOrParent, String resourceName) {
        this.serviceOrParent = serviceOrParent;
        this.resourceName = resourceName;
    }

    @Override
    public UriProvider getBaseUrl() {
        return serviceOrParent.getBaseUrl();
    }

    @Override
    public UriBuilder getResourceUrl() {
        return getBaseUrl().cloneUriBuilder().resource(resourceName);
    }

    @Override
    public UriBuilder getResourceUrl(Object resourceId) {
        return getBaseUrl().cloneUriBuilder().resource(resourceName, resourceId);
    }
}
