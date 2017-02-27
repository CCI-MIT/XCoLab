package org.xcolab.view.config.spring;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.xcolab.util.exceptions.InternalException;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.provider.CacheProvider;
import org.xcolab.util.http.caching.provider.CacheProviderNoOpImpl;

import java.lang.reflect.InvocationTargetException;

@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class CacheConfig {

    @Bean
    public CacheProvider cacheProvider(CacheProperties cacheProperties) {
        CacheProvider cacheProvider;
        if (cacheProperties.isEnabled()) {
            final Class<? extends CacheProvider> provider = cacheProperties.getProvider();
            try {
                cacheProvider = provider.getConstructor().newInstance();
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
