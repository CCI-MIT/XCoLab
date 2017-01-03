package org.xcolab.util.attributes;

import org.springframework.util.Assert;

public abstract class AbstractAttributeGetter<ValueT> implements AttributeGetter<ValueT> {

    private final AttributeProvider<? extends Attribute> attributeProvider;

    public AbstractAttributeGetter(AttributeProvider<? extends Attribute> attributeProvider) {
        Assert.notNull(attributeProvider, "AttributeProvider is required");
        this.attributeProvider = attributeProvider;
    }

    @Override
    public String name() {
        return attributeProvider.name();
    }

    @Override
    public ValueT get() {
        return extractValue(attributeProvider.get());
    }

    @Override
    public ValueT get(long additionalId) {
        return extractValue(attributeProvider.get(additionalId));
    }

    protected abstract ValueT extractValue(Attribute attribute);


    @Override
    public int hashCode() {
        int result = attributeProvider.hashCode();
        result = 31 * result + getClass().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof AbstractAttributeGetter)) {
            return false;
        }

        AbstractAttributeGetter other = (AbstractAttributeGetter) obj;
        return attributeProvider.equals(other.attributeProvider)
                && getClass().equals(other.getClass());
    }
}
