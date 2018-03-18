package org.xcolab.util.attributes;

import org.xcolab.util.attributes.exceptions.AttributeNotFoundException;
import org.xcolab.util.attributes.wrappers.OptionalAttribute;

import java.util.Optional;

/**
 * Classes that implement this interface represent a wrapper around a certain {@link Attribute},
 * which extracts the value of a specified type.
 *
 * @param <ValueT> the type to be extracted
 */
public interface AttributeGetter<ValueT> {

    ValueT get();

    ValueT get(long additionalId);

    default Optional<ValueT> getOpt() {
        try {
            return Optional.of(get());
        } catch (AttributeNotFoundException e) {
            return Optional.empty();
        }
    }

    default Optional<ValueT> getOpt(long additionalId) {
        try {
            return Optional.of(get(additionalId));
        } catch (AttributeNotFoundException e) {
            return Optional.empty();
        }
    }

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
