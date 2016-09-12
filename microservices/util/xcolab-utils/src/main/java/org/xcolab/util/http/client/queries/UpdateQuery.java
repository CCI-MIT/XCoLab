package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.client.RestResource;

public class UpdateQuery<ElementT> implements Query<ElementT, Boolean> {

    private final UriBuilder uriBuilder;
    private final ElementT pojo;
    private CacheKey<ElementT, ElementT> cacheKey;

    public UpdateQuery(RestResource<ElementT> restResource, long id, ElementT pojo) {
        this.pojo = pojo;
        this.uriBuilder = restResource.getResourceUrl(id);
    }

    public UpdateQuery(RestResource<ElementT> restResource, String id, ElementT pojo) {
        this.pojo = pojo;
        this.uriBuilder = restResource.getResourceUrl(id);
    }

    @Override
    public Boolean execute() {
        if (cacheKey == null) {
            return RequestUtils.put(uriBuilder, pojo);
        } else {
            return RequestUtils.put(uriBuilder, pojo, cacheKey);
        }
    }

    public UpdateQuery<ElementT> cacheKey(CacheKey<ElementT, ElementT> cacheKey) {
        this.cacheKey = cacheKey;
        return this;
    }

    @Override
    public UpdateQuery<ElementT> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public UpdateQuery<ElementT> queryParam(String name, Object... value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public UpdateQuery<ElementT> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
