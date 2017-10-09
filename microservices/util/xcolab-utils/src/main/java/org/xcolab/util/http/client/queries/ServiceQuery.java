package org.xcolab.util.http.client.queries;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.interfaces.HttpResource;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public class ServiceQuery<T, R> implements CacheableQuery<T, R> {

    private final UriBuilder uriBuilder;
    private final Class<R> returnType;
    private final ParameterizedTypeReference<List<T>> typeReference;
    private CacheKey<T, R> cacheKey;
    private CacheName cacheName;

    public ServiceQuery(HttpResource httpResource, long id, String serviceName,
            Class<R> returnType) {
        this.returnType = returnType;
        this.typeReference = null;
        this.uriBuilder = httpResource.getResourceUrl(id).path("/" + serviceName);
    }

    public ServiceQuery(HttpResource httpResource, String id, String serviceName,
            Class<R> returnType) {
        this.returnType = returnType;
        this.typeReference = null;
        this.uriBuilder = httpResource.getResourceUrl(id).path("/" + serviceName);
    }

    public ServiceQuery(HttpResource httpResource, long id, Class<R> returnType) {
        this.returnType = returnType;
        this.typeReference = null;
        this.uriBuilder = httpResource.getResourceUrl(id);
    }

    public ServiceQuery(HttpResource httpResource, String serviceNameOrId, Class<R> returnType) {
        this.returnType = returnType;
        this.typeReference = null;
        this.uriBuilder = httpResource.getResourceUrl().path("/" + serviceNameOrId);
    }

    public ServiceQuery(HttpResource httpResource, Class<R> returnType) {
        this.returnType = returnType;
        this.typeReference = null;
        this.uriBuilder = httpResource.getResourceUrl();
    }


    public ServiceQuery(HttpResource httpResource, long id, String serviceName,
            ParameterizedTypeReference<List<T>> typeReference) {
        this.returnType = null;
        this.typeReference = typeReference;
        this.uriBuilder = httpResource.getResourceUrl(id).path("/" + serviceName);
    }

    public ServiceQuery(HttpResource httpResource, String id, String serviceName,
            ParameterizedTypeReference<List<T>> typeReference) {
        this.returnType = null;
        this.typeReference = typeReference;
        this.uriBuilder = httpResource.getResourceUrl(id).path("/" + serviceName);
    }

    public ServiceQuery(HttpResource httpResource, long id,
            ParameterizedTypeReference<List<T>> typeReference) {
        this.typeReference = typeReference;
        this.returnType = null;
        this.uriBuilder = httpResource.getResourceUrl(id);
    }

    public ServiceQuery(HttpResource httpResource, String serviceNameOrId,
            ParameterizedTypeReference<List<T>> typeReference) {
        this.typeReference = typeReference;
        this.returnType = null;
        this.uriBuilder = httpResource.getResourceUrl().path("/" + serviceNameOrId);
    }

    public ServiceQuery(HttpResource httpResource,
            ParameterizedTypeReference<List<T>> typeReference) {
        this.typeReference = typeReference;
        this.returnType = null;
        this.uriBuilder = httpResource.getResourceUrl();
    }

    @Override
    public R execute() {
        return get();
    }

    public R getChecked() throws EntityNotFoundException {
        if (cacheKey == null) {
            return ServiceRequestUtils.get(uriBuilder, returnType);
        } else {
            return ServiceRequestUtils.get(uriBuilder, returnType, cacheKey, cacheName);
        }
    }

    public R get() {
        if (cacheKey == null) {
            return ServiceRequestUtils.getUnchecked(uriBuilder, returnType);
        } else {
            return ServiceRequestUtils.getUnchecked(uriBuilder, returnType, cacheKey, cacheName);
        }
    }

    public List<T> getList() {
        if (cacheKey == null) {
            return ServiceRequestUtils.getList(uriBuilder, typeReference);
        } else {
            //TODO : Implement cache for service lists
            return ServiceRequestUtils.getList(uriBuilder, typeReference);
        }
    }

    public R post() {
        return ServiceRequestUtils.post(uriBuilder, null, returnType);
    }

    public R post(Object pojo) {
        return ServiceRequestUtils.post(uriBuilder, pojo, returnType);
    }

    public boolean put() {
        return ServiceRequestUtils.put(uriBuilder);
    }

    public boolean delete() {
        return ServiceRequestUtils.delete(uriBuilder);
    }

    @Override
    public ServiceQuery<T, R> withCache(CacheKey<T, R> cacheKey, CacheName cacheName) {
        this.cacheKey = cacheKey;
        this.cacheName = cacheName;
        return this;
    }

    @Override
    public Query<T, R> withCache(CacheName cacheName) {
        throw new UnsupportedOperationException(
                "Service queries don't support caching without explicit keys");
    }

    @Override
    public ServiceQuery<T, R> queryParam(String name, Object value) {
        uriBuilder.queryParam(name, value);
        return this;
    }

    @Override
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
