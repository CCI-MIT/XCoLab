package org.xcolab.util.http.caching.provider.ehcache3.statistics;

import com.codahale.metrics.Meter;
import org.ehcache.Cache;
import org.ehcache.event.EventFiring;
import org.ehcache.event.EventOrdering;
import org.ehcache.event.EventType;
import org.springframework.util.Assert;

import org.xcolab.util.metrics.MetricsUtil;

import java.util.HashMap;
import java.util.Map;

import static com.codahale.metrics.MetricRegistry.name;

public class CacheStatisticProvider {

    private final Map<MeterMapKey, Meter> hitMeters = new HashMap<>();
    private final Map<MeterMapKey, Meter> missMeters = new HashMap<>();


    public CacheStatisticProvider() {
    }

    public void initializeMeters(String cacheName, Cache<String, Object> cache) {
        if (cache != null) {
            cache.getRuntimeConfiguration()
                    .registerCacheEventListener(new StatisticsCacheEventAdapter(cacheName),
                            EventOrdering.UNORDERED, EventFiring.ASYNCHRONOUS,
                            EventType.EVICTED, EventType.EXPIRED, EventType.CREATED,
                            EventType.REMOVED);
        }
    }

    private Meter getHitMeter(String cacheName, Class<?> cachedClass) {
        MeterMapKey key = new MeterMapKey(cacheName, cachedClass);
        return hitMeters.computeIfAbsent(key,
                k -> MetricsUtil.REGISTRY.meter(name("cache", cacheName, cachedClass != null ? cachedClass.getSimpleName() : "stats", "cache-hits")));
    }

    private Meter getMissMeter(String cacheName, Class<?> cachedClass) {
        MeterMapKey key = new MeterMapKey(cacheName, cachedClass);
        return missMeters.computeIfAbsent(key,
                k -> MetricsUtil.REGISTRY.meter(name("cache", cacheName, cachedClass != null ? cachedClass.getSimpleName() : "stats", "cache-misses")));
    }

    public void recordCacheEvent(String cacheName, Class<?> cachedClass, CacheEvent cacheEvent) {
        switch (cacheEvent) {
            case HIT:
                getHitMeter(cacheName, cachedClass).mark();
                getHitMeter(cacheName, null).mark();
                break;
            case MISS:
                getMissMeter(cacheName, cachedClass).mark();
                getMissMeter(cacheName, null).mark();
                break;
             //missing default case
            default:
            // add default case
                break;

        }
    }

    public enum CacheEvent {
        HIT, MISS
    }

    private static class MeterMapKey {

        private final String cacheName;
        private final Class<?> cachedClass;

        MeterMapKey(String cacheName, Class<?> cachedClass) {
            Assert.notNull(cacheName, "Cache name is required");
            this.cacheName = cacheName;
            this.cachedClass = cachedClass;
        }


        public String getCacheName() {
            return cacheName;
        }

        public Class<?> getCachedClass() {
            return cachedClass;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof MeterMapKey)) {
                return false;
            }

            MeterMapKey that = (MeterMapKey) o;

            return cacheName.equals(that.cacheName) && (cachedClass != null ? cachedClass
                    .equals(that.cachedClass) : that.cachedClass == null);
        }

        @Override
        public int hashCode() {
            int result = cacheName.hashCode();
            result = 31 * result + (cachedClass != null ? cachedClass.hashCode() : 0);
            return result;
        }
    }
}
