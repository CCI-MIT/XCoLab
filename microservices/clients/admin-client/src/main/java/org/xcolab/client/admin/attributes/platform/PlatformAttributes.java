package org.xcolab.client.admin.attributes.platform;

import org.springframework.core.env.PropertyResolver;

import org.xcolab.commons.HomeDirPropertyResolver;
import org.xcolab.commons.attributes.AttributeGetterBuilder;
import org.xcolab.commons.attributes.Attributes;

import java.util.List;
import java.util.function.Function;

public final class PlatformAttributes {

    private static final String FILE_BASE_NAME = ".xcolab.application";

    private static final PropertyResolver propertyResolver =
            new HomeDirPropertyResolver(FILE_BASE_NAME, true);

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
