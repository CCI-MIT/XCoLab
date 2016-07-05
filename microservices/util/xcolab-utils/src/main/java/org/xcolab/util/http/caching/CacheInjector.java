package org.xcolab.util.http.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.util.http.RequestUtils;

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
