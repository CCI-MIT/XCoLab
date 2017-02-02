package org.xcolab.util.attributes;

import org.xcolab.util.attributes.wrappers.CachedAttribute;
import org.xcolab.util.attributes.wrappers.OptionalAttribute;

/**
 * This class is a convenience builder for various {@link AttributeGetter}s.
 *
 * @param <ValueT> the value type returned be the built {@link AttributeGetter}
 */
public class AttributeGetterBuilder<ValueT> {

    private AttributeGetter<ValueT> attributeGetter;

    public AttributeGetterBuilder(AttributeGetter<ValueT> attributeGetter) {
        this.attributeGetter = attributeGetter;
    }

    public AttributeGetterBuilder<ValueT> withCache() {
        attributeGetter = new CachedAttribute<>(attributeGetter);
        return this;
    }

    public AttributeGetterBuilder<ValueT> defaultValue(ValueT defaultValue) {
        attributeGetter = new OptionalAttribute<>(attributeGetter, defaultValue);
        return this;
    }

    public AttributeGetterBuilder<ValueT> defaultValue(AttributeGetter<ValueT> defaultValueGetter) {
        attributeGetter = new OptionalAttribute<>(attributeGetter, defaultValueGetter);
        return this;
    }

    public AttributeGetter<ValueT> build() {
        return attributeGetter;
    }
}
