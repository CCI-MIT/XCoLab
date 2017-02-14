package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheName;

public interface CacheableQuery<T, R> extends Query<T, R> {
    Query<T, R> withCache(CacheKey<T, R> cacheIdentifier, CacheName cacheName);
    Query<T, R> withCache(CacheName cacheName);
}
