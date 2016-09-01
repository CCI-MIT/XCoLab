package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.AttributeProvider;

/**
 * A getter for boolean values from {@link Attribute}s.
 *
 * The BooleanAttribute getter uses the {@link Attribute#getNumericValue()} method and return true
 * if the returned value is greater than zero.
 */
public class BooleanAttribute implements AttributeGetter<Boolean> {

    private final AttributeProvider<? extends Attribute> attributeProvider;

    public BooleanAttribute(AttributeProvider<? extends Attribute> attributeProvider) {
        this.attributeProvider = attributeProvider;
    }

    @Override
    public Boolean get() {
        return attributeProvider.get().getNumericValue() > 0;
    }

    @Override
    public Boolean get(long additionalId) {
        return attributeProvider.get(additionalId).getNumericValue() > 0;
    }
}
