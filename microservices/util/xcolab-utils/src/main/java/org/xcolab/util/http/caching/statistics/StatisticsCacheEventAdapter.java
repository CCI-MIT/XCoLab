package org.xcolab.util.http.caching.statistics;

import com.codahale.metrics.Meter;
import org.ehcache.impl.events.CacheEventAdapter;

import org.xcolab.util.http.caching.CacheProviderEhcacheImpl;
import org.xcolab.util.metrics.MetricsUtil;

import static com.codahale.metrics.MetricRegistry.name;

public class StatisticsCacheEventAdapter extends CacheEventAdapter<String, Object> {

    private final String identityName;
    private final String cacheName;

    private Meter evictionMeter;
    private Meter expirationMeter;
    private Meter creationMeter;

    public StatisticsCacheEventAdapter(String identityName, String cacheName) {
        this.identityName = identityName;
        this.cacheName = cacheName;
    }

    @Override
    protected void onEviction(String key, Object evictedValue) {
        getEvictionMeter().mark();
    }

    private Meter getEvictionMeter() {
        if (evictionMeter == null) {
            evictionMeter = MetricsUtil.REGISTRY.meter(
                    name(CacheProviderEhcacheImpl.class, identityName, cacheName, "cache-evictions"));
        }
        return evictionMeter;
    }

    @Override
    protected void onExpiry(String key, Object expiredValue) {
        getExpirationMeter().mark();
    }

    private Meter getExpirationMeter() {
        if (expirationMeter == null) {
            expirationMeter = MetricsUtil.REGISTRY.meter(
                    name(CacheProviderEhcacheImpl.class, identityName, cacheName, "cache-expirations"));
        }
        return expirationMeter;
    }

    @Override
    protected void onCreation(String key, Object newValue) {
        getCreationMeter().mark();
    }

    private Meter getCreationMeter() {
        if (creationMeter == null) {
            creationMeter = MetricsUtil.REGISTRY.meter(
                    name(CacheProviderEhcacheImpl.class, identityName, cacheName, "cache-creations"));
        }
        return creationMeter;
    }
}
