package org.xcolab.util.http.caching;

public class CacheProviderNoOpImpl implements CacheProvider {

    @Override
    public <T> T get(CacheKey<?, T> key, CacheName cacheName) {
        return null;
    }

    @Override
    public <T> boolean add(CacheKey<?, T> key, CacheName cacheName, T value) {
        return false;
    }

    @Override
    public <T> boolean replace(CacheKey<?, T> key, CacheName cacheName, T value) {
        return false;
    }

    @Override
    public boolean delete(CacheKey<?, ?> key, CacheName cacheName) {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public void clear(CacheName cacheName) {
    }
}
