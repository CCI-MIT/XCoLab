package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.client.RestResource;

public class UpdateQuery<T, IdT> implements Query<T, Boolean> {

    private final UriBuilder uriBuilder;
    private final T pojo;
    private CacheKey<T, T> cacheKey;

    public UpdateQuery(RestResource<T, IdT> restResource, IdT id, T pojo) {
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

    public UpdateQuery<T, IdT> cacheKey(CacheKey<T, T> cacheKey) {
        this.cacheKey = cacheKey;
        return this;
    }

    @Override
    public UpdateQuery<T, IdT> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public UpdateQuery<T, IdT> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
