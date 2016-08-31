package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.caching.CachingStrategy;

public interface CacheableQuery<T, R> extends Query<T, R> {
    Query<T, R> withCache(String cacheIdentifier, CachingStrategy cachingStrategy);
}
