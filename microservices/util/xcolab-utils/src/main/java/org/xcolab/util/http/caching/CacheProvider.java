package org.xcolab.util.http.caching;

public interface CacheProvider {

    Object get(CachingStrategy cachingStrategy, String key);

    boolean add(CachingStrategy cachingStrategy, String key, Object o);

    boolean replace(CachingStrategy cachingStrategy, String key, Object o);

    boolean delete(CachingStrategy cachingStrategy, String key);

    boolean isActive();
}
