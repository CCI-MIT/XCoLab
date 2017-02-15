package org.xcolab.util.http.caching.statistics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import org.ehcache.impl.events.CacheEventAdapter;

import org.xcolab.util.metrics.MetricsUtil;

import static com.codahale.metrics.MetricRegistry.name;

public class StatisticsCacheEventAdapter extends CacheEventAdapter<String, Object> {

    private final String cacheName;

    private Meter evictionMeter;
    private Meter expirationMeter;
    private Meter creationMeter;
    private Counter sizeCounter;

    public StatisticsCacheEventAdapter(String cacheName) {
        this.cacheName = cacheName;
    }

    @Override
    protected void onEviction(String key, Object evictedValue) {
        getEvictionMeter().mark();
        getSizeCounter().dec();
    }

    @Override
    protected void onExpiry(String key, Object expiredValue) {
        getExpirationMeter().mark();
        getSizeCounter().dec();
    }

    @Override
    protected void onCreation(String key, Object newValue) {
        getCreationMeter().mark();
        getSizeCounter().inc();
    }

    @Override
    protected void onRemoval(String key, Object removedValue) {
        getSizeCounter().dec();
    }

    private Meter getEvictionMeter() {
        if (evictionMeter == null) {
            evictionMeter = MetricsUtil.REGISTRY.meter(
                    name("cache", cacheName, "cache-evictions"));
        }
        return evictionMeter;
    }

    private Meter getExpirationMeter() {
        if (expirationMeter == null) {
            expirationMeter = MetricsUtil.REGISTRY.meter(
                    name("cache", cacheName, "cache-expirations"));
        }
        return expirationMeter;
    }

    private Meter getCreationMeter() {
        if (creationMeter == null) {
            creationMeter = MetricsUtil.REGISTRY.meter(
                    name("cache", cacheName, "cache-creations"));
        }
        return creationMeter;
    }

    private Counter getSizeCounter() {
        if (sizeCounter == null) {
            sizeCounter = MetricsUtil.REGISTRY.counter(name("cache",
                    cacheName, "cache-size"));
        }
        return sizeCounter;
    }
}
