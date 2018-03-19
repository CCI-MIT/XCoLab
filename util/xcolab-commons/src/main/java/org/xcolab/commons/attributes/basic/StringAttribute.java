package org.xcolab.commons.attributes.basic;

import org.xcolab.commons.attributes.AbstractAttributeGetter;
import org.xcolab.commons.attributes.Attribute;
import org.xcolab.commons.attributes.AttributeProvider;

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
