package org.xcolab.util.attributes.wrappers;

import org.springframework.util.Assert;

import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.exceptions.AttributeNotFoundException;
import org.xcolab.util.attributes.i18n.LocalizableAttributeGetter;

import static org.xcolab.util.attributes.i18n.LocalizableAttributeGetter.localizable;

public class OptionalAttribute<T> implements AttributeGetter<T> {

    protected final AttributeGetter<T> wrappedAttributeGetter;

    protected final T defaultValue;
    protected final AttributeGetter<T> defaultValueGetter;

    private OptionalAttribute(AttributeGetter<T> wrappedAttributeGetter, T defaultValue) {
        this.wrappedAttributeGetter = wrappedAttributeGetter;
        this.defaultValue = defaultValue;
        this.defaultValueGetter = null;
    }

    private OptionalAttribute(AttributeGetter<T> wrappedAttributeGetter,
            AttributeGetter<T> defaultValueGetter) {
        Assert.notNull(defaultValueGetter, "Default AttributeGetter is required.");
        this.wrappedAttributeGetter = wrappedAttributeGetter;
        this.defaultValue = null;
        this.defaultValueGetter = defaultValueGetter;
    }

    public static <T> OptionalAttribute<T> of(AttributeGetter<T> wrappedAttributeGetter,
            T defaultValue) {
        if (wrappedAttributeGetter instanceof LocalizableAttributeGetter) {
            return new LocalizableWrapper<>(localizable(wrappedAttributeGetter), defaultValue);
        }
        return new OptionalAttribute<>(wrappedAttributeGetter, defaultValue);
    }

    public static <T> OptionalAttribute<T> of(AttributeGetter<T> wrappedAttributeGetter,
            AttributeGetter<T> defaultValueGetter) {
        if (wrappedAttributeGetter instanceof LocalizableAttributeGetter) {
            return new LocalizableWrapper<>(localizable(wrappedAttributeGetter), defaultValueGetter);
        }
        return new OptionalAttribute<>(wrappedAttributeGetter, defaultValueGetter);
    }

    @Override
    public T get() {
        try {
            return wrappedAttributeGetter.get();
        } catch (AttributeNotFoundException e) {
            if (defaultValueGetter != null) {
                return defaultValueGetter.get();
            }
            return defaultValue;
        }
    }

    @Override
    public T get(long additionalId) {
        try {
            return wrappedAttributeGetter.get(additionalId);
        } catch (AttributeNotFoundException e) {
            if (defaultValueGetter != null) {
                return defaultValueGetter.get(additionalId);
            }
            return defaultValue;
        }
    }

    @Override
    public String name() {
        return wrappedAttributeGetter.name();
    }

    public static class LocalizableWrapper<T> extends OptionalAttribute<T>
            implements LocalizableAttributeGetter<T> {

        protected LocalizableWrapper(LocalizableAttributeGetter<T> wrappedAttributeGetter,
                T defaultValue) {
            super(wrappedAttributeGetter, defaultValue);
        }

        protected LocalizableWrapper(LocalizableAttributeGetter<T> wrappedAttributeGetter,
                AttributeGetter<T> defaultValueGetter) {
            super(wrappedAttributeGetter, defaultValueGetter);
        }

        @Override
        public T get(String locale) {
            try {
                return localizable(wrappedAttributeGetter).get(locale);
            } catch (AttributeNotFoundException e) {
                if (defaultValueGetter != null) {
                    return localizable(defaultValueGetter).get(locale);
                }
                return defaultValue;
            }
        }

        @Override
        public T get(String locale, long additionalId) {
            try {
                return localizable(wrappedAttributeGetter).get(locale, additionalId);
            } catch (AttributeNotFoundException e) {
                if (defaultValueGetter != null) {
                    return localizable(defaultValueGetter).get(locale, additionalId);
                }
                return defaultValue;
            }
        }
    }
}
