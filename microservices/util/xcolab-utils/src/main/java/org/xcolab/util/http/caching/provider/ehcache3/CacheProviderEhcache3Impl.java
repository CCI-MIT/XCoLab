package org.xcolab.util.http.caching.provider.ehcache3;

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

import org.xcolab.util.http.caching.CacheCustomization;
import org.xcolab.util.http.caching.CacheCustomization.DiskStorage;
import org.xcolab.util.http.caching.CacheDuration;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.caching.provider.CacheProvider;
import org.xcolab.util.http.caching.provider.ehcache3.statistics.CacheStatisticProvider;
import org.xcolab.util.http.caching.provider.ehcache3.statistics.CacheStatisticProvider.CacheEvent;

import java.util.Map;

import static org.ehcache.config.builders.CacheConfigurationBuilder.newCacheConfigurationBuilder;
import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManagerBuilder;

public class CacheProviderEhcache3Impl implements CacheProvider, DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(CacheProviderEhcache3Impl.class);

    private CacheManager cacheManager;

    private final CacheStatisticProvider cacheStatisticProvider = new CacheStatisticProvider();
    private Map<CacheName, CacheCustomization> customizations;

    public CacheProviderEhcache3Impl() {
    }

    @Override
    public void init(Map<CacheName, CacheCustomization> customizations, DiskStorage diskStorage) {
        this.customizations = customizations;
        CacheManager newCacheManager;
        try {
            CacheManagerBuilder<? extends CacheManager> cacheManagerBuilder = newCacheManagerBuilder();
            cacheManagerBuilder = configureCaches(cacheManagerBuilder);
            newCacheManager = cacheManagerBuilder.build(true);
        } catch (RuntimeException e) {
            log.error("Error while creating CacheManager - cache inactive", e);
            newCacheManager = null;
        }
        cacheManager = newCacheManager;

        for (CacheName cacheName : CacheName.values()) {
            if (cacheName != CacheName.NONE) {
                cacheStatisticProvider.initializeMeters(cacheName.name(), getCache(cacheName));
            }
        }
    }

    private CacheManagerBuilder<? extends CacheManager> configureCaches(
                CacheManagerBuilder<? extends CacheManager> cacheManagerBuilder) {
        for (CacheName cacheName : CacheName.values()) {
            if (cacheName != CacheName.NONE) {
                final CacheCustomization cacheCustomization = customizations.get(cacheName);
                if (cacheCustomization != null && cacheCustomization.getDiskStorage().isEnabled()) {
                    log.warn("Disk storage is enabled but not supported by this implementation");
                }
                if (cacheCustomization == null || cacheCustomization.isEnabled() ) {
                    if (cacheName.getDuration() == CacheDuration.RUNTIME) {
                        cacheManagerBuilder = cacheManagerBuilder.withCache(cacheName.name(),
                                getConfigBuilder(cacheName));
                    } else {
                        cacheManagerBuilder =
                                cacheManagerBuilder.withCache(cacheName.name(), getTTLConfig(cacheName));
                    }
                }
            }
        }
        return cacheManagerBuilder;
    }

    private CacheConfiguration<String, Object> getTTLConfig(CacheName cacheName) {
        final CacheCustomization cacheCustomization = customizations.get(cacheName);
        CacheDuration cacheDuration;
        if (cacheCustomization != null && cacheCustomization.getDuration() != null) {
            cacheDuration = cacheCustomization.getDuration();
        } else {
            cacheDuration = cacheName.getDuration();
        }
        return getConfigBuilder(cacheName)
                .withExpiry(Expirations.timeToLiveExpiration(cacheDuration.getDuration()))
                .build();
    }

    private CacheConfigurationBuilder<String, Object> getConfigBuilder(
            CacheName cacheName) {
        final CacheCustomization cacheCustomization = customizations.get(cacheName);
        final long numberOfEntries;
        if (cacheCustomization != null && cacheCustomization.getEntries() > 0) {
            numberOfEntries = cacheCustomization.getEntries();
        } else {
            numberOfEntries = cacheName.getNumberOfEntries();
        }
        return newCacheConfigurationBuilder(String.class, Object.class,
                ResourcePoolsBuilder.heap(numberOfEntries)
        );
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
                final T ret = (T) getCache(cacheName).get(key.stringKey());
                cacheStatisticProvider.recordCacheEvent(cacheName.name(), key.getElementType(),
                                ret != null ? CacheEvent.HIT : CacheEvent.MISS);
                return ret;

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
