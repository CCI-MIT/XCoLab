package org.xcolab.util.http.caching.provider.ehcache2;

import com.codahale.metrics.ehcache.InstrumentedEhcache;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.MemoryUnit;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration.Strategy;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import org.xcolab.util.http.caching.CacheCustomization;
import org.xcolab.util.http.caching.CacheCustomization.DiskStorage;
import org.xcolab.util.http.caching.CacheDuration;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.caching.provider.CacheProvider;
import org.xcolab.util.metrics.MetricsUtil;

import java.util.Map;

public class CacheProviderEhcache2Impl implements CacheProvider {

    private CacheManager cacheManager;

    @Override
    public void init(Map<CacheName, CacheCustomization> customizations) {
        cacheManager = CacheManager.create();
        for (CacheName cacheName : CacheName.values()) {
            if (cacheName != CacheName.NONE) {
                addCache(cacheName, customizations.get(cacheName));
            }
        }
    }

    private void addCache(CacheName cacheName, CacheCustomization cacheCustomization) {
        MemoryStoreEvictionPolicy evictionPolicy;
        if (cacheCustomization != null && cacheCustomization.getEvictionPolicy() != null) {
            evictionPolicy = MemoryStoreEvictionPolicy.fromString(cacheCustomization.getEvictionPolicy());
        } else {
            evictionPolicy = MemoryStoreEvictionPolicy.LRU;
        }
        CacheConfiguration cacheConfiguration = new CacheConfiguration(cacheName.name(),
                cacheName.getNumberOfEntries(cacheCustomization))
                .memoryStoreEvictionPolicy(evictionPolicy);

        if (cacheName.getDuration() != CacheDuration.RUNTIME) {
            cacheConfiguration = cacheConfiguration
                    .timeToLiveSeconds(cacheName.getDuration(cacheCustomization).getDuration().getLength());
        }
        if (cacheCustomization != null) {
            final DiskStorage diskStorage = cacheCustomization.getDiskStorage();
            if (diskStorage.isEnabled()) {
                cacheConfiguration = cacheConfiguration
                        .maxBytesLocalDisk(diskStorage.getMaxMegabytes(), MemoryUnit.MEGABYTES)
                        .persistence(new PersistenceConfiguration().strategy(Strategy.LOCALTEMPSWAP));
            }
        }
        Cache cache = new Cache(cacheConfiguration);
        Ehcache instrumentedCache = InstrumentedEhcache.instrument(MetricsUtil.REGISTRY, cache);
        cacheManager.addCache(instrumentedCache);
    }

    private Ehcache getCache(CacheName cacheName) {
        return cacheManager.getEhcache(cacheName.name());
    }

    @Override
    public <T> T get(CacheKey<?, T> key, CacheName cacheName) {
        final Element element = getCache(cacheName).get(key.stringKey());
        if (element == null) {
            return null;
        }
        //noinspection unchecked
        return (T) element.getObjectValue();
    }

    @Override
    public <T> boolean add(CacheKey<?, T> key, CacheName cacheName, T value) {
        Element element = new Element(key.stringKey(), value);
        getCache(cacheName).put(element);
        return true;
    }

    @Override
    public <T> boolean replace(CacheKey<?, T> key, CacheName cacheName, T value) {
        Element element = new Element(key.stringKey(), value);
        getCache(cacheName).put(element);
        return true;
    }

    @Override
    public boolean delete(CacheKey<?, ?> key, CacheName cacheName) {
        getCache(cacheName).remove(key.stringKey());
        return false;
    }

    @Override
    public boolean isActive() {
        return cacheManager != null;
    }

    @Override
    public void clear() {
        cacheManager.clearAll();
    }

    @Override
    public void clear(CacheName cacheName) {
        getCache(cacheName).removeAll();
    }
}
