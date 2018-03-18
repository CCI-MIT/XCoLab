package org.xcolab.commons.attributes.basic;

import org.xcolab.commons.attributes.AbstractAttributeGetter;
import org.xcolab.commons.attributes.Attribute;
import org.xcolab.commons.attributes.AttributeProvider;

/**
 * A getter for floating point values from {@link Attribute}s.
 */
public class DoubleAttribute extends AbstractAttributeGetter<Double> {

    public DoubleAttribute(AttributeProvider<? extends Attribute> attributeProvider) {
        super(attributeProvider);
    }

    @Override
    protected Double extractValue(Attribute attribute) {
        return attribute.getRealValue();
    }
}
