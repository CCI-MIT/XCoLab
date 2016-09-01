package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.AttributeProvider;

/**
 * A getter for long values from {@link Attribute}s.
 */
public class LongAttribute implements AttributeGetter<Long> {

    private final AttributeProvider<? extends Attribute> attributeProvider;

    public LongAttribute(AttributeProvider<? extends Attribute> attributeProvider) {
        this.attributeProvider = attributeProvider;
    }

    @Override
    public Long get() {
        return attributeProvider.get().getNumericValue();
    }

    @Override
    public Long get(long additionalId) {
        return attributeProvider.get(additionalId).getNumericValue();
    }
}
