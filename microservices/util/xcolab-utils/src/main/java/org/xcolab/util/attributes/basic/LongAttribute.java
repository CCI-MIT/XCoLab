package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.AbstractAttributeGetter;
import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeProvider;

/**
 * A getter for long values from {@link Attribute}s.
 */
public class LongAttribute extends AbstractAttributeGetter<Long> {

    public LongAttribute(AttributeProvider<? extends Attribute> attributeProvider) {
        super(attributeProvider);
    }

    @Override
    protected Long extractValue(Attribute attribute) {
        return attribute.getNumericValue();
    }
}
