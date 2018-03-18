package org.xcolab.commons.http.caching.provider;

import org.xcolab.commons.http.caching.CacheCustomization;
import org.xcolab.commons.http.caching.CacheCustomization.DiskStorage;
import org.xcolab.commons.http.caching.CacheKey;
import org.xcolab.commons.http.caching.CacheName;

import java.util.Map;

public interface CacheProvider {

    void init(Map<CacheName, CacheCustomization> customizations, DiskStorage diskStorage);

    <T> T get(CacheKey<?, T> key, CacheName cacheName);

    <T> boolean add(CacheKey<?, T> key, CacheName cacheName, T value);

    <T> boolean replace(CacheKey<?, T> key, CacheName cacheName, T value);

    boolean delete(CacheKey<?, ?> key, CacheName cacheName);

    boolean isActive();

    default void clear() {
        for (CacheName cacheName : CacheName.values()) {
            clear(cacheName);
        }
    }

    void clear(CacheName cacheName);
}
