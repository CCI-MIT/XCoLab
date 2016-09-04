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
import org.springframework.stereotype.Component;

import static org.ehcache.config.builders.CacheConfigurationBuilder.newCacheConfigurationBuilder;
import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManagerBuilder;

@Component
public class CacheProviderEhcacheImpl implements CacheProvider, DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(CacheProviderEhcacheImpl.class);

    private final CacheManager cacheManager;

    public CacheProviderEhcacheImpl() {
        CacheManager newCacheManager;
        try {
            final CacheManagerBuilder<CacheManager> cacheManagerBuilder = newCacheManagerBuilder()
                    .withCache(CachingStrategy.REQUEST.name(), getTTLConfig(CachingStrategy.REQUEST))
                    .withCache(CachingStrategy.SHORT.name(), getTTLConfig(CachingStrategy.SHORT))
                    .withCache(CachingStrategy.MEDIUM.name(), getTTLConfig(CachingStrategy.MEDIUM))
                    .withCache(CachingStrategy.LONG.name(), getTTLConfig(CachingStrategy.LONG))
                    .withCache(CachingStrategy.RUNTIME.name(),
                            getConfigBuilder(CachingStrategy.RUNTIME).build());

            newCacheManager = cacheManagerBuilder.build(true);
        } catch (RuntimeException e) {
            log.error("Error while creating CacheManager - cache inactive");
            newCacheManager = null;
        }
        cacheManager = newCacheManager;
    }

    private CacheConfigurationBuilder<String, Object> getConfigBuilder(
            CachingStrategy cachingStrategy) {
        return newCacheConfigurationBuilder(String.class, Object.class,
                ResourcePoolsBuilder.heap(cachingStrategy.getNumberOfEntries()));
    }


    private CacheConfiguration<String, Object> getTTLConfig(CachingStrategy cachingStrategy) {
        return getConfigBuilder(cachingStrategy)
                .withExpiry(Expirations.timeToLiveExpiration(cachingStrategy.getDuration()))
                .build();
    }

    private Cache<String, Object> getCache(CachingStrategy cachingStrategy) {
        return cacheManager.getCache(cachingStrategy.name(), String.class, Object.class);
    }

    @Override
    public void destroy() throws StateTransitionException {
        this.cacheManager.close();
    }

    @Override
    public <T> T get(CacheKey<?, T> key, CachingStrategy cachingStrategy) {
        if (isActive()) {
            try {
                //noinspection unchecked
                return (T) getCache(cachingStrategy).get(key.stringKey());
            } catch (CacheLoadingException e) {
                log.error("Error while loading cache {} using {}: {}", cachingStrategy, key, e.toString());
                return null;
            }
        }
        log.warn("Cache inactive - did not load {} from cache", key);
        return null;
    }

    @Override
    public <T> boolean add(CacheKey<?, T> key, CachingStrategy cachingStrategy, T value) {
        if (isActive()) {
            try {
                getCache(cachingStrategy).put(key.stringKey(), value);
                return true;
            } catch (CacheWritingException e) {
                log.error("Could not add entry {} to cache {}: {}", key, cachingStrategy, e.toString());
                return false;
            }
        }
        return false;
    }

    @Override
    public <T> boolean replace(CacheKey<?, T> key, CachingStrategy cachingStrategy, T value) {
        return add(key, cachingStrategy, value);
    }

    @Override
    public boolean delete(CacheKey<?, ?> key, CachingStrategy cachingStrategy) {
        if (isActive()) {
            try {
                getCache(cachingStrategy).remove(key.stringKey());
            } catch (CacheWritingException e) {
                log.error("Could not delete entry {} from cache : {}", key, cachingStrategy, e.toString());
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean isActive() {
        return cacheManager != null && cacheManager.getStatus() == Status.AVAILABLE;
    }
}
