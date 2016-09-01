package org.xcolab.util.attributes;

import org.xcolab.util.attributes.basic.BooleanAttribute;
import org.xcolab.util.attributes.basic.DoubleAttribute;
import org.xcolab.util.attributes.basic.EnumAttribute;
import org.xcolab.util.attributes.basic.IdListAttribute;
import org.xcolab.util.attributes.basic.LongAttribute;
import org.xcolab.util.attributes.basic.StringAttribute;

import java.util.List;

/**
 * This class contains static factory methods to build {@link AttributeGetter}s of various types.
 */
public final class Attributes {

    private Attributes() {
    }

    public static AttributeGetterBuilder<String> newStringAttribute(
            AttributeProvider<?> attributeProvider) {
        return new AttributeGetterBuilder<>(new StringAttribute(attributeProvider));
    }

    public static AttributeGetterBuilder<Long> newLongAttribute(
            AttributeProvider<?> attributeProvider) {
        return new AttributeGetterBuilder<>(new LongAttribute(attributeProvider));
    }

    public static AttributeGetterBuilder<Boolean> newBooleanAttribute(
            AttributeProvider<?> attributeProvider) {
        return new AttributeGetterBuilder<>(new BooleanAttribute(attributeProvider));
    }

    public static AttributeGetterBuilder<Double> newDoubleAttribute(
            AttributeProvider<?> attributeProvider) {
        return new AttributeGetterBuilder<>(new DoubleAttribute(attributeProvider));
    }

    public static AttributeGetterBuilder<List<Long>> newIdListAttribute(
            AttributeProvider<?> attributeProvider) {
        return new AttributeGetterBuilder<>(new IdListAttribute(attributeProvider));
    }

    public static <T extends Enum<T>> AttributeGetterBuilder<T> newEnumAttribute(
            AttributeProvider<?> attributeProvider, Class<T> enumType) {
        return new AttributeGetterBuilder<>(new EnumAttribute<>(attributeProvider, enumType));
    }
}
