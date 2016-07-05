package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class ServiceQuery<T, O> {
    private final UriBuilder uriBuilder;
    private final Class<O> entityType;
    private String cacheIdentifierValue;

    public ServiceQuery(RestResource<T> restResource, long id, String serviceName,
            Class<O> entityType) {
        this.entityType = entityType;
        this.uriBuilder = restResource.getResourceUrl(id).path("/" + serviceName);
    }

    public ServiceQuery(RestResource<T> restResource, String id, String serviceName,
            Class<O> entityType) {
        this.entityType = entityType;
        this.uriBuilder = restResource.getResourceUrl(id).path("/" + serviceName);
    }

    public ServiceQuery(RestResource<T> restResource, String serviceName,
            Class<O> entityType) {
        this.entityType = entityType;
        this.uriBuilder = restResource.getResourceUrl().path("/" + serviceName);
    }

    public O get() throws EntityNotFoundException {
        if (cacheIdentifierValue == null) {
            return RequestUtils.get(uriBuilder, entityType);
        } else {
            return RequestUtils.get(uriBuilder, entityType, cacheIdentifierValue);
        }
    }

    public O getUnchecked() {
        if (cacheIdentifierValue == null) {
            return RequestUtils.getUnchecked(uriBuilder, entityType);
        } else {
            return RequestUtils.getUnchecked(uriBuilder, entityType, cacheIdentifierValue);
        }
    }

    public O post() {
        return RequestUtils.post(uriBuilder, null, entityType);
    }

    public boolean put() {
        return RequestUtils.put(uriBuilder);
    }

    public boolean delete() {
        return RequestUtils.delete(uriBuilder);
    }

    public ServiceQuery<T, O> cacheIdentifier(String cacheIdentifier) {
        this.cacheIdentifierValue = cacheIdentifier;
        return this;
    }

    public ServiceQuery<T, O> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    public ServiceQuery<T, O> queryParam(String name, Object... values) {
        uriBuilder.queryParam(name, values);
        return this;
    }

    public ServiceQuery<T, O> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
