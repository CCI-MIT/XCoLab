package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;

public class DeleteQuery<ElementT, IdT> implements Query<ElementT, Boolean> {

    private final UriBuilder uriBuilder;
    private final Class<ElementT> entityType;
    private final IdT id;
    private CacheName cacheName;

    public DeleteQuery(RestResource<ElementT, IdT> restResource, Class<ElementT> entityType, IdT id) {
        this.entityType = entityType;
        this.id = id;
        this.uriBuilder = restResource.getResourceUrl(id);
    }

    @Override
    public Boolean execute() {
        if (cacheName == null) {
            return ServiceRequestUtils.delete(uriBuilder);
        } else {
            return ServiceRequestUtils.delete(uriBuilder, CacheKeys.of(entityType, id), cacheName);
        }
    }

    public DeleteQuery<ElementT, IdT> cacheName(CacheName cacheName) {
        this.cacheName = cacheName;
        return this;
    }

    @Override
    public DeleteQuery<ElementT, IdT> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public DeleteQuery<ElementT, IdT> queryParam(String name, Object... value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public DeleteQuery<ElementT, IdT> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
