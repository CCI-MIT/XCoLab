package org.xcolab.util.http.caching.provider.redis;

import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import org.xcolab.util.http.caching.CacheCustomization;
import org.xcolab.util.http.caching.CacheCustomization.DiskStorage;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.caching.provider.CacheProvider;

import java.util.HashMap;
import java.util.Map;

public class RedisCacheProvider implements CacheProvider {

    private RedisCacheManager redisCacheManager;
    private final RedisConnectionFactory redisConnectionFactory;

    public RedisCacheProvider(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Override
    public void init(Map<CacheName, CacheCustomization> customizations, DiskStorage diskStorage) {
        final HashMap<String, RedisCacheConfiguration> configurations = new HashMap<>();
        for (CacheName cacheName : CacheName.values()) {
            if (cacheName != CacheName.NONE) {
                configurations.put(cacheName.name(), RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(cacheName.getDuration().getDuration()));
            }
        }
        redisCacheManager = RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
                .withInitialCacheConfigurations(configurations)
                .build();
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
