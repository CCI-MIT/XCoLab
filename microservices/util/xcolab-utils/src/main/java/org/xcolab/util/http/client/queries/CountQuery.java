package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;

public class CountQuery<ElementT> implements CacheableQuery<ElementT, Integer> {
    private final UriBuilder uriBuilder;
    private CacheKey<ElementT, Integer> cacheKey;
    private CacheRetention cacheRetention;

    public CountQuery(RestResource<ElementT, ?> restResource) {
        this.uriBuilder = restResource.getResourceUrl();
    }

    @Override
    public Integer execute() {
        if (cacheKey == null) {
            return ServiceRequestUtils.getCount(uriBuilder);
        } else {
            return ServiceRequestUtils.getCount(uriBuilder, cacheKey, cacheRetention);
        }
    }

    @Override
    public CountQuery<ElementT> withCache(CacheKey<ElementT, Integer> cacheKey, CacheRetention cacheRetention) {
        this.cacheKey = cacheKey;
        this.cacheRetention = cacheRetention;
        return this;
    }

    @Override
    public CountQuery<ElementT> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public CountQuery<ElementT> queryParam(String name, Object... value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public CountQuery<ElementT> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
