package org.xcolab.view.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.provider.CacheProvider;
import org.xcolab.util.http.caching.provider.CacheProviderNoOpImpl;
import org.xcolab.util.http.caching.provider.redis.RedisCacheProvider;
import org.xcolab.view.config.spring.properties.CacheProperties;

import java.lang.reflect.InvocationTargetException;

@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class CacheConfig {

    @SuppressWarnings("SpringAutowiredFieldsWarningInspection")
    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    private final CacheProperties cacheProperties;

    @Autowired
    public CacheConfig(CacheProperties cacheProperties) {
        Assert.notNull(cacheProperties, "CacheProperties are required");
        this.cacheProperties = cacheProperties;
    }

    @Bean
    public CacheProvider cacheProvider() {
        CacheProvider cacheProvider;
        if (cacheProperties.isEnabled()) {
            final Class<? extends CacheProvider> provider = cacheProperties.getProvider();
            Assert.notNull(provider, "No CacheProvider configured.");
            try {
                if (provider == RedisCacheProvider.class) {
                    Assert.notNull(redisTemplate,
                            "RedisTemplate is required when redis caching is enabled");
                    cacheProvider = new RedisCacheProvider(redisTemplate);
                } else {
                    cacheProvider = provider.getConstructor().newInstance();
                }
            } catch (InstantiationException | IllegalAccessException
                    | NoSuchMethodException | InvocationTargetException e) {
                throw new CacheConfigException("Could not configure cache provider", e);
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
