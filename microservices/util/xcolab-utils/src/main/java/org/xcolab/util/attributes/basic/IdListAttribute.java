package org.xcolab.util.attributes.basic;

import org.xcolab.util.IdListUtil;
import org.xcolab.util.attributes.AbstractAttributeGetter;
import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeProvider;

import java.util.List;

/**
 * A getter that parses the output of {@link Attribute#getStringValue()} as a comma-separated
 * list of IDs.
 */
public class IdListAttribute extends AbstractAttributeGetter<List<Long>> {

    public IdListAttribute(AttributeProvider<? extends Attribute> attributeProvider) {
        super(attributeProvider);
    }

    @Override
    protected List<Long> extractValue(Attribute attribute) {
        return IdListUtil.getIdsFromString(attribute.getStringValue());
    }
}
