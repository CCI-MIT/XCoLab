package org.xcolab.view.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

import org.xcolab.util.exceptions.InternalException;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.provider.CacheProvider;
import org.xcolab.util.http.caching.provider.CacheProviderNoOpImpl;
import org.xcolab.util.http.caching.provider.redis.RedisCacheProvider;

import java.lang.reflect.InvocationTargetException;

@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class CacheConfig {

    private final RedisTemplate redisTemplate;

    @Autowired
    public CacheConfig(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public CacheProvider cacheProvider(CacheProperties cacheProperties) {
        CacheProvider cacheProvider;
        if (cacheProperties.isEnabled()) {
            final Class<? extends CacheProvider> provider = cacheProperties.getProvider();
            Assert.notNull(provider, "No CacheProvider configured.");
            try {
                if (provider == RedisCacheProvider.class) {
                    cacheProvider = new RedisCacheProvider(redisTemplate);
                } else {
                    cacheProvider = provider.getConstructor().newInstance();
                }
            } catch (InstantiationException | IllegalAccessException
                    | NoSuchMethodException | InvocationTargetException e) {
                throw new InternalException(e);
            }
            cacheProvider.init(cacheProperties.getCaches(), cacheProperties.getDiskStorage());
        } else {
            cacheProvider = new CacheProviderNoOpImpl();
        }
        ServiceRequestUtils.setCacheProvider(cacheProvider);
        return cacheProvider;
    }

    private static class CacheConfigException extends RuntimeException {
        public CacheConfigException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }
}
