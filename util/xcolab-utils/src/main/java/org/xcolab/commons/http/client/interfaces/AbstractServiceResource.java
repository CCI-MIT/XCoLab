package org.xcolab.commons.http.client.interfaces;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.UriBuilder;
import org.xcolab.commons.http.client.ServiceResource;
import org.xcolab.commons.http.client.queries.ServiceQuery;

import java.util.List;

public abstract class AbstractServiceResource implements ServiceResource {

    private final String resourceName;

    public AbstractServiceResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public UriBuilder getResourceUrl() {
        return getBaseUrl().resource(resourceName);
    }

    @Override
    public UriBuilder getResourceUrl(Object resourceId) {
        return getBaseUrl().resource(resourceName, resourceId);
    }

    @Override
    public <T, R> ServiceQuery<T, R> service(long id, String serviceEndpoint, Class<R> returnType) {
        return new ServiceQuery<>(this, id, serviceEndpoint, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> service(String id, String serviceEndpoint,
            Class<R> returnType) {
        return new ServiceQuery<>(this, id, serviceEndpoint, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> service(String serviceEndpoint, Class<R> returnType) {
        return new ServiceQuery<>(this, serviceEndpoint, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> query(long id, Class<R> returnType) {
        return new ServiceQuery<>(this, id, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> query(String id, Class<R> returnType) {
        return new ServiceQuery<>(this, id, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> query(Class<R> returnType) {
        return new ServiceQuery<>(this, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> service(long id, String serviceEndpoint,
            ParameterizedTypeReference<List<T>> typeReference) {
        return new ServiceQuery<>(this, id, serviceEndpoint, typeReference);
    }

    @Override
    public <T, R> ServiceQuery<T, R> service(String id, String serviceEndpoint,
            ParameterizedTypeReference<List<T>> typeReference) {
        return new ServiceQuery<>(this, id, serviceEndpoint, typeReference);
    }

    @Override
    public <T, R> ServiceQuery<T, R> service(String serviceEndpoint,
            ParameterizedTypeReference<List<T>> typeReference) {
        return new ServiceQuery<>(this, serviceEndpoint, typeReference);
    }

}
