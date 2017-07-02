package org.xcolab.util.attributes.i18n;

import org.xcolab.util.attributes.AbstractAttributeGetter;
import org.xcolab.util.attributes.Attribute;
import org.xcolab.util.attributes.exceptions.AttributeNotFoundException;

public abstract class AbstractLocalizableAttributeGetter<ValueT>
        extends AbstractAttributeGetter<ValueT>
        implements LocalizableAttributeGetter<ValueT> {

    public AbstractLocalizableAttributeGetter(
            LocalizableAttributeProvider<? extends Attribute> attributeProvider) {
        super(attributeProvider);
    }

    @Override
    public String name() {
        return getLocalizableAttributeProvider().name();
    }

    @Override
    public ValueT get() {
        return extractValue(getLocalizableAttributeProvider().get());
    }

    @Override
    public ValueT get(long additionalId) {
        return extractValue(getLocalizableAttributeProvider().get(additionalId));
    }

    @Override
    public ValueT get(String locale) {
        try {
            return extractValue(getLocalizableAttributeProvider().get(locale));
        } catch (AttributeNotFoundException e) {
            // try with default locale
            return get();
        }
    }

    @Override
    public ValueT get(String locale, long additionalId) {
        try {
            return extractValue(getLocalizableAttributeProvider().get(locale, additionalId));
        } catch (AttributeNotFoundException e) {
            // try with default locale
            return get(additionalId);
        }
    }

    private LocalizableAttributeProvider<Attribute> getLocalizableAttributeProvider() {
        //noinspection unchecked
        return (LocalizableAttributeProvider<Attribute>) getAttributeProvider();
    }

}
