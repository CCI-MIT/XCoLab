package org.xcolab.util.attributes;

import org.xcolab.util.attributes.exceptions.AttributeNotFoundException;
import org.xcolab.util.attributes.wrappers.OptionalAttribute;

/**
 * Classes that implement this interface represent a wrapper around a certain {@link Attribute},
 * which extracts the value of a specified type.
 *
 * @param <ValueT> the type to be extracted
 */
public interface AttributeGetter<ValueT> {

    ValueT get();

    ValueT get(long additionalId);

    String name();

    default boolean isPresent() {
        try {
            get();
            return true;
        } catch (AttributeNotFoundException e) {
            return false;
        }
    }

    default boolean isPresent(long additionalId) {
        try {
            get(additionalId);
            return true;
        } catch (AttributeNotFoundException e) {
            return false;
        }
    }

    default AttributeGetter<ValueT> withDefaultValue(ValueT defaultValue) {
        return OptionalAttribute.of(this, defaultValue);
    }
}
