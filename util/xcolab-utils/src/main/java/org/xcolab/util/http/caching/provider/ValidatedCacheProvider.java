package org.xcolab.util.http.caching.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.util.http.caching.CacheCustomization;
import org.xcolab.util.http.caching.CacheCustomization.DiskStorage;
import org.xcolab.util.http.caching.CacheKey;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.caching.validation.CacheValidator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Decorator that can validate entities that enter or leave the decorated {@link CacheProvider}.
 *
 * This can be helpful for debugging purposes if problems are found with entities in the cache,
 * as it will be sure to fail fast when invalid entities are added to or retrieved from the cache.
 *
 * This class decorates another {@code CacheProvider} and uses a list of {@link CacheValidator}s to
 * validate them. All methods function the same as the decorated {@code CacheProvider}, with the
 * difference that the {@link #add(CacheKey, CacheName, Object)}, {@link #get(CacheKey, CacheName)},
 * {@link #replace(CacheKey, CacheName, Object)} methods throw an {@link InvalidCacheEntryException}
 * if an invalid element is found.
 */
public class ValidatedCacheProvider implements CacheProvider {

    private static final Logger log = LoggerFactory.getLogger(ValidatedCacheProvider.class);

    private final Map<Class, CacheValidator> cacheValidators = new HashMap<>();

    private final CacheProvider cacheProvider;

    public ValidatedCacheProvider(CacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    /**
     * Register a CacheValidator in order to validate certain cache entries before they are
     * retrieved from the cache.
     *
     * If the entry is invalid, it will be evicted and null will be returned from the cache.
     * @param cacheValidator Cache validator to register.
     */
    public void registerCacheValidator(CacheValidator cacheValidator) {
        log.trace("Registering validator {} of type {}", cacheValidator.getClass().getSimpleName(),
                cacheValidator.getEntityType());
        cacheValidators.put(cacheValidator.getEntityType(), cacheValidator);
    }

    private boolean isValid(Object entity) {
        //noinspection unchecked
        Class<?> entityType = entity.getClass();
        if (entity instanceof Collection) {
            Collection collection = (Collection) entity;
            if (collection.isEmpty()) {
                return true;
            } else {
                final Object collectionEntity = collection.iterator().next();
                entityType = collectionEntity.getClass();
            }
        }
        //noinspection unchecked
        CacheValidator<? super Object> cacheValidator = cacheValidators.get(entityType);
        if (cacheValidator == null) {
            return true;
        }

        log.trace("Validating entity of type {} with validator {}", entityType,
                cacheValidator.getClass().getSimpleName());

        if (entity instanceof Collection) {
            //noinspection unchecked
            Collection<Object> collection = (Collection<Object>) entity;
            return collection.stream().allMatch(cacheValidator::isValid);
        } else {
            return cacheValidator.isValid(entity);
        }
    }

    @Override
    public void init(Map<CacheName, CacheCustomization> customizations, DiskStorage diskStorage) {
        cacheProvider.init(customizations, diskStorage);
    }

    @Override
    public <T> T get(CacheKey<?, T> key, CacheName cacheName) {
        T ret = cacheProvider.get(key, cacheName);
        if (ret != null && !isValid(ret)) {
            delete(key, cacheName);
            throw new InvalidCacheEntryException(cacheName, key, ret);
        }
        return ret;
    }

    @Override
    public <T> boolean add(CacheKey<?, T> key, CacheName cacheName, T value) {
        if (!isValid(value)) {
            throw new InvalidCacheEntryException(value);
        }
        return cacheProvider.add(key, cacheName, value);
    }

    @Override
    public <T> boolean replace(CacheKey<?, T> key, CacheName cacheName, T value) {
        if (!isValid(value)) {
            throw new InvalidCacheEntryException(value);
        }
        return cacheProvider.replace(key, cacheName, value);
    }

    @Override
    public boolean delete(CacheKey<?, ?> key, CacheName cacheName) {
        return cacheProvider.delete(key, cacheName);
    }

    @Override
    public boolean isActive() {
        return cacheProvider.isActive();
    }

    @Override
    public void clear() {
        cacheProvider.clear();
    }

    @Override
    public void clear(CacheName cacheName) {
        cacheProvider.clear();
    }

    public static class InvalidCacheEntryException extends IllegalStateException {
        public InvalidCacheEntryException(Object invalidEntry) {
            super("Invalid cache entry found: " + invalidEntry);
        }

        public InvalidCacheEntryException(CacheName cacheName, Object key, Object invalidEntry) {
            super(String.format("Invalid cache entry %s with key %s found in cache %s: ",
                    invalidEntry, key, cacheName.toString()));
        }
    }
}
