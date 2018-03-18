package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeProvider;

/**
 * A getter that parses the output of {@link Attribute#getStringValue()} as a comma-separated
 * list of IDs.
 */
public class IdListAttribute extends ListAttribute<Long> {

    public IdListAttribute(AttributeProvider<? extends Attribute> attributeProvider) {
        super(attributeProvider, Long::parseLong);
    }
}
