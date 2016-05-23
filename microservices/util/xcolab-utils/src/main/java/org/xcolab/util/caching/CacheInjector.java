package org.xcolab.util.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.util.RequestUtils;

import javax.annotation.PostConstruct;

@Component
public class CacheInjector {
    @Autowired
    private CacheProvider injectedCacheProvider;

    @PostConstruct
    public void postConstruct() {
        RequestUtils.setCacheProvider(injectedCacheProvider);
    }
}
