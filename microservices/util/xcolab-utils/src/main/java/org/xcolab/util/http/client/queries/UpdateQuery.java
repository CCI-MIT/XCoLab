package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.client.RestResource;

public class UpdateQuery<ElementT, IdT> implements Query<ElementT, Boolean> {

    private final UriBuilder uriBuilder;
    private final ElementT pojo;
    private CacheKey<ElementT, ElementT> cacheKey;

    public UpdateQuery(RestResource<ElementT, IdT> restResource, IdT id, ElementT pojo) {
        this.pojo = pojo;
        this.uriBuilder = restResource.getResourceUrl(id);
    }

    @Override
    public Boolean execute() {
        if (cacheKey == null) {
            return ServiceRequestUtils.put(uriBuilder, pojo);
        } else {
            return ServiceRequestUtils.put(uriBuilder, pojo, cacheKey);
        }
    }

    public UpdateQuery<ElementT, IdT> cacheKey(CacheKey<ElementT, ElementT> cacheKey) {
        this.cacheKey = cacheKey;
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
