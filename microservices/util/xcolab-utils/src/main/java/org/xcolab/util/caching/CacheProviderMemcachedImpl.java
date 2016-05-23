package org.xcolab.util.caching;

import net.spy.memcached.MemcachedClient;
import org.apache.commons.lang3.concurrent.ConcurrentUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
public class CacheProviderMemcachedImpl implements CacheProvider, DisposableBean {

    private MemcachedClient memcached;

    public CacheProviderMemcachedImpl() {
        try {
            memcached = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
        } catch (IOException ignored) {
            memcached = null;
        }
    }

    @Override
    public Object get(String key) {
        if (isActive()) {
            return memcached.get(key);
        }
        return null;
    }

    @Override
    public Future<Boolean> add(String key, int exp, Object o) {
        if (isActive()) {
            return memcached.add(key, exp, o);
        }
        return ConcurrentUtils.constantFuture(false);
    }

    @Override
    public boolean isActive() {
        return memcached != null;
    }

    @Override
    public void destroy() throws Exception {
        if (memcached != null) {
            memcached.shutdown(1000, TimeUnit.MILLISECONDS);
        }
    }
}
