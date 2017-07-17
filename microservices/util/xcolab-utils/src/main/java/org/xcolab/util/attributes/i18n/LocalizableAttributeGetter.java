package org.xcolab.util.attributes.i18n;

import org.xcolab.util.attributes.AttributeGetter;

public interface LocalizableAttributeGetter<ValueT> extends AttributeGetter<ValueT> {

    ValueT get(String locale);

    ValueT get(String locale, long additionalId);

    static <T> LocalizableAttributeGetter<T> localizable(AttributeGetter<T> attributeGetter) {
        return (LocalizableAttributeGetter<T>) attributeGetter;
    }
}
