package org.xcolab.util.attributes.wrappers;

import org.xcolab.util.attributes.AttributeGetter;

import java.util.function.Function;

public class TransformedAttribute<ValueT, ValueR> implements AttributeGetter<ValueR> {
    private final AttributeGetter<ValueT> wrappedAttributeGetter;
    private final Function<ValueT, ValueR> transform;

    public TransformedAttribute(AttributeGetter<ValueT> wrappedAttributeGetter,
            Function<ValueT, ValueR> transformation) {
        this.wrappedAttributeGetter = wrappedAttributeGetter;
        this.transform = transformation;
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
}
