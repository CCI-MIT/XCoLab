package org.xcolab.util.attributes.wrappers;

import org.springframework.util.Assert;

import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.exceptions.AttributeNotFoundException;

public class OptionalAttribute<T> implements AttributeGetter<T> {

    private final AttributeGetter<T> wrappedAttributeGetter;
    private final T defaultValue;
    private final AttributeGetter<T> defaultValueGetter;

    public OptionalAttribute(AttributeGetter<T> wrappedAttributeGetter, T defaultValue) {
        this.wrappedAttributeGetter = wrappedAttributeGetter;
        this.defaultValue = defaultValue;
        this.defaultValueGetter = null;
    }

    public OptionalAttribute(AttributeGetter<T> wrappedAttributeGetter,
            AttributeGetter<T> defaultValueGetter) {
        Assert.notNull(defaultValueGetter, "Default AttributeGetter is required.");
        this.wrappedAttributeGetter = wrappedAttributeGetter;
        this.defaultValue = null;
        this.defaultValueGetter = defaultValueGetter;
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
            return defaultValue;
        }
    }

    @Override
    public String name() {
        return wrappedAttributeGetter.name();
    }
}
