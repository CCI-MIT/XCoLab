package org.xcolab.util.http.client.queries;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.interfaces.HttpResource;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

/**
 * Represents a query that does not follow the standard REST interface.
 *
 * Service queries come in the form of "services" and simple "queries".
 * A service is a named function on either an element or a collection while a query is a
 * non-standard request on an element or collection URL.
 */
public class ServiceQuery<T, R> implements CacheableQuery<T, R> {

    private final UriBuilder uriBuilder;
    private final Class<R> returnType;
    private final ParameterizedTypeReference<List<T>> typeReference;
    private CacheKey<T, R> cacheKey;
    private CacheName cacheName;

    private ServiceQuery(UriBuilder uriBuilder, Class<R> returnType) {
        this.uriBuilder = uriBuilder;
        this.returnType = returnType;
        this.typeReference = null;
    }

    private ServiceQuery(UriBuilder uriBuilder, ParameterizedTypeReference<List<T>> typeReference) {
        this.uriBuilder = uriBuilder;
        this.returnType = null;
        this.typeReference = typeReference;
    }

    public static <T, R> ServiceQuery<T, R> createElementService(HttpResource httpResource,
            Object id, String serviceName, Class<R> returnType) {
        return new ServiceQuery<>(httpResource.getResourceUrl(id).path("/" + serviceName),
                returnType);
    }

    public static <T, R> ServiceQuery<T, R> createElementService(HttpResource httpResource,
            Object id, String serviceName, ParameterizedTypeReference<List<T>> typeReference) {
        return new ServiceQuery<>(httpResource.getResourceUrl(id).path("/" + serviceName),
                typeReference);
    }

    public static <T, R> ServiceQuery<T, R> createElementQuery(HttpResource httpResource, Object id,
            Class<R> returnType) {
        return new ServiceQuery<>(httpResource.getResourceUrl(id), returnType);
    }

    public static <T, R> ServiceQuery<T, R> createElementQuery(HttpResource httpResource, Object id,
            ParameterizedTypeReference<List<T>> typeReference) {
        return new ServiceQuery<>(httpResource.getResourceUrl(id), typeReference);
    }

    public static <T, R> ServiceQuery<T, R> createCollectionService(HttpResource httpResource,
            String serviceName, Class<R> returnType) {
        return new ServiceQuery<>(httpResource.getResourceUrl().path("/" + serviceName),
                returnType);
    }

    public static <T, R> ServiceQuery<T, R> createCollectionService(HttpResource httpResource,
            String serviceName, ParameterizedTypeReference<List<T>> typeReference) {
        return new ServiceQuery<>(httpResource.getResourceUrl().path("/" + serviceName),
                typeReference);
    }

    public static <T, R> ServiceQuery<T, R> createCollectionQuery(HttpResource httpResource,
            Class<R> returnType) {
        return new ServiceQuery<>(httpResource.getResourceUrl(), returnType);
    }

    public static <T, R> ServiceQuery<T, R> createCollectionQuery(HttpResource httpResource,
            ParameterizedTypeReference<List<T>> typeReference) {
        return new ServiceQuery<>(httpResource.getResourceUrl(), typeReference);
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
            //TODO COLAB-2589: Implement cache for service lists
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
