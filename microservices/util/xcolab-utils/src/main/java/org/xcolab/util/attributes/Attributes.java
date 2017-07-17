package org.xcolab.util.attributes;

import org.xcolab.util.attributes.basic.BooleanAttribute;
import org.xcolab.util.attributes.basic.DoubleAttribute;
import org.xcolab.util.attributes.basic.EnumAttribute;
import org.xcolab.util.attributes.basic.IdListAttribute;
import org.xcolab.util.attributes.basic.JsonAttribute;
import org.xcolab.util.attributes.basic.ListAttribute;
import org.xcolab.util.attributes.basic.LocalizedStringAttribute;
import org.xcolab.util.attributes.basic.LongAttribute;
import org.xcolab.util.attributes.basic.StringAttribute;
import org.xcolab.util.attributes.i18n.LocalizableAttributeProvider;

import java.util.List;
import java.util.function.Function;

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

    public static AttributeGetterBuilder<String> newLocalizedStringAttribute(
            LocalizableAttributeProvider<?> attributeProvider) {
        return new AttributeGetterBuilder<>(new LocalizedStringAttribute(attributeProvider));
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

    public static <T> AttributeGetterBuilder<List<T>> newListAttribute(
            AttributeProvider<?> attributeProvider, Function<String, T> conversionFunction) {
        return new AttributeGetterBuilder<>(
                new ListAttribute<>(attributeProvider, conversionFunction));
    }

    public static <T extends Enum<T>> AttributeGetterBuilder<T> newEnumAttribute(
            AttributeProvider<?> attributeProvider, Class<T> enumType) {
        return new AttributeGetterBuilder<>(new EnumAttribute<>(attributeProvider, enumType));
    }

    public static <T> AttributeGetterBuilder<T> newJsonAttribute(
            AttributeProvider<?> attributeProvider, Class<T> entityType) {
        return new AttributeGetterBuilder<>(new JsonAttribute<>(attributeProvider, entityType));
    }
}
