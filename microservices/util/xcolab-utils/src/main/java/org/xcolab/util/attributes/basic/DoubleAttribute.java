package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.AttributeProvider;

/**
 * A getter for floating point values from {@link Attribute}s.
 */
public class DoubleAttribute implements AttributeGetter<Double> {

    private final AttributeProvider<? extends Attribute> attributeProvider;

    public DoubleAttribute(AttributeProvider<? extends Attribute> attributeProvider) {
        this.attributeProvider = attributeProvider;
    }

    @Override
    public Double get() {
        return attributeProvider.get().getRealValue();
    }
    
    @Override
    public Double get(long additionalId) {
        return attributeProvider.get(additionalId).getRealValue();
    }
}
