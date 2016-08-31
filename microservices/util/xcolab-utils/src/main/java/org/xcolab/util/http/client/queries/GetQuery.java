package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CachingStrategy;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class GetQuery<T> implements CacheableQuery<T, T> {
    private final UriBuilder uriBuilder;
    private final Class<T> entityType;
    private String cacheIdentifierValue;
    private CachingStrategy cachingStrategy;

    public GetQuery(RestResource<T> restResource, long id, Class<T> entityType) {
        this.entityType = entityType;
        this.uriBuilder = restResource.getResourceUrl(id);
    }

    public GetQuery(RestResource<T> restResource, String id, Class<T> entityType) {
        this.entityType = entityType;
        this.uriBuilder = restResource.getResourceUrl(id);
    }

    @Override
    public T execute() {
        if (cacheIdentifierValue == null) {
            return RequestUtils.getUnchecked(uriBuilder, entityType);
        } else {
            return RequestUtils.getUnchecked(uriBuilder, entityType, cacheIdentifierValue, cachingStrategy);
        }
    }

    public T executeChecked() throws EntityNotFoundException {
        if (cacheIdentifierValue == null) {
            return RequestUtils.get(uriBuilder, entityType);
        } else {
            return RequestUtils.get(uriBuilder, entityType, cacheIdentifierValue, cachingStrategy);
        }
    }

    @Override
    public GetQuery<T> withCache(String cacheIdentifier, CachingStrategy cachingStrategy) {
        this.cacheIdentifierValue = cacheIdentifier;
        this.cachingStrategy = cachingStrategy;
        return this;
    }

    @Override
    public GetQuery<T> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public GetQuery<T> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
