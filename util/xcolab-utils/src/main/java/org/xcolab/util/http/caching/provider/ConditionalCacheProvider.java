package org.xcolab.util.http.caching.provider;

import org.xcolab.util.http.caching.CacheName;

public interface ConditionalCacheProvider extends CacheProvider {

    boolean accepts(CacheName cacheName);
}
