package org.xcolab.commons.http.caching.provider.redis;

import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import org.xcolab.commons.http.caching.CacheCustomization;
import org.xcolab.commons.http.caching.CacheCustomization.DiskStorage;
import org.xcolab.commons.http.caching.CacheKey;
import org.xcolab.commons.http.caching.CacheName;
import org.xcolab.commons.http.caching.provider.CacheProvider;

import java.util.HashMap;
import java.util.Map;

public class RedisCacheProvider implements CacheProvider {

    private final RedisCacheManager redisCacheManager;

    public RedisCacheProvider(RedisTemplate redisTemplate) {
        redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setUsePrefix(true);
    }

    @Override
    public void init(Map<CacheName, CacheCustomization> customizations, DiskStorage diskStorage) {
        final HashMap<String, Long> expires = new HashMap<>();
        for (CacheName cacheName : CacheName.values()) {
            if (cacheName != CacheName.NONE) {
                expires.put(cacheName.name(), cacheName.getDuration().toSeconds());
            }
        }
        redisCacheManager.setExpires(expires);
    }

    @Override
    public <T> T get(CacheKey<?, T> key, CacheName cacheName) {
        final ValueWrapper valueWrapper = redisCacheManager.getCache(cacheName.name())
                .get(key.stringKey());
        if (valueWrapper == null) {
            return null;
        }
        //noinspection unchecked
        return (T) valueWrapper.get();
    }

    @Override
    public <T> boolean add(CacheKey<?, T> key, CacheName cacheName, T value) {
        redisCacheManager.getCache(cacheName.name()).put(key.stringKey(), value);
        return true;
    }

    @Override
    public <T> boolean replace(CacheKey<?, T> key, CacheName cacheName, T value) {
        redisCacheManager.getCache(cacheName.name()).put(key.stringKey(), value);
        return true;
    }

    @Override
    public boolean delete(CacheKey<?, ?> key, CacheName cacheName) {
        redisCacheManager.getCache(cacheName.name()).evict(key.stringKey());
        return true;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void clear(CacheName cacheName) {
        redisCacheManager.getCache(cacheName.name()).clear();
    }
}
