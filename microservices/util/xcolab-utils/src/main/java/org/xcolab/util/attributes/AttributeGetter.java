package org.xcolab.util.attributes;

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
}
