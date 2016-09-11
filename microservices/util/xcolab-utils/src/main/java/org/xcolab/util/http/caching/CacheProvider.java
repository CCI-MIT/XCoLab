package org.xcolab.util.http.caching;

public interface CacheProvider {

    <T> T get(CacheKey<?, T> key, CacheRetention cacheRetention);

    <T> boolean add(CacheKey<?, T> key, CacheRetention cacheRetention, T value);

    <T> boolean replace(CacheKey<?, T> key, CacheRetention cacheRetention, T value);

    boolean delete(CacheKey<?, ?> key, CacheRetention cacheRetention);

    boolean isActive();
}
