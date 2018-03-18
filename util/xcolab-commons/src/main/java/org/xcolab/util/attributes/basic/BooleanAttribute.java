package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.AbstractAttributeGetter;
import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeProvider;
import org.xcolab.util.attributes.exceptions.AttributeFormatException;

/**
 * A getter for boolean values from {@link Attribute}s.
 *
 * The BooleanAttribute getter uses the {@link Attribute#getNumericValue()}
 * and {@link Attribute#getStringValue()} methods and returns true
 * if the returned value is greater than zero or not equal to {@code 'true'}.
 */
public class BooleanAttribute extends AbstractAttributeGetter<Boolean> {

    public BooleanAttribute(AttributeProvider<? extends Attribute> attributeProvider) {
        super(attributeProvider);
    }

    @Override
    protected Boolean extractValue(Attribute attribute) {
        if (attribute.getNumericValue() != null) {
            return attribute.getNumericValue() > 0;
        }

        if (attribute.getStringValue() != null) {
            return Boolean.parseBoolean(attribute.getStringValue());
        }

        throw new AttributeFormatException("Non-null numeric or String value required");
    }
}
