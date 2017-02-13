package org.xcolab.util.http.caching;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.StateTransitionException;
import org.ehcache.Status;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Expirations;
import org.ehcache.spi.loaderwriter.CacheLoadingException;
import org.ehcache.spi.loaderwriter.CacheWritingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import static org.ehcache.config.builders.CacheConfigurationBuilder.newCacheConfigurationBuilder;
import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManagerBuilder;

public class CacheProviderEhcacheImpl implements CacheProvider, DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(CacheProviderEhcacheImpl.class);

    private final CacheManager cacheManager;

    public CacheProviderEhcacheImpl() {
        CacheManager newCacheManager;
        try {
            final CacheManagerBuilder<CacheManager> cacheManagerBuilder = newCacheManagerBuilder()
                    .withCache(CacheName.MISC_REQUEST
                            .name(), getTTLConfig(CacheName.MISC_REQUEST))
                    .withCache(CacheName.MISC_SHORT
                            .name(), getTTLConfig(CacheName.MISC_SHORT))
                    .withCache(CacheName.MISC_MEDIUM
                            .name(), getTTLConfig(CacheName.MISC_MEDIUM))
                    .withCache(CacheName.MISC_LONG
                            .name(), getTTLConfig(CacheName.MISC_LONG))
                    .withCache(CacheName.MISC_RUNTIME.name(),
                            getConfigBuilder(CacheName.MISC_RUNTIME).build());

            newCacheManager = cacheManagerBuilder.build(true);
        } catch (RuntimeException e) {
            log.error("Error while creating CacheManager - cache inactive");
            newCacheManager = null;
        }
        cacheManager = newCacheManager;
    }

    private CacheConfigurationBuilder<String, Object> getConfigBuilder(
            CacheName cacheName) {
        return newCacheConfigurationBuilder(String.class, Object.class,
                ResourcePoolsBuilder.heap(cacheName.getNumberOfEntries()));
    }


    private CacheConfiguration<String, Object> getTTLConfig(CacheName cacheName) {
        return getConfigBuilder(cacheName)
                .withExpiry(Expirations.timeToLiveExpiration(cacheName.getDuration().getDuration()))
                .build();
    }

    private Cache<String, Object> getCache(CacheName cacheName) {
        return cacheManager.getCache(cacheName.name(), String.class, Object.class);
    }

    @Override
    public void destroy() throws StateTransitionException {
        this.cacheManager.close();
    }

    @Override
    public <T> T get(CacheKey<?, T> key, CacheName cacheName) {
        if (isActive()) {
            try {
                //noinspection unchecked
                return (T) getCache(cacheName).get(key.stringKey());
            } catch (CacheLoadingException e) {
                log.error("Error while loading cache {} using {}: {}", cacheName, key, e.toString());
                return null;
            }
        }
        log.warn("Cache inactive - did not load {} from cache", key);
        return null;
    }

    @Override
    public <T> boolean add(CacheKey<?, T> key, CacheName cacheName, T value) {
        if (isActive()) {
            try {
                getCache(cacheName).put(key.stringKey(), value);
                return true;
            } catch (CacheWritingException e) {
                log.error("Could not add entry {} to cache {}: {}", key, cacheName, e.toString());
                return false;
            }
        }
        return false;
    }

    @Override
    public <T> boolean replace(CacheKey<?, T> key, CacheName cacheName, T value) {
        return add(key, cacheName, value);
    }

    @Override
    public boolean delete(CacheKey<?, ?> key, CacheName cacheName) {
        if (isActive()) {
            try {
                getCache(cacheName).remove(key.stringKey());
            } catch (CacheWritingException e) {
                log.error("Could not delete entry {} from cache {}: {}", key, cacheName, e.toString());
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean isActive() {
        return cacheManager != null && cacheManager.getStatus() == Status.AVAILABLE;
    }

    @Override
    public void clear() {
        for (CacheName cacheName : CacheName.values()) {
            clear(cacheName);
        }
    }

    @Override
    public void clear(CacheName cacheName) {
        if (cacheName != CacheName.NONE) {
            final Cache<String, Object> cache = getCache(cacheName);
            if (cache != null) {
                cache.clear();
            }
        }
    }
}
