package org.xcolab.util.http.caching;

public class CacheProviderNoOpImpl implements CacheProvider {

    @Override
    public Object get(CachingStrategy cachingStrategy, String key) {
        return null;
    }

    @Override
    public boolean add(CachingStrategy cachingStrategy, String key, Object o) {
        return false;
    }

    @Override
    public boolean replace(CachingStrategy cachingStrategy, String key, Object o) {
        return false;
    }

    @Override
    public boolean delete(CachingStrategy cachingStrategy, String key) {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
