package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class GetQuery<ElementT, IdT> implements CacheableQuery<ElementT, ElementT> {
    private final UriBuilder uriBuilder;
    private final IdT id;
    private final Class<ElementT> entityType;
    private CacheKey<ElementT, ElementT> cacheKey;
    private CacheName cacheName;

    public GetQuery(RestResource<ElementT, IdT> restResource, IdT id, Class<ElementT> entityType) {
        this.id = id;
        this.entityType = entityType;
        this.uriBuilder = restResource.getResourceUrl(id);
    }

    @Override
    public ElementT execute() {
        if (cacheName == null) {
            return ServiceRequestUtils.getUnchecked(uriBuilder, entityType);
        } else {
            if (cacheKey == null) {
                cacheKey = CacheKeys.of(entityType, id);
            }
            return ServiceRequestUtils.getUnchecked(uriBuilder, entityType, cacheKey, cacheName);
        }
    }

    public ElementT executeChecked() throws EntityNotFoundException {
        if (cacheName == null) {
            return ServiceRequestUtils.get(uriBuilder, entityType);
        } else {
            if (cacheKey == null) {
                cacheKey = CacheKeys.of(entityType, id);
            }
            return ServiceRequestUtils.get(uriBuilder, entityType, cacheKey, cacheName);
        }
    }

    public void deleteFromCache() {
        if (cacheName != null) {
            if (cacheKey == null) {
                cacheKey = CacheKeys.of(entityType, id);
            }
            ServiceRequestUtils.deleteFromCache(cacheKey, cacheName);
        }
    }

    @Override
    public GetQuery<ElementT, IdT> withCache(CacheKey<ElementT, ElementT> cacheKey, CacheName cacheName) {
        this.cacheKey = cacheKey;
        this.cacheName = cacheName;
        return this;
    }

    @Override
    public GetQuery<ElementT, IdT> withCache(CacheName cacheName) {
        this.cacheName = cacheName;
        return this;
    }

    @Override
    public GetQuery<ElementT, IdT> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public GetQuery<ElementT, IdT> queryParam(String name, Object... value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public GetQuery<ElementT, IdT> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
