package org.xcolab.util.caching;


import org.apache.commons.lang3.concurrent.ConcurrentUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
public class CacheProviderEhCachedImpl implements CacheProvider, DisposableBean {


    
    private CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            .withCache("preConfigured",
                    CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Object.class,
                            ResourcePoolsBuilder.heap(300))
                            .withExpiry(Expirations.timeToLiveExpiration(Duration.of(4, TimeUnit.SECONDS)))
                            .build()).build(true);

    private Cache<String, Object> getCache() {
        return cacheManager.getCache("preConfigured", String.class, Object.class);
    }

    @Override
    public void destroy() throws Exception {
        this.cacheManager.close();
    }

    @Override
    public Object get(String key) {
        if (isActive()){
            return getCache().get(key);
        }
        return null;
    }

    @Override
    public Future<Boolean> add(String key, int exp, Object o) {
        if (isActive()){
            getCache().put(key, o);
        }
        return ConcurrentUtils.constantFuture(false);
    }

    @Override
    public Future<Boolean> replace(String key, int exp, Object o) {
        if (isActive()){
            getCache().put(key, o);
        }
        return ConcurrentUtils.constantFuture(false);
    }

    @Override
    public Future<Boolean> delete(String key) {
        if (isActive()){
            getCache().remove(key);
        }
        return ConcurrentUtils.constantFuture(false);
    }

    @Override
    public boolean isActive() {
        return getCache()!=null;
    }
}
