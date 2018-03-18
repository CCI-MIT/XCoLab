package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.AbstractAttributeGetter;
import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeProvider;

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
