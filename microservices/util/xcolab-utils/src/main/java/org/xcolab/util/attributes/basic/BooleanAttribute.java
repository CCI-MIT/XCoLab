package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.AbstractAttributeGetter;
import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeProvider;

/**
 * A getter for boolean values from {@link Attribute}s.
 *
 * The BooleanAttribute getter uses the {@link Attribute#getNumericValue()} method and return true
 * if the returned value is greater than zero.
 */
public class BooleanAttribute extends AbstractAttributeGetter<Boolean> {

    public BooleanAttribute(AttributeProvider<? extends Attribute> attributeProvider) {
        super(attributeProvider);
    }

    @Override
    protected Boolean extractValue(Attribute attribute) {
        return attribute.getNumericValue() > 0;
    }
}
