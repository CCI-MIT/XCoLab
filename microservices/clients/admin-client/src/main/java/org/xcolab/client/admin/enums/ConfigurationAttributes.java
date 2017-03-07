package org.xcolab.client.admin.enums;

import org.xcolab.util.attributes.AttributeGetterBuilder;
import org.xcolab.util.attributes.Attributes;

import java.util.List;
import java.util.function.Function;

final class ConfigurationAttributes {

    private ConfigurationAttributes() {
    }

    public static AttributeGetterBuilder<String> newStringAttribute(String name) {
        return Attributes.newStringAttribute(new ConfigurationAttributeProvider(name));
    }


    public static AttributeGetterBuilder<Long> newLongAttribute(String name) {
        return Attributes.newLongAttribute(new ConfigurationAttributeProvider(name));
    }

    public static AttributeGetterBuilder<Boolean> newBooleanAttribute(String name) {
        return Attributes.newBooleanAttribute(new ConfigurationAttributeProvider(name));
    }

    public static AttributeGetterBuilder<Double> newDoubleAttribute(String name) {
        return Attributes.newDoubleAttribute(new ConfigurationAttributeProvider(name));
    }

    public static AttributeGetterBuilder<List<Long>> newIdListAttribute(String name) {
        return Attributes.newIdListAttribute(new ConfigurationAttributeProvider(name));
    }

    public static <T> AttributeGetterBuilder<List<T>> newListAttribute(String name,
            Function<String, T> conversionFunction) {
        return Attributes.newListAttribute(
                new ConfigurationAttributeProvider(name), conversionFunction);
    }

    public static <T extends Enum<T>> AttributeGetterBuilder<T> newEnumAttribute(
            String name, Class<T> enumType) {
        return Attributes.newEnumAttribute(new ConfigurationAttributeProvider(name), enumType);
    }

    public static <T> AttributeGetterBuilder<T> newJsonAttribute(String name, Class<T> entityType) {
        return Attributes.newJsonAttribute(new ConfigurationAttributeProvider(name), entityType);
    }
}
