package org.xcolab.util.http.caching;

import org.apache.commons.lang3.concurrent.ConcurrentUtils;

import java.util.concurrent.Future;

public class CacheProviderNoOpImpl implements CacheProvider {

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public Future<Boolean> add(String key, int exp, Object o) {
        return ConcurrentUtils.constantFuture(false);
    }

    @Override
    public Future<Boolean> replace(String key, int exp, Object o) {
        return ConcurrentUtils.constantFuture(false);
    }

    @Override
    public Future<Boolean> delete(String key) {
        return ConcurrentUtils.constantFuture(false);
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
