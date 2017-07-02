package org.xcolab.util.attributes.i18n;

import org.xcolab.util.attributes.AbstractAttributeGetter;
import org.xcolab.util.attributes.Attribute;

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
        return extractValue(getLocalizableAttributeProvider().get(locale));
    }

    @Override
    public ValueT get(String locale, long additionalId) {
        return extractValue(getLocalizableAttributeProvider().get(locale, additionalId));
    }

    private LocalizableAttributeProvider<Attribute> getLocalizableAttributeProvider() {
        //noinspection unchecked
        return (LocalizableAttributeProvider<Attribute>) getAttributeProvider();
    }

}
