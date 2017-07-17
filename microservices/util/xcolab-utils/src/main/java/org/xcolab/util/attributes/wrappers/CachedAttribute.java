package org.xcolab.util.attributes.wrappers;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.Assert;

import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.i18n.LocalizableAttributeGetter;

import java.util.HashMap;
import java.util.Map;

public class CachedAttribute<T> implements AttributeGetter<T> {

    protected final AttributeGetter<T> wrappedAttributeGetter;

    private T cachedValue;
    private final Map<Long, T> cachedValuesByAdditionalId = new HashMap<>();

    private CachedAttribute(AttributeGetter<T> wrappedAttributeGetter) {
        this.wrappedAttributeGetter = wrappedAttributeGetter;
    }

    public static <T> CachedAttribute<T> of(AttributeGetter<T> wrappedAttributeGetter) {
        if (wrappedAttributeGetter instanceof LocalizableAttributeGetter) {
            return new LocalizableCachedAttribute<>(wrappedAttributeGetter);
        }
        return new CachedAttribute<>(wrappedAttributeGetter);
    }

    @Override
    public T get() {
        if (cachedValue == null) {
            cachedValue = wrappedAttributeGetter.get();
        }
        return cachedValue;
    }

    @Override
    public T get(long additionalId) {
        return cachedValuesByAdditionalId
                .computeIfAbsent(additionalId, k -> wrappedAttributeGetter.get(additionalId));
    }

    @Override
    public String name() {
        return wrappedAttributeGetter.name();
    }

    private static class LocalizableCachedAttribute<T> extends CachedAttribute<T>
            implements LocalizableAttributeGetter<T> {

        private final Map<String, T> cachedValuesByLocale = new HashMap<>();
        private final Map<Pair<String, Long>, T> cachedValuesByLocaleAndAdditionalId = new HashMap<>();

        private LocalizableCachedAttribute(AttributeGetter<T> wrappedAttributeGetter) {
            super(wrappedAttributeGetter);
            Assert.isInstanceOf(LocalizableAttributeGetter.class, wrappedAttributeGetter,
                    "wrappedAttributeGetter must be localizable");
        }

        @Override
        public T get(String locale) {
            return cachedValuesByLocale
                    .computeIfAbsent(locale, k ->
                            getLocalizableAttributeGetter().get(locale));
        }

        @Override
        public T get(String locale, long additionalId) {
            return cachedValuesByLocaleAndAdditionalId
                    .computeIfAbsent(Pair.of(locale, additionalId), k ->
                            getLocalizableAttributeGetter().get(locale, additionalId));
        }

        private LocalizableAttributeGetter<T> getLocalizableAttributeGetter() {
            return (LocalizableAttributeGetter<T>) wrappedAttributeGetter;
        }
    }
}
