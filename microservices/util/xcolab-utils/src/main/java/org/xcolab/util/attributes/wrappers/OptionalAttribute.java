package org.xcolab.util.attributes.wrappers;

import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.exceptions.AttributeNotFoundException;

public class OptionalAttribute<T> implements AttributeGetter<T> {

    private final AttributeGetter<T> wrappedAttributeGetter;
    private final T defaultValue;

    public OptionalAttribute(AttributeGetter<T> wrappedAttributeGetter, T defaultValue) {

        this.wrappedAttributeGetter = wrappedAttributeGetter;
        this.defaultValue = defaultValue;
    }

    @Override
    public T get() {
        try {
            return wrappedAttributeGetter.get();
        } catch (AttributeNotFoundException e) {
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
