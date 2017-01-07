package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class GetQuery<ElementT, IdT> implements CacheableQuery<ElementT, ElementT> {
    private final UriBuilder uriBuilder;
    private final Class<ElementT> entityType;
    private CacheKey<ElementT, ElementT> cacheKey;
    private CacheRetention cacheRetention;

    public GetQuery(RestResource<ElementT, IdT> restResource, IdT id, Class<ElementT> entityType) {
        this.entityType = entityType;
        this.uriBuilder = restResource.getResourceUrl(id);
    }

    @Override
    public ElementT execute() {
        if (cacheKey == null) {
            return ServiceRequestUtils.getUnchecked(uriBuilder, entityType);
        } else {
            return ServiceRequestUtils.getUnchecked(uriBuilder, entityType, cacheKey, cacheRetention);
        }
    }

    public ElementT executeChecked() throws EntityNotFoundException {
        if (cacheKey == null) {
            return ServiceRequestUtils.get(uriBuilder, entityType);
        } else {
            return ServiceRequestUtils.get(uriBuilder, entityType, cacheKey, cacheRetention);
        }
    }

    @Override
    public GetQuery<ElementT, IdT> withCache(CacheKey<ElementT, ElementT> cacheKey, CacheRetention cacheRetention) {
        this.cacheKey = cacheKey;
        this.cacheRetention = cacheRetention;
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
