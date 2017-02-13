package org.xcolab.util.http.caching;

public interface CacheProvider {

    <T> T get(CacheKey<?, T> key, CacheName cacheName);

    <T> boolean add(CacheKey<?, T> key, CacheName cacheName, T value);

    <T> boolean replace(CacheKey<?, T> key, CacheName cacheName, T value);

    boolean delete(CacheKey<?, ?> key, CacheName cacheName);

    boolean isActive();

    void clear();

    void clear(CacheName cacheName);
}
