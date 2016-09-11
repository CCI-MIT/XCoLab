package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.interfaces.HttpResource;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class ServiceQuery<T, R> implements CacheableQuery<T, R> {
    private final UriBuilder uriBuilder;
    private final Class<R> returnType;
    private CacheKey<T, R> cacheKey;
    private CacheRetention cacheRetention;

    public ServiceQuery(HttpResource httpResource, long id, String serviceName,
            Class<R> returnType) {
        this.returnType = returnType;
        this.uriBuilder = httpResource.getResourceUrl(id).path("/" + serviceName);
    }

    public ServiceQuery(HttpResource httpResource, String id, String serviceName,
            Class<R> returnType) {
        this.returnType = returnType;
        this.uriBuilder = httpResource.getResourceUrl(id).path("/" + serviceName);
    }

    public ServiceQuery(HttpResource httpResource, long id, Class<R> returnType) {
        this.returnType = returnType;
        this.uriBuilder = httpResource.getResourceUrl(id);
    }

    public ServiceQuery(HttpResource httpResource, String serviceNameOrId,
            Class<R> returnType) {
        this.returnType = returnType;
        this.uriBuilder = httpResource.getResourceUrl().path("/" + serviceNameOrId);
    }

    public ServiceQuery(HttpResource httpResource, Class<R> returnType) {
        this.returnType = returnType;
        this.uriBuilder = httpResource.getResourceUrl();
    }

    @Override
    public R execute() {
        return get();
    }

    public R getChecked() throws EntityNotFoundException {
        if (cacheKey == null) {
            return RequestUtils.get(uriBuilder, returnType);
        } else {
            return RequestUtils.get(uriBuilder, returnType, cacheKey, CacheRetention.REQUEST);
        }
    }

    public R get() {
        if (cacheKey == null) {
            return RequestUtils.getUnchecked(uriBuilder, returnType);
        } else {
            return RequestUtils.getUnchecked(uriBuilder, returnType, cacheKey, cacheRetention);
        }
    }

    public R post() {
        return RequestUtils.post(uriBuilder, null, returnType);
    }

    public R post(Object pojo) {
        return RequestUtils.post(uriBuilder, pojo, returnType);
    }

    public boolean put() {
        return RequestUtils.put(uriBuilder);
    }

    public boolean delete() {
        return RequestUtils.delete(uriBuilder);
    }

    @Override
    public ServiceQuery<T, R> withCache(CacheKey<T, R> cacheKey, CacheRetention cacheRetention) {
        this.cacheKey = cacheKey;
        this.cacheRetention = cacheRetention;
        return this;
    }

    @Override
    public ServiceQuery<T, R> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    public ServiceQuery<T, R> queryParam(String name, Object... values) {
        uriBuilder.queryParam(name, values);
        return this;
    }

    @Override
    public ServiceQuery<T, R> optionalQueryParam(String name, Object value) {
        uriBuilder.optionalQueryParam(name, value);
        return this;
    }
}
