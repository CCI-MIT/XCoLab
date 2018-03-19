package org.xcolab.commons.attributes.basic;

import org.xcolab.commons.attributes.Attribute;
import org.xcolab.commons.attributes.AttributeProvider;

/**
 * A getter that parses the output of {@link Attribute#getStringValue()} as a comma-separated
 * list of IDs.
 */
public class IdListAttribute extends ListAttribute<Long> {

    public IdListAttribute(AttributeProvider<? extends Attribute> attributeProvider) {
        super(attributeProvider, Long::parseLong);
    }
}
