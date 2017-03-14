package org.xcolab.util.http;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.caching.provider.CacheProvider;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.util.http.exceptions.EurekaNotInitializedException;
import org.xcolab.util.http.exceptions.translation.TranslationErrorHandler;
import org.xcolab.util.http.exceptions.translation.service.ServiceExceptionTranslator;

import java.util.List;

public final class ServiceRequestUtils {

    private static final RequestHelper requestHelper;
    private static String namespace;
    private static boolean isInitialized;

    static {
        requestHelper = new RequestHelper(new TranslationErrorHandler(new ServiceExceptionTranslator()));
    }

    private ServiceRequestUtils() {
    }

    public static void setCacheProvider(CacheProvider cacheProvider) {
        requestHelper.setCacheProvider(cacheProvider);
    }

    public static void initialize(RestTemplate restTemplate, String namespace) {
        requestHelper.setRestTemplate(restTemplate);
        ServiceRequestUtils.namespace = namespace;
        isInitialized = true;
    }

    private static void checkInitialized() {
        if (!isInitialized) {
            throw new EurekaNotInitializedException();
        }
    }

    public static <R> List<R> getList(UriBuilder uriBuilder,
            ParameterizedTypeReference<List<R>> typeReference) {
        checkInitialized();
        return requestHelper.getList(uriBuilder, typeReference);
    }

    public static <T, R> List<R> getList(UriBuilder uriBuilder,
            ParameterizedTypeReference<List<R>> typeReference,
            CacheKey<T, List<R>> cacheKey,
            CacheName cacheName) {
        checkInitialized();
        return requestHelper.getList(uriBuilder, typeReference, cacheKey, cacheName);
    }

    public static <T> T get(UriBuilder uriBuilder, Class<T> entityType)
            throws EntityNotFoundException {
        checkInitialized();
        return requestHelper.get(uriBuilder, entityType);
    }

    public static <T, R> R get(UriBuilder uriBuilder, Class<R> entityType,
            CacheKey<T, R> cacheKey,
            CacheName cacheName) throws EntityNotFoundException {
        checkInitialized();
        return requestHelper.get(uriBuilder, entityType, cacheKey, cacheName);
    }

    public static <R> R getUnchecked(UriBuilder uriBuilder, Class<R> returnType) {
        checkInitialized();
        return requestHelper.getUnchecked(uriBuilder, returnType);
    }

    public static <T, R> R getUnchecked(UriBuilder uriBuilder, Class<R> returnType,
            CacheKey<T, R> cacheKey,
            CacheName cacheName) {
        checkInitialized();
        return requestHelper.getUnchecked(uriBuilder, returnType, cacheKey, cacheName);
    }

    public static int getCount(UriBuilder uriBuilder) {
        checkInitialized();
        return requestHelper.getCount(uriBuilder);
    }

    public static int getCount(UriBuilder uriBuilder,
            CacheKey<?, Integer> cacheKey,
            CacheName cacheName) {
        checkInitialized();
        return requestHelper.getCount(uriBuilder, cacheKey, cacheName);
    }

    public static boolean put(UriBuilder uriBuilder) {
        checkInitialized();
        return requestHelper.put(uriBuilder);
    }

    public static <T> boolean put(UriBuilder uriBuilder, T entity) {
        checkInitialized();
        return requestHelper.put(uriBuilder, entity);
    }

    public static <T> boolean put(UriBuilder uriBuilder, T entity,
            CacheKey<T, T> cacheKey, CacheName cacheName) {
        checkInitialized();
        return requestHelper.put(uriBuilder, entity, cacheKey, cacheName);
    }

    public static boolean delete(UriBuilder uriBuilder) {
        checkInitialized();
        return requestHelper.delete(uriBuilder);
    }

    public static void deleteFromCache(CacheKey<?, ?> cacheKey, CacheName cacheName) {
        checkInitialized();
        requestHelper.deleteFromCache(cacheKey, cacheName);
    }

    public static <T> boolean delete(UriBuilder uriBuilder, CacheKey<T, T> cacheKey, CacheName cacheName) {
        checkInitialized();
        return requestHelper.delete(uriBuilder, cacheKey, cacheName);
    }

    public static <T> T post(UriBuilder uriBuilder, Object entity, Class<T> returnType) {
        checkInitialized();
        return requestHelper.post(uriBuilder, entity, returnType);
    }

    public static void invalidateCache(CacheKey<?, ?> cacheKey,
            CacheName cacheName) {
        checkInitialized();
        requestHelper.invalidateCache(cacheKey, cacheName);
    }

    public static void clearCache() {
        checkInitialized();
        requestHelper.clearCache();
    }

    public static void clearCache(CacheName cacheName) {
        checkInitialized();
        requestHelper.clearCache(cacheName);
    }

    public static String getNamespace() {
        checkInitialized();
        return namespace;
    }

    public static boolean isInitialized() {
        return isInitialized;
    }
}
