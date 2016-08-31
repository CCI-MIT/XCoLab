package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CachingStrategy;
import org.xcolab.util.http.client.interfaces.HttpResource;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class ServiceQuery<T> implements CacheableQuery<T, T> {
    private final UriBuilder uriBuilder;
    private final Class<T> entityType;
    private String cacheIdentifierValue;
    private CachingStrategy cachingStrategy;

    public ServiceQuery(HttpResource httpResource, long id, String serviceName,
            Class<T> entityType) {
        this.entityType = entityType;
        this.uriBuilder = httpResource.getResourceUrl(id).path("/" + serviceName);
    }

    public ServiceQuery(HttpResource httpResource, String id, String serviceName,
            Class<T> entityType) {
        this.entityType = entityType;
        this.uriBuilder = httpResource.getResourceUrl(id).path("/" + serviceName);
    }

    public ServiceQuery(HttpResource httpResource, long id, Class<T> entityType) {
        this.entityType = entityType;
        this.uriBuilder = httpResource.getResourceUrl(id);
    }

    public ServiceQuery(HttpResource httpResource, String serviceNameOrId,
            Class<T> entityType) {
        this.entityType = entityType;
        this.uriBuilder = httpResource.getResourceUrl().path("/" + serviceNameOrId);
    }

    public ServiceQuery(HttpResource httpResource, Class<T> entityType) {
        this.entityType = entityType;
        this.uriBuilder = httpResource.getResourceUrl();
    }

    @Override
    public T execute() {
        return get();
    }

    public T getChecked() throws EntityNotFoundException {
        if (cacheIdentifierValue == null) {
            return RequestUtils.get(uriBuilder, entityType);
        } else {
            return RequestUtils.get(uriBuilder, entityType, cacheIdentifierValue,
                    CachingStrategy.REQUEST);
        }
    }

    public T get() {
        if (cacheIdentifierValue == null) {
            return RequestUtils.getUnchecked(uriBuilder, entityType);
        } else {
            return RequestUtils.getUnchecked(uriBuilder, entityType, cacheIdentifierValue, cachingStrategy);
        }
    }

    public T post() {
        return RequestUtils.post(uriBuilder, null, entityType);
    }

    public T post(Object pojo) {
        return RequestUtils.post(uriBuilder, pojo, entityType);
    }

    public boolean put() {
        return RequestUtils.put(uriBuilder);
    }

    public boolean delete() {
        return RequestUtils.delete(uriBuilder);
    }

    @Override
    public ServiceQuery<T> withCache(String cacheIdentifier, CachingStrategy cachingStrategy) {
        this.cacheIdentifierValue = cacheIdentifier;
        this.cachingStrategy = cachingStrategy;
        return this;
    }

    @Override
    public ServiceQuery<T> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    public ServiceQuery<T> queryParam(String name, Object... values) {
        uriBuilder.queryParam(name, values);
        return this;
    }

    @Override
    public ServiceQuery<T> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
