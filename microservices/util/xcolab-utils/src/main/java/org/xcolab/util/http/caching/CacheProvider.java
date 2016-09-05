package org.xcolab.util.http.caching;

public interface CacheProvider {

    <T> T get(CacheKey<?, T> key, CachingStrategy cachingStrategy);

    <T> boolean add(CacheKey<?, T> key, CachingStrategy cachingStrategy, T value);

    <T> boolean replace(CacheKey<?, T> key, CachingStrategy cachingStrategy, T value);

    boolean delete(CacheKey<?, ?> key, CachingStrategy cachingStrategy);

    boolean isActive();
}
