package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheRetention;

public interface CacheableQuery<T, R> extends Query<T, R> {
    Query<T, R> withCache(CacheKey<T, R> cacheIdentifier, CacheRetention cacheRetention);
}
