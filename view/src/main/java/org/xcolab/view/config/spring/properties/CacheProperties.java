package org.xcolab.view.config.spring.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.xcolab.util.http.caching.CacheCustomization;
import org.xcolab.util.http.caching.CacheCustomization.DiskStorage;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.caching.provider.CacheProvider;
import org.xcolab.util.http.caching.validation.CacheValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties("cache")
public class CacheProperties {

    /**
     * Enable caching of in service clients.
     */
    private boolean enabled = true;

    /**
     * Custom implementation of cache provider to be used for caching.
     */
    private Class<? extends CacheProvider> provider;

    /**
     * Configure disk storage for all caches.
     */
    private final DiskStorage diskStorage = new DiskStorage();

    /**
     * Customize individual caches.
     */
    private final Map<CacheName, CacheCustomization> caches = new HashMap<>();

    private final Validation validation = new Validation();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Class<? extends CacheProvider> getProvider() {
        return provider;
    }

    public void setProvider(Class<? extends CacheProvider> provider) {
        this.provider = provider;
    }

    public Map<CacheName, CacheCustomization> getCaches() {
        return caches;
    }

    public DiskStorage getDiskStorage() {
        return diskStorage;
    }

    public Validation getValidation() {
        return validation;
    }

    public static class Validation {

        /**
         * Enable validation of cache entries.
         *
         * If active, adding invalid entries will throw an exception
         * and retrieving them would log a warning.
         *
         * Make sure you configure a least one cacheValidator if this option is active.
         */
        private boolean enabled = false;

        /**
         * A List of CacheValidators to validate entries.
         *
         * Each CacheValidator acts on a specific class. Make sure you only register one per class,
         * otherwise only the latest validator for a class will be executed.
         */
        private final List<Class<CacheValidator>> validators = new ArrayList<>();

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public List<Class<CacheValidator>> getValidators() {
            return validators;
        }
    }
}
