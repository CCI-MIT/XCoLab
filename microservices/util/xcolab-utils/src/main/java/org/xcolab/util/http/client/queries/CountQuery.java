package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CachingStrategy;
import org.xcolab.util.http.client.RestResource;

public class CountQuery<T> implements CacheableQuery<T, Integer> {
    private final UriBuilder uriBuilder;
    private CacheKey<T, Integer> cacheKey;
    private CachingStrategy cachingStrategy;

    public CountQuery(RestResource<T> restResource) {
        this.uriBuilder = restResource.getResourceUrl();
    }

    @Override
    public Integer execute() {
        if (cacheKey == null) {
            return RequestUtils.getCount(uriBuilder);
        } else {
            return RequestUtils.getCount(uriBuilder, cacheKey, cachingStrategy);
        }
    }

    @Override
    public CountQuery<T> withCache(CacheKey<T, Integer> cacheKey, CachingStrategy cachingStrategy) {
        this.cacheKey = cacheKey;
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
