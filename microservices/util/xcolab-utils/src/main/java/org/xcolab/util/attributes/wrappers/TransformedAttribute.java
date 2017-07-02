package org.xcolab.util.attributes.wrappers;

import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.i18n.LocalizableAttributeGetter;

import java.util.function.Function;

import static org.xcolab.util.attributes.i18n.LocalizableAttributeGetter.localizable;

public class TransformedAttribute<ValueT, ValueR> implements AttributeGetter<ValueR> {

    protected final AttributeGetter<ValueT> wrappedAttributeGetter;
    protected final Function<ValueT, ValueR> transform;

    private TransformedAttribute(AttributeGetter<ValueT> wrappedAttributeGetter,
            Function<ValueT, ValueR> transformation) {
        this.wrappedAttributeGetter = wrappedAttributeGetter;
        this.transform = transformation;
    }

    public static <ValueT, ValueR> TransformedAttribute<ValueT, ValueR> of(
            AttributeGetter<ValueT> wrappedAttributeGetter,
            Function<ValueT, ValueR> transformation) {
        if (wrappedAttributeGetter instanceof LocalizableAttributeGetter) {
            return new LocalizableWrapper<>(localizable(wrappedAttributeGetter), transformation);
        }
        return new TransformedAttribute<>(wrappedAttributeGetter, transformation);
    }

    @Override
    public ValueR get() {
        return transform.apply(wrappedAttributeGetter.get());
    }

    @Override
    public ValueR get(long additionalId) {
        return transform.apply(wrappedAttributeGetter.get(additionalId));
    }

    @Override
    public String name() {
        return wrappedAttributeGetter.name();
    }

    public static class LocalizableWrapper<ValueT, ValueR>
            extends TransformedAttribute<ValueT, ValueR>
            implements LocalizableAttributeGetter<ValueR>{

        private LocalizableWrapper(AttributeGetter<ValueT> wrappedAttributeGetter,
                Function<ValueT, ValueR> transformation) {
            super(wrappedAttributeGetter, transformation);
        }

        @Override
        public ValueR get(String locale) {
            return transform.apply(localizable(wrappedAttributeGetter).get(locale));
        }

        @Override
        public ValueR get(String locale, long additionalId) {
            return transform.apply(localizable(wrappedAttributeGetter).get(locale, additionalId));
        }
    }
}
