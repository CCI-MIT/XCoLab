package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CachingStrategy;
import org.xcolab.util.http.client.RestResource;

public class CountQuery<T> implements CacheableQuery<T, Integer> {
    private final UriBuilder uriBuilder;
    private final Class<T> entityType;
    private String cacheIdentifierValue;
    private CachingStrategy cachingStrategy;

    public CountQuery(RestResource<T> restResource, Class<T> entityType) {
        this.entityType = entityType;
        this.uriBuilder = restResource.getResourceUrl();
    }

    @Override
    public Integer execute() {
        if (cacheIdentifierValue == null) {
            return RequestUtils.getCount(uriBuilder);
        } else {
            return RequestUtils.getCount(uriBuilder, entityType, cacheIdentifierValue, cachingStrategy);
        }
    }

    @Override
    public CountQuery<T> withCache(String cacheIdentifier, CachingStrategy cachingStrategy) {
        this.cacheIdentifierValue = cacheIdentifier;
        this.cachingStrategy = cachingStrategy;
        return this;
    }

    @Override
    public CountQuery<T> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public CountQuery<T> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
