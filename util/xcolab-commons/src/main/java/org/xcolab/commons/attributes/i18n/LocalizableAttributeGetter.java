package org.xcolab.commons.attributes.i18n;

import org.xcolab.commons.attributes.AttributeGetter;
import org.xcolab.commons.attributes.exceptions.AttributeNotFoundException;

import java.util.Optional;

public interface LocalizableAttributeGetter<ValueT> extends AttributeGetter<ValueT> {

    ValueT get(String locale);

    ValueT get(String locale, long additionalId);

    default Optional<ValueT> getOpt(String locale) {
        try {
            return Optional.of(get(locale));
        } catch (AttributeNotFoundException e) {
            return Optional.empty();
        }
    }

    default Optional<ValueT> getOpt(String locale, long additionalId) {
        try {
            return Optional.of(get(locale, additionalId));
        } catch (AttributeNotFoundException e) {
            return Optional.empty();
        }
    }

    static <T> LocalizableAttributeGetter<T> localizable(AttributeGetter<T> attributeGetter) {
        return (LocalizableAttributeGetter<T>) attributeGetter;
    }
}
