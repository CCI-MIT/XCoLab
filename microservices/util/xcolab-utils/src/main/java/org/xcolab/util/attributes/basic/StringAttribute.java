package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.AbstractAttributeGetter;
import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeProvider;

/**
 * A getter for string values from {@link Attribute}s.
 */
public class StringAttribute extends AbstractAttributeGetter<String> {

    public StringAttribute(AttributeProvider<? extends Attribute> attributeProvider) {
        super(attributeProvider);
    }

    @Override
    protected String extractValue(Attribute attribute) {
        return attribute.getStringValue();
    }
}
