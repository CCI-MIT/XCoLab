package org.xcolab.commons.attributes.basic;

import org.xcolab.commons.attributes.Attribute;
import org.xcolab.commons.attributes.i18n.AbstractLocalizableAttributeGetter;
import org.xcolab.commons.attributes.i18n.LocalizableAttributeProvider;

/**
 * A getter for localized string values from {@link Attribute}s.
 */
public class LocalizedStringAttribute extends AbstractLocalizableAttributeGetter<String> {

    public LocalizedStringAttribute
            (LocalizableAttributeProvider<? extends Attribute> attributeProvider) {
        super(attributeProvider);
    }

    @Override
    protected String extractValue(Attribute attribute) {
        return attribute.getStringValue();
    }
}
