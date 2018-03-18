package org.xcolab.commons.http.caching;

import org.springframework.util.Assert;

import org.xcolab.commons.http.caching.CacheKey.Builder;

import java.util.Collections;
import java.util.HashMap;

public final class CacheKeys {

    @SuppressWarnings("unchecked")
    private static final CacheKey NONE = new EmptyCacheKey();

    private CacheKeys() {
    }

    @SuppressWarnings("unchecked")
    public static <T> CacheKey<T, T> none() {
        return (CacheKey<T, T>) NONE;
    }

    public static <T> Builder<T> withClass(Class<T> elementType) {
        return new Builder<>(elementType);
    }

    public static <T> CacheKey<T, T> of(Class<T> elementType, long id) {
        final HashMap<String, String> parameters = new HashMap<>();
        parameters.put("id", Long.toString(id));
        return new CacheKey<>(elementType, parameters);
    }

    public static <T> CacheKey<T, T> of(Class<T> elementType, String stringId) {
        Assert.notNull(stringId, "stringId cannot be null");
        final HashMap<String, String> parameters = new HashMap<>();
        parameters.put("id", stringId);
        return new CacheKey<>(elementType, parameters);
    }

    public static <T> CacheKey<T, T> of(Class<T> elementType, Object objectId) {
        Assert.notNull(objectId, "objectId cannot be null");
        return of(elementType, objectId.toString());
    }

    private static class EmptyCacheKey extends CacheKey<Void, Void> {

        private EmptyCacheKey() {
            super(Void.class, Collections.singletonMap("isCached", "false"));
        }

        @Override
        public boolean isPresent() {
            return false;
        }
    }
}
