package org.xcolab.util.http.client;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.interfaces.AbstractHttpResource;
import org.xcolab.util.http.client.interfaces.HttpEndpoint;
import org.xcolab.util.http.client.interfaces.HttpResource;
import org.xcolab.util.http.client.queries.ServiceQuery;

public class ServiceResource extends AbstractHttpResource implements HttpResource {
    private final UriBuilder baseUrl;

    public ServiceResource(HttpEndpoint serviceOrParent, String resourceName) {
        super(resourceName);
        this.baseUrl = serviceOrParent.getBaseUrl();
    }

    @Override
    public UriBuilder getBaseUrl() {
        return baseUrl;
    }

    public <O> ServiceQuery<O> service(long id, String serviceEndpoint, Class<O> returnType) {
        return new ServiceQuery<>(this, id, serviceEndpoint, returnType);
    }

    public <O> ServiceQuery<O> service(String id, String serviceEndpoint, Class<O> returnType) {
        return new ServiceQuery<>(this, id, serviceEndpoint, returnType);
    }

    public <O> ServiceQuery<O> service(String serviceEndpoint, Class<O> returnType) {
        return new ServiceQuery<>(this, serviceEndpoint, returnType);
    }

    public <O> ServiceQuery<O> query(long id, Class<O> returnType) {
        return new ServiceQuery<>(this, id, returnType);
    }

    public <O> ServiceQuery<O> query(String id, Class<O> returnType) {
        return new ServiceQuery<>(this, id, returnType);
    }

    public <O> ServiceQuery<O> query(Class<O> returnType) {
        return new ServiceQuery<>(this, returnType);
    }

    public ServiceResource getSubServiceResource(final long resourceId, String subResourceName) {
        return new ServiceResource(new HttpEndpoint() {
            private final UriBuilder baseUrl = ServiceResource.this.getResourceUrl(resourceId);
            @Override
            public UriBuilder getBaseUrl() {
                return baseUrl;
            }
        }, subResourceName);
    }
}
