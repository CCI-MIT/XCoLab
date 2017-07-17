package org.xcolab.util.attributes.i18n;

import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.AttributeProvider;

public interface LocalizableAttributeProvider<AttributeT extends Attribute>
        extends AttributeProvider<AttributeT> {

    AttributeT get(String locale);

    AttributeT get(String locale, long additionalId);
}
