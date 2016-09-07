package org.xcolab.util.http.client;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.UriProvider;
import org.xcolab.util.http.client.interfaces.AbstractRestResource;
import org.xcolab.util.http.client.interfaces.HttpEndpoint;
import org.xcolab.util.http.client.types.TypeProvider;

public class RestResource1<T> extends AbstractRestResource<T> {

    private final HttpEndpoint serviceOrParent;
    private final String resourceName;

    public RestResource1(HttpEndpoint serviceOrParent, String resourceName,
            TypeProvider<T> typeProvider) {
        super(typeProvider);
        this.serviceOrParent = serviceOrParent;
        this.resourceName = resourceName;
    }

    @Override
    public UriProvider getBaseUrl() {
        return serviceOrParent.getBaseUrl();
    }

    public <S> RestResource1<S> getSubRestResource(final long resourceId, String subResourceName,
            TypeProvider<S> typeProvider) {
        return new RestResource1<>(new HttpEndpoint() {
            private final UriProvider baseUrl
                    = new UriProvider(RestResource1.this.getResourceUrl(resourceId));
            @Override
            public UriProvider getBaseUrl() {
                return baseUrl;
            }
        }, subResourceName, typeProvider);
    }

    public ServiceResource getSubServiceResource(final long resourceId, String subResourceName) {
        return new ServiceResource1(new HttpEndpoint() {
            private final UriProvider baseUrl
                    = new UriProvider(RestResource1.this.getResourceUrl(resourceId));
            @Override
            public UriProvider getBaseUrl() {
                return baseUrl;
            }
        }, subResourceName);
    }

    @Override
    public UriBuilder getResourceUrl() {
        return getBaseUrl().cloneUriBuilder().path("/" + resourceName);
    }

    @Override
    public UriBuilder getResourceUrl(long resourceId) {
        return getBaseUrl().cloneUriBuilder().path("/" + resourceName + "/" + resourceId);
    }

    @Override
    public UriBuilder getResourceUrl(String resourceId) {
        return getBaseUrl().cloneUriBuilder().path("/" + resourceName + "/" + resourceId);
    }
}
