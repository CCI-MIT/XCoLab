package org.xcolab.util.http.caching;

import org.xcolab.util.http.caching.CacheKey.Builder;

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
        final HashMap<String, String> parameters = new HashMap<>();
        parameters.put("id", stringId);
        return new CacheKey<>(elementType, parameters);
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
