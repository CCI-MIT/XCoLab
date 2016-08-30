package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.AttributeProvider;
import org.xcolab.util.IdListUtil;

import java.util.List;

/**
 * A getter that parses the output of {@link Attribute#getStringValue()} as a comma-separated
 * list of IDs.
 */
public class IdListAttribute implements AttributeGetter<List<Long>> {

    private final AttributeProvider<? extends Attribute> attributeProvider;

    public IdListAttribute(AttributeProvider<? extends Attribute> attributeProvider) {
        this.attributeProvider = attributeProvider;
    }

    @Override
    public List<Long> get() {
        return getList(attributeProvider.get());
    }

    @Override
    public List<Long> get(long additionalId) {
        return getList(attributeProvider.get(additionalId));
    }

    private List<Long> getList(Attribute attribute) {
        return IdListUtil.getIdsFromString(attribute.getStringValue());
    }
}
