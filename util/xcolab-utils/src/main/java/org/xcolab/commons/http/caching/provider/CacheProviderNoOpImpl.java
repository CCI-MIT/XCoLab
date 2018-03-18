package org.xcolab.commons.http.caching.provider;

import org.xcolab.commons.http.caching.CacheCustomization;
import org.xcolab.commons.http.caching.CacheCustomization.DiskStorage;
import org.xcolab.commons.http.caching.CacheKey;
import org.xcolab.commons.http.caching.CacheName;

import java.util.Map;

public class CacheProviderNoOpImpl implements CacheProvider {

    @Override
    public void init(Map<CacheName, CacheCustomization> customizations, DiskStorage diskStorage) {
    }

    @Override
    public <T> T get(CacheKey<?, T> key, CacheName cacheName) {
        return null;
    }

    @Override
    public <T> boolean add(CacheKey<?, T> key, CacheName cacheName, T value) {
        return false;
    }

    @Override
    public <T> boolean replace(CacheKey<?, T> key, CacheName cacheName, T value) {
        return false;
    }

    @Override
    public boolean delete(CacheKey<?, ?> key, CacheName cacheName) {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public void clear(CacheName cacheName) {
    }
}
