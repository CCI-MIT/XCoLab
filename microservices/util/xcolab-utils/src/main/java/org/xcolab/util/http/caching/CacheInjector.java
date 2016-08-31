package org.xcolab.util.http.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.util.http.RequestUtils;

import javax.annotation.PostConstruct;

@Component
public class CacheInjector {
    private final CacheProvider injectedCacheProvider;

    @Autowired
    public CacheInjector(CacheProvider injectedCacheProvider) {
            this.injectedCacheProvider = injectedCacheProvider;
    }

    @PostConstruct
    public void postConstruct() {
        RequestUtils.setCacheProvider(injectedCacheProvider);
    }
}
