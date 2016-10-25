package org.xcolab.util.attributes;

import org.springframework.util.Assert;

public abstract class AbstractAttributeGetter<ValueT> implements AttributeGetter<ValueT> {

    private final AttributeProvider<? extends Attribute> attributeProvider;
    private final String getterName;

    public AbstractAttributeGetter(AttributeProvider<? extends Attribute> attributeProvider,
            String getterName) {
        Assert.notNull(attributeProvider, "AttributeProvider is required");
        Assert.notNull(getterName, "GetterName is required");
        this.getterName = getterName;
        this.attributeProvider = attributeProvider;
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
        result = 31 * result + getterName.hashCode();
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
                && getterName.equals(other.getterName);
    }
}
