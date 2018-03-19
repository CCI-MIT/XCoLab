package org.xcolab.commons.attributes.basic;

import org.xcolab.commons.attributes.AbstractAttributeGetter;
import org.xcolab.commons.attributes.Attribute;
import org.xcolab.commons.attributes.AttributeProvider;

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
