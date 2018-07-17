package org.xcolab.util.http.client.queries;

import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheName;

/**
 * Allows adding cache keys to a {@link Query}.
 *
 * @param <ElementT> the type of the element the query represents - in a RESTful query, this is the resource
 * @param <ReturnT> the type of the value returned when the query is executed
 */
public interface CacheableQuery<ElementT, ReturnT> extends Query<ElementT, ReturnT> {

    Query<ElementT, ReturnT> withCache(CacheKey<ElementT, ReturnT> cacheIdentifier, CacheName cacheName);

    Query<ElementT, ReturnT> withCache(CacheName cacheName);
}
