package org.xcolab.commons.http.caching.provider;

import org.xcolab.commons.http.caching.CacheName;

public interface ConditionalCacheProvider extends CacheProvider {

    boolean accepts(CacheName cacheName);
}
