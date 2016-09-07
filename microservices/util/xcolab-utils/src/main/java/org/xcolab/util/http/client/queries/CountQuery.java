package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;

public class CountQuery<T> implements CacheableQuery<T, Integer> {
    private final UriBuilder uriBuilder;
    private CacheKey<T, Integer> cacheKey;
    private CacheRetention cacheRetention;

    public CountQuery(RestResource<T> restResource) {
        this.uriBuilder = restResource.getResourceUrl();
    }

    @Override
    public Integer execute() {
        if (cacheKey == null) {
            return RequestUtils.getCount(uriBuilder);
        } else {
            return RequestUtils.getCount(uriBuilder, cacheKey, cacheRetention);
        }
    }

    @Override
    public CountQuery<T> withCache(CacheKey<T, Integer> cacheKey, CacheRetention cacheRetention) {
        this.cacheKey = cacheKey;
        this.cacheRetention = cacheRetention;
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
