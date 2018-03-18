package org.xcolab.commons.http.client.queries;

import org.xcolab.commons.http.ServiceRequestUtils;
import org.xcolab.commons.http.UriBuilder;
import org.xcolab.commons.http.caching.CacheKey;
import org.xcolab.commons.http.caching.CacheKeys;
import org.xcolab.commons.http.caching.CacheName;
import org.xcolab.commons.http.client.RestResource;

public class UpdateQuery<ElementT, IdT> implements Query<ElementT, Boolean> {

    private final UriBuilder uriBuilder;
    private final Class<ElementT> entityType;
    private final IdT id;
    private final ElementT pojo;
    private CacheKey<ElementT, ElementT> cacheKey;
    private CacheName cacheName;

    public UpdateQuery(RestResource<ElementT, IdT> restResource, Class<ElementT> entityType, IdT id,
            ElementT pojo) {
        this.entityType = entityType;
        this.id = id;
        this.pojo = pojo;
        this.uriBuilder = restResource.getResourceUrl(id);
    }

    @Override
    public Boolean execute() {
        if (cacheName == null) {
            if (cacheKey != null) {
                return ServiceRequestUtils.put(uriBuilder, pojo, cacheKey, CacheName.MISC_REQUEST);
            } else {
                return ServiceRequestUtils.put(uriBuilder, pojo);
            }
        } else {
            if (cacheKey == null) {
                cacheKey = CacheKeys.of(entityType, id);
            }
            return ServiceRequestUtils.put(uriBuilder, pojo, cacheKey, cacheName);
        }
    }

    public UpdateQuery<ElementT, IdT> cacheKey(CacheKey<ElementT, ElementT> cacheKey) {
        this.cacheKey = cacheKey;
        return this;
    }

    public UpdateQuery<ElementT, IdT> cacheName(CacheName cacheName) {
        this.cacheName = cacheName;
        return this;
    }

    @Override
    public UpdateQuery<ElementT, IdT> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public UpdateQuery<ElementT, IdT> queryParam(String name, Object... value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public UpdateQuery<ElementT, IdT> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
