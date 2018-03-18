package org.xcolab.commons.attributes.i18n;

import org.xcolab.commons.attributes.Attribute;
import org.xcolab.commons.attributes.AttributeProvider;

public interface LocalizableAttributeProvider<AttributeT extends Attribute>
        extends AttributeProvider<AttributeT> {

    AttributeT get(String locale);

    AttributeT get(String locale, long additionalId);
}
