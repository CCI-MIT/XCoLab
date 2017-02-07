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
                    .withCache(CacheRetention.REQUEST.name(), getTTLConfig(CacheRetention.REQUEST))
                    .withCache(CacheRetention.SHORT.name(), getTTLConfig(CacheRetention.SHORT))
                    .withCache(CacheRetention.MEDIUM.name(), getTTLConfig(CacheRetention.MEDIUM))
                    .withCache(CacheRetention.LONG.name(), getTTLConfig(CacheRetention.LONG))
                    .withCache(CacheRetention.RUNTIME.name(),
                            getConfigBuilder(CacheRetention.RUNTIME).build());

            newCacheManager = cacheManagerBuilder.build(true);
        } catch (RuntimeException e) {
            log.error("Error while creating CacheManager - cache inactive");
            newCacheManager = null;
        }
        cacheManager = newCacheManager;
    }

    private CacheConfigurationBuilder<String, Object> getConfigBuilder(
            CacheRetention cacheRetention) {
        return newCacheConfigurationBuilder(String.class, Object.class,
                ResourcePoolsBuilder.heap(cacheRetention.getNumberOfEntries()));
    }


    private CacheConfiguration<String, Object> getTTLConfig(CacheRetention cacheRetention) {
        return getConfigBuilder(cacheRetention)
                .withExpiry(Expirations.timeToLiveExpiration(cacheRetention.getDuration()))
                .build();
    }

    private Cache<String, Object> getCache(CacheRetention cacheRetention) {
        return cacheManager.getCache(cacheRetention.name(), String.class, Object.class);
    }

    @Override
    public void destroy() throws StateTransitionException {
        this.cacheManager.close();
    }

    @Override
    public <T> T get(CacheKey<?, T> key, CacheRetention cacheRetention) {
        if (isActive()) {
            try {
                //noinspection unchecked
                return (T) getCache(cacheRetention).get(key.stringKey());
            } catch (CacheLoadingException e) {
                log.error("Error while loading cache {} using {}: {}", cacheRetention, key, e.toString());
                return null;
            }
        }
        log.warn("Cache inactive - did not load {} from cache", key);
        return null;
    }

    @Override
    public <T> boolean add(CacheKey<?, T> key, CacheRetention cacheRetention, T value) {
        if (isActive()) {
            try {
                getCache(cacheRetention).put(key.stringKey(), value);
                return true;
            } catch (CacheWritingException e) {
                log.error("Could not add entry {} to cache {}: {}", key, cacheRetention, e.toString());
                return false;
            }
        }
        return false;
    }

    @Override
    public <T> boolean replace(CacheKey<?, T> key, CacheRetention cacheRetention, T value) {
        return add(key, cacheRetention, value);
    }

    @Override
    public boolean delete(CacheKey<?, ?> key, CacheRetention cacheRetention) {
        if (isActive()) {
            try {
                getCache(cacheRetention).remove(key.stringKey());
            } catch (CacheWritingException e) {
                log.error("Could not delete entry {} from cache : {}", key, cacheRetention, e.toString());
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
