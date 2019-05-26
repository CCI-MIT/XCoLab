package org.xcolab.service.admin.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.admin.ICacheClient;


@RestController
public class CacheController implements ICacheClient {

    private final Logger LOGGER = LoggerFactory
            .getLogger(CacheController.class);

    @Autowired
    CacheManager cacheManager;

    @Override
    @GetMapping("/clearCache")
    public String clearCache() {
        LOGGER.info("Clearing all Admin Service cache");
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());

        return "Clearing all Admin Service cache";
    }

}
