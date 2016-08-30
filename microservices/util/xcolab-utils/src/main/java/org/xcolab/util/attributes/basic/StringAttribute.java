package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.AttributeProvider;

/**
 * A getter for string values from {@link Attribute}s.
 */
public class StringAttribute implements AttributeGetter<String> {

    private final AttributeProvider<? extends Attribute> attributeProvider;

    public StringAttribute(AttributeProvider<? extends Attribute> attributeProvider) {
        this.attributeProvider = attributeProvider;
    }

    @Override
    public String get() {
        return attributeProvider.get().getStringValue();
    }

    @Override
    public String get(long additionalId) {
        return attributeProvider.get(additionalId).getStringValue();
    }
}
