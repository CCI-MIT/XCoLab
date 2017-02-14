package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;

public class CountQuery<ElementT> implements CacheableQuery<ElementT, Integer> {
    private final UriBuilder uriBuilder;
    private final Class<ElementT> entityType;
    private CacheKey<ElementT, Integer> cacheKey;
    private CacheName cacheName;

    public CountQuery(RestResource<ElementT, ?> restResource, Class<ElementT> entityType) {
        this.uriBuilder = restResource.getResourceUrl();
        this.entityType = entityType;
    }

    @Override
    public Integer execute() {
        if (cacheName == null) {
            return ServiceRequestUtils.getCount(uriBuilder);
        } else {
            if (cacheKey == null) {
                cacheKey = CacheKeys.withClass(entityType)
                        .withParameter("_countUrl", uriBuilder.getParameterString()).asCount();
            }
            return ServiceRequestUtils.getCount(uriBuilder, cacheKey, cacheName);
        }
    }

    @Override
    public CountQuery<ElementT> withCache(CacheKey<ElementT, Integer> cacheKey, CacheName cacheName) {
        this.cacheKey = cacheKey;
        this.cacheName = cacheName;
        return this;
    }

    @Override
    public CountQuery<ElementT> withCache(CacheName cacheName) {
        this.cacheName = cacheName;
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
