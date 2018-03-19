package org.xcolab.commons.attributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.commons.attributes.i18n.LocalizableAttributeGetter;
import org.xcolab.commons.attributes.wrappers.CachedAttribute;
import org.xcolab.commons.attributes.wrappers.OptionalAttribute;
import org.xcolab.commons.attributes.wrappers.TransformedAttribute;

import java.util.function.Function;

/**
 * This class is a convenience builder for various {@link AttributeGetter}s.
 *
 * @param <ValueT> the value type returned be the built {@link AttributeGetter}
 */
public class AttributeGetterBuilder<ValueT> {

    private static final Logger log = LoggerFactory.getLogger(AttributeGetterBuilder.class);

    private AttributeGetter<ValueT> attributeGetter;

    public AttributeGetterBuilder(AttributeGetter<ValueT> attributeGetter) {
        this.attributeGetter = attributeGetter;
    }

    public AttributeGetterBuilder<ValueT> withCache() {
        attributeGetter = CachedAttribute.of(attributeGetter);
        return this;
    }

    public AttributeGetterBuilder<ValueT> defaultValue(ValueT defaultValue) {
        attributeGetter = OptionalAttribute.of(attributeGetter, defaultValue);
        return this;
    }

    public AttributeGetterBuilder<ValueT> defaultValue(AttributeGetter<ValueT> defaultValueGetter) {
        attributeGetter = OptionalAttribute.of(attributeGetter, defaultValueGetter);
        return this;
    }

    public <ValueR> AttributeGetterBuilder<ValueR>  map(Function<ValueT, ValueR> transformation) {
        final TransformedAttribute<ValueT, ValueR> newAttributeGetter =
                TransformedAttribute.of(attributeGetter, transformation);
        return new AttributeGetterBuilder<>(newAttributeGetter);
    }

    public AttributeGetter<ValueT> build() {
        return attributeGetter;
    }

    public LocalizableAttributeGetter<ValueT> buildLocalizable() {
        if (! (attributeGetter instanceof LocalizableAttributeGetter)) {
            final String message =
                    "AttributeGetter " + attributeGetter.name() + " of class " + attributeGetter
                            .getClass().getSimpleName() + " is not localizable.";
            log.error(message);
            //noinspection ProhibitedExceptionThrown
            throw new ClassCastException(message);
        }
        return (LocalizableAttributeGetter<ValueT>) attributeGetter;
    }
}
