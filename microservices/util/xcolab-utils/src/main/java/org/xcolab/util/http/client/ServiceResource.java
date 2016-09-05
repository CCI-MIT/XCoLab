package org.xcolab.util.http.client;

import org.xcolab.util.http.UriProvider;
import org.xcolab.util.http.client.interfaces.AbstractHttpResource;
import org.xcolab.util.http.client.interfaces.HttpEndpoint;
import org.xcolab.util.http.client.interfaces.HttpResource;
import org.xcolab.util.http.client.queries.ServiceQuery;

public class ServiceResource extends AbstractHttpResource implements HttpResource {

    private final HttpEndpoint serviceOrParent;
    public ServiceResource(HttpEndpoint serviceOrParent, String resourceName) {
        super(resourceName);
        this.serviceOrParent = serviceOrParent;
    }

    @Override
    public UriProvider getBaseUrl() {
        return serviceOrParent.getBaseUrl();
    }

    public <T, R> ServiceQuery<T, R> service(long id, String serviceEndpoint, Class<R> returnType) {
        return new ServiceQuery<>(this, id, serviceEndpoint, returnType);
    }

    public <T, R> ServiceQuery<T, R> service(String id, String serviceEndpoint, Class<R> returnType) {
        return new ServiceQuery<>(this, id, serviceEndpoint, returnType);
    }

    public <T, R> ServiceQuery<T, R> service(String serviceEndpoint, Class<R> returnType) {
        return new ServiceQuery<>(this, serviceEndpoint, returnType);
    }

    public <T, R> ServiceQuery<T, R> query(long id, Class<R> returnType) {
        return new ServiceQuery<>(this, id, returnType);
    }

    public <T, R> ServiceQuery<T, R> query(String id, Class<R> returnType) {
        return new ServiceQuery<>(this, id, returnType);
    }

    public <T, R> ServiceQuery<T, R> query(Class<R> returnType) {
        return new ServiceQuery<>(this, returnType);
    }

    public ServiceResource getSubServiceResource(final long resourceId, String subResourceName) {
        return new ServiceResource(new HttpEndpoint() {
            private final UriProvider baseUrl
                    = new UriProvider(ServiceResource.this.getResourceUrl(resourceId));
            @Override
            public UriProvider getBaseUrl() {
                return baseUrl;
            }
        }, subResourceName);
    }
}
