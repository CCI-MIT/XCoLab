package org.xcolab.commons.http.caching.provider;

import org.xcolab.commons.http.caching.CacheCustomization;
import org.xcolab.commons.http.caching.CacheCustomization.DiskStorage;
import org.xcolab.commons.http.caching.CacheKey;
import org.xcolab.commons.http.caching.CacheName;

import java.util.Map;

public class TieredCacheProvider implements CacheProvider {

    private final ConditionalCacheProvider tier1;
    private final CacheProvider tier2;

    public TieredCacheProvider(ConditionalCacheProvider tier1, CacheProvider tier2) {
        this.tier1 = tier1;
        this.tier2 = tier2;
    }

    @Override
    public void init(Map<CacheName, CacheCustomization> customizations, DiskStorage diskStorage) {
        tier1.init(customizations, diskStorage);
        tier2.init(customizations, diskStorage);
    }

    @Override
    public <T> T get(CacheKey<?, T> key, CacheName cacheName) {
        T ret = null;
        if (tier1.accepts(cacheName)) {
            ret = tier1.get(key, cacheName);
        }
        if (ret == null) {
            ret = tier2.get(key, cacheName);
            if (ret != null && tier1.accepts(cacheName)) {
                tier1.add(key, cacheName, ret);
            }
        }
        return ret;
    }

    @Override
    public <T> boolean add(CacheKey<?, T> key, CacheName cacheName, T value) {
        boolean success1 = !tier1.accepts(cacheName) || tier1.add(key, cacheName, value);
        boolean success2 =  tier2.add(key, cacheName, value);
        return success1 && success2;
    }

    @Override
    public <T> boolean replace(CacheKey<?, T> key, CacheName cacheName, T value) {
        boolean success1 = !tier1.accepts(cacheName) || tier1.replace(key, cacheName, value);
        boolean success2 =  tier2.replace(key, cacheName, value);
        return success1 && success2;
    }

    @Override
    public boolean delete(CacheKey<?, ?> key, CacheName cacheName) {
        boolean success1 = !tier1.accepts(cacheName) || tier1.delete(key, cacheName);
        boolean success2 =  tier2.delete(key, cacheName);
        return success1 && success2;
    }

    @Override
    public boolean isActive() {
        return tier1.isActive() || tier2.isActive();
    }

    @Override
    public void clear() {
        tier1.clear();
        tier2.clear();
    }

    @Override
    public void clear(CacheName cacheName) {
        if (tier1.accepts(cacheName)) {
            tier1.clear();
        }
        tier2.clear();
    }
}
