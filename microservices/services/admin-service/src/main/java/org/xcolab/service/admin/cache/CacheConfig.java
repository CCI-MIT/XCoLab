package org.xcolab.service.admin.cache;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer {

    public final static String CONFIG_ATTRIBUTE_CACHE = "configAttributeCache";
    public final static String CONTEST_TYPE_ATTRIBUTE_CACHE = "contestTypeAttributeCache";

    @Bean
    @Override
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        GuavaCache configAttributeCache =
                new GuavaCache(CONFIG_ATTRIBUTE_CACHE, CacheBuilder.newBuilder()
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .build());

        GuavaCache contestTypeAttributeCache =
                new GuavaCache(CONTEST_TYPE_ATTRIBUTE_CACHE, CacheBuilder.newBuilder()
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .build());

        cacheManager.setCaches(Arrays.asList(
                configAttributeCache,
                contestTypeAttributeCache));

        return cacheManager;
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return null;
    }
}
