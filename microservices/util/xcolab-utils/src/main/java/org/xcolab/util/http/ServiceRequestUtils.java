package org.xcolab.util.http;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheProvider;
import org.xcolab.util.http.caching.CacheProviderEhcacheImpl;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.util.http.exceptions.translation.TranslationErrorHandler;
import org.xcolab.util.http.exceptions.translation.service.ServiceExceptionTranslator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public final class ServiceRequestUtils {

    private static final RequestHelper requestHelper;

    private static String servicesPort;
    private static Boolean cacheActive;

    static {
        readProperties();
        requestHelper = new RequestHelper(new TranslationErrorHandler(new ServiceExceptionTranslator()));
        requestHelper.setCacheActive(cacheActive);
        if (cacheActive) {
            requestHelper.setCacheProvider(new CacheProviderEhcacheImpl());
        }
    }

    private ServiceRequestUtils() {
    }

    public static <R> List<R> getList(UriBuilder uriBuilder,
            ParameterizedTypeReference<List<R>> typeReference) {
        return requestHelper.getList(uriBuilder, typeReference);
    }

    public static <T, R> List<R> getList(UriBuilder uriBuilder,
            ParameterizedTypeReference<List<R>> typeReference,
            CacheKey<T, List<R>> cacheKey,
            CacheName cacheName) {
        return requestHelper.getList(uriBuilder, typeReference, cacheKey, cacheName);
    }

    public static <T> T get(UriBuilder uriBuilder, Class<T> entityType)
            throws EntityNotFoundException {
        return requestHelper.get(uriBuilder, entityType);
    }

    public static <T, R> R get(UriBuilder uriBuilder, Class<R> entityType,
            CacheKey<T, R> cacheKey,
            CacheName cacheName) throws EntityNotFoundException {
        return requestHelper.get(uriBuilder, entityType, cacheKey, cacheName);
    }

    public static <R> R getUnchecked(UriBuilder uriBuilder, Class<R> returnType) {
        return requestHelper.getUnchecked(uriBuilder, returnType);
    }

    public static <T, R> R getUnchecked(UriBuilder uriBuilder, Class<R> returnType,
            CacheKey<T, R> cacheKey,
            CacheName cacheName) {
        return requestHelper.getUnchecked(uriBuilder, returnType, cacheKey, cacheName);
    }

    public static int getCount(UriBuilder uriBuilder) {
        return requestHelper.getCount(uriBuilder);
    }

    public static int getCount(UriBuilder uriBuilder,
            CacheKey<?, Integer> cacheKey,
            CacheName cacheName) {
        return requestHelper.getCount(uriBuilder, cacheKey, cacheName);
    }

    public static boolean put(UriBuilder uriBuilder) {
        return requestHelper.put(uriBuilder);
    }

    public static <T> boolean put(UriBuilder uriBuilder, T entity) {
        return requestHelper.put(uriBuilder, entity);
    }

    public static <T> boolean put(UriBuilder uriBuilder, T entity,
            CacheKey<T, T> cacheKey, CacheName cacheName) {
        return requestHelper.put(uriBuilder, entity, cacheKey, cacheName);
    }

    public static boolean delete(UriBuilder uriBuilder) {
        return requestHelper.delete(uriBuilder);
    }

    public static <T> boolean delete(UriBuilder uriBuilder, CacheKey<T, T> cacheKey, CacheName cacheName) {
        return requestHelper.delete(uriBuilder, cacheKey, cacheName);
    }

    public static <T> T post(UriBuilder uriBuilder, Object entity, Class<T> returnType) {
        return requestHelper.post(uriBuilder, entity, returnType);
    }

    public static void invalidateCache(CacheKey<?, ?> cacheKey,
            CacheName cacheName) {
        requestHelper.invalidateCache(cacheKey, cacheName);
    }

    public static void clearCache() {
        requestHelper.clearCache();
    }

    public static void clearCache(CacheName cacheName) {
        requestHelper.clearCache(cacheName);
    }

    public static void setCacheProvider(CacheProvider cacheProvider) {
        requestHelper.setCacheProvider(cacheProvider);
    }

    public static String getServicesPort() {

        if (servicesPort == null) {
            readProperties();
        }
        return servicesPort;
    }

    private static void readProperties() {
        final String propertiesPath = System.getProperty("user.home") + File.separator
                + ".xcolab.application.properties";

        try (InputStream inputStream = new FileInputStream(propertiesPath)) {
            Properties prop = new Properties();
            prop.load(inputStream);
            servicesPort = prop.getProperty("services.port");
            cacheActive = Boolean.valueOf(prop.getProperty("cache.active", "true"));
        } catch (IOException e) {
            servicesPort = "8080";
            cacheActive = true;
        }
    }
}
