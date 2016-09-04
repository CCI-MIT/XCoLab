package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CachingStrategy;

public interface CacheableQuery<T, R> extends Query<T, R> {
    Query<T, R> withCache(CacheKey<T, R> cacheIdentifier, CachingStrategy cachingStrategy);
}
