package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.AbstractAttributeGetter;
import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeProvider;
import org.xcolab.util.attributes.exceptions.AttributeFormatException;

/**
 * A getter for boolean values from {@link Attribute}s.
 *
 * The BooleanAttribute getter uses the {@link Attribute#getNumericValue()} method and returns true
 * if the returned value is greater than zero.
 */
public class BooleanAttribute extends AbstractAttributeGetter<Boolean> {

    public BooleanAttribute(AttributeProvider<? extends Attribute> attributeProvider) {
        super(attributeProvider);
    }

    @Override
    protected Boolean extractValue(Attribute attribute) {
        if (attribute.getNumericValue() == null) {
            throw new AttributeFormatException("Non-null numeric value required");
        }
        return attribute.getNumericValue() > 0;
    }
}
