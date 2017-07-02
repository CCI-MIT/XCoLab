package org.xcolab.client.admin.attributes.platform;

import org.springframework.core.env.PropertyResolver;

import org.xcolab.util.HomeDirPropertyResolver;
import org.xcolab.util.attributes.AttributeGetterBuilder;
import org.xcolab.util.attributes.Attributes;

import java.util.List;
import java.util.function.Function;

public final class PlatformAttributes {

    private static final PropertyResolver propertyResolver =
            new HomeDirPropertyResolver(true);

    private PlatformAttributes() {
    }

    public static AttributeGetterBuilder<String> newStringAttribute(String key) {
        return Attributes.newStringAttribute(new PlatformAttributeProvider(propertyResolver, key));
    }


    public static AttributeGetterBuilder<Long> newLongAttribute(String key) {
        return Attributes.newLongAttribute(new PlatformAttributeProvider(propertyResolver, key));
    }

    public static AttributeGetterBuilder<Boolean> newBooleanAttribute(String key) {
        return Attributes.newBooleanAttribute(new PlatformAttributeProvider(propertyResolver, key));
    }

    public static AttributeGetterBuilder<Double> newDoubleAttribute(String key) {
        return Attributes.newDoubleAttribute(new PlatformAttributeProvider(propertyResolver, key));
    }

    public static AttributeGetterBuilder<List<Long>> newIdListAttribute(String key) {
        return Attributes.newIdListAttribute(new PlatformAttributeProvider(propertyResolver, key));
    }

    public static <T> AttributeGetterBuilder<List<T>> newListAttribute(String key,
            Function<String, T> conversionFunction) {
        return Attributes.newListAttribute(
                new PlatformAttributeProvider(propertyResolver, key), conversionFunction);
    }

    public static <T extends Enum<T>> AttributeGetterBuilder<T> newEnumAttribute(
            String key, Class<T> enumType) {
        return Attributes.newEnumAttribute(
                new PlatformAttributeProvider(propertyResolver, key), enumType);
    }

    public static <T> AttributeGetterBuilder<T> newJsonAttribute(String key, Class<T> entityType) {
        return Attributes.newJsonAttribute(
                new PlatformAttributeProvider(propertyResolver, key), entityType);
    }
}
