package org.xcolab.util.http.caching;

public class CacheProviderNoOpImpl implements CacheProvider {

    @Override
    public <T> T get(CacheKey<?, T> key, CachingStrategy cachingStrategy) {
        return null;
    }

    @Override
    public <T> boolean add(CacheKey<?, T> key, CachingStrategy cachingStrategy, T value) {
        return false;
    }

    @Override
    public <T> boolean replace(CacheKey<?, T> key, CachingStrategy cachingStrategy, T value) {
        return false;
    }

    @Override
    public boolean delete(CacheKey<?, ?> key, CachingStrategy cachingStrategy) {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
