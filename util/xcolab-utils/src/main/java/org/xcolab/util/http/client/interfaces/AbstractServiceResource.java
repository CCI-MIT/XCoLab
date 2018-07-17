package org.xcolab.util.http.client.interfaces;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.ServiceResource;
import org.xcolab.util.http.client.queries.ServiceQuery;

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
    public <T, R> ServiceQuery<T, R> elementService(Object id, String serviceEndpoint,
            Class<R> returnType) {
        return ServiceQuery.createElementService(this, id, serviceEndpoint, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> collectionService(String serviceEndpoint, Class<R> returnType) {
        return ServiceQuery.createCollectionService(this, serviceEndpoint, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> elementQuery(Object id, Class<R> returnType) {
        return ServiceQuery.createElementQuery(this, id, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> collectionQuery(Class<R> returnType) {
        return ServiceQuery.createCollectionQuery(this, returnType);
    }

    @Override
    public <T, R> ServiceQuery<T, R> elementService(Object id, String serviceEndpoint,
            ParameterizedTypeReference<List<T>> typeReference) {
        return ServiceQuery.createElementService(this, id, serviceEndpoint,
                typeReference);
    }

    @Override
    public <T, R> ServiceQuery<T, R> collectionService(String serviceEndpoint,
            ParameterizedTypeReference<List<T>> typeReference) {
        return ServiceQuery.createCollectionService(this, serviceEndpoint, typeReference);
    }

}
