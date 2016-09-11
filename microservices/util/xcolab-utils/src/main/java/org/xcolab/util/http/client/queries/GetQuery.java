package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class GetQuery<T> implements CacheableQuery<T, T> {
    private final UriBuilder uriBuilder;
    private final Class<T> entityType;
    private CacheKey<T, T> cacheKey;
    private CacheRetention cacheRetention;

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
        if (cacheKey == null) {
            return RequestUtils.getUnchecked(uriBuilder, entityType);
        } else {
            return RequestUtils.getUnchecked(uriBuilder, entityType, cacheKey, cacheRetention);
        }
    }

    public T executeChecked() throws EntityNotFoundException {
        if (cacheKey == null) {
            return RequestUtils.get(uriBuilder, entityType);
        } else {
            return RequestUtils.get(uriBuilder, entityType, cacheKey, cacheRetention);
        }
    }

    @Override
    public GetQuery<T> withCache(CacheKey<T, T> cacheKey, CacheRetention cacheRetention) {
        this.cacheKey = cacheKey;
        this.cacheRetention = cacheRetention;
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
