package org.xcolab.util.attributes.basic;

import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.i18n.AbstractLocalizableAttributeGetter;
import org.xcolab.util.attributes.i18n.LocalizableAttributeProvider;

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
