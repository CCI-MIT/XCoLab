package org.xcolab.view.config.spring.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.util.Assert;

import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.provider.CacheProvider;
import org.xcolab.util.http.caching.provider.CacheProviderNoOpImpl;
import org.xcolab.util.http.caching.provider.ValidatedCacheProvider;
import org.xcolab.util.http.caching.provider.redis.RedisCacheProvider;
import org.xcolab.util.http.caching.validation.CacheValidator;
import org.xcolab.view.config.spring.properties.CacheProperties;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class CacheConfig {

    private static final Logger log = LoggerFactory.getLogger(CacheConfig.class);

    @SuppressWarnings("SpringAutowiredFieldsWarningInspection")
    @Autowired(required = false)
    private RedisConnectionFactory redisConnectionFactory;


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
                    Assert.notNull(redisConnectionFactory,
                            "RedisConnectionFactory is required when redis caching is enabled");
                    cacheProvider = new RedisCacheProvider(redisConnectionFactory);
                } else {
                    cacheProvider = provider.getConstructor().newInstance();
                }
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException
                    | InvocationTargetException e) {
                throw new CacheConfigException("Could not configure cache provider", e);
            }
            cacheProvider.init(cacheProperties.getCaches(), cacheProperties.getDiskStorage());

            if (cacheProperties.getValidation().isEnabled()) {

                    final List<Class<CacheValidator>> cacheValidators =
                            cacheProperties.getValidation().getValidators();
                    if (cacheValidators.isEmpty()) {
                        log.warn("No CacheValidators are configured (but validation is enabled).");

                    }
                    ValidatedCacheProvider validatedCacheProvider =
                            new ValidatedCacheProvider(cacheProvider);
                    for (Class<CacheValidator> validatorClass : cacheValidators) {
                        log.info("Adding cache validator {}", validatorClass.getName());
                        try {
                            CacheValidator cacheValidator = validatorClass
                                    .getConstructor().newInstance();
                            validatedCacheProvider.registerCacheValidator(cacheValidator);
                        } catch (IllegalAccessException | InstantiationException
                                | NoSuchMethodException | InvocationTargetException e) {
                            throw new CacheConfigException("Could not instantiate cache validator "
                                    + validatorClass.getName(), e);
                        }
                    }
                    cacheProvider = validatedCacheProvider;
            }
        } else {
            cacheProvider = new CacheProviderNoOpImpl();
        }

        log.info("Cache provider configured: {}", cacheProvider);
        ServiceRequestUtils.setCacheProvider(cacheProvider);
        return cacheProvider;
    }

    private static class CacheConfigException extends RuntimeException {

        public CacheConfigException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }
}
