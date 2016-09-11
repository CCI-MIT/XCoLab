package org.xcolab.util.http.caching;

public class CacheProviderNoOpImpl implements CacheProvider {

    @Override
    public <T> T get(CacheKey<?, T> key, CacheRetention cacheRetention) {
        return null;
    }

    @Override
    public <T> boolean add(CacheKey<?, T> key, CacheRetention cacheRetention, T value) {
        return false;
    }

    @Override
    public <T> boolean replace(CacheKey<?, T> key, CacheRetention cacheRetention, T value) {
        return false;
    }

    @Override
    public boolean delete(CacheKey<?, ?> key, CacheRetention cacheRetention) {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
