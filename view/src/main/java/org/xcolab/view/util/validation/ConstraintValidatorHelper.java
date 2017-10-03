package org.xcolab.view.util.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.view.i18n.ResourceMessageResolver;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ConstraintValidatorHelper {

    private static final Logger _log = LoggerFactory.getLogger(ConstraintValidatorHelper.class);

    public static <T> T getPropertyValue(Class<T> requiredType, String propertyName,
            Object instance) {
        if (requiredType == null) {
            throw new IllegalArgumentException("Invalid argument. requiredType must NOT be null!");
        }
        if (propertyName == null) {
            throw new IllegalArgumentException("Invalid argument. PropertyName must NOT be null!");
        }
        if (instance == null) {
            throw new IllegalArgumentException(
                    "Invalid argument. Object instance must NOT be null!");
        }
        T returnValue = null;
        try {
            PropertyDescriptor descriptor =
                    new PropertyDescriptor(propertyName, instance.getClass());
            Method readMethod = descriptor.getReadMethod();
            if (readMethod == null) {
                throw new IllegalStateException(
                        "Property '" + propertyName + "' of " + instance.getClass().getName()
                                + " is NOT readable!");
            }
            if (requiredType.isAssignableFrom(readMethod.getReturnType())) {
                try {
                    Object propertyValue = readMethod.invoke(instance);
                    returnValue = requiredType.cast(propertyValue);
                } catch (Exception e) {
                    _log.error("Error invoking readMethod in constraint validator", e);
                }
            }
        } catch (IntrospectionException e) {
            throw new IllegalArgumentException(
                    "Property '" + propertyName + "' is NOT defined in " + instance.getClass()
                            .getName() + "!", e);
        }
        return returnValue;
    }

    public static boolean isValid(Collection<String> propertyValues,
            StringComparisonMode comparisonMode) {
        boolean ignoreCase = false;
        switch (comparisonMode) {
            case EQUAL_IGNORE_CASE:
                ignoreCase = true;
        }

        List<String> values = new ArrayList<>(propertyValues.size());
        for (String propertyValue : propertyValues) {
            if (ignoreCase) {
                values.add(propertyValue.toLowerCase());
            } else {
                values.add(propertyValue);
            }
        }

        Set<String> uniqueValues = new HashSet<>(values);
        // support all nulls
        return uniqueValues.size() == 1 || uniqueValues.isEmpty();
    }

    public static String resolveMessage(String[] propertyNames,
            ResourceMessageResolver messageResolver) {
        StringBuffer buffer = concatPropertyNames(propertyNames);
        buffer.append(" ").append(messageResolver
                .getLocalizedMessage("register.form.validation.equalfields"));
        buffer.append('.');
        return buffer.toString();
    }

    private static StringBuffer concatPropertyNames(String[] propertyNames) {
        //TODO improve concatenation
        StringBuffer buffer = new StringBuffer();
        buffer.append('[');
        for (String propertyName : propertyNames) {
            char firstChar = Character.toUpperCase(propertyName.charAt(0));
            buffer.append(firstChar);
            buffer.append(propertyName.substring(1));
            buffer.append(", ");
        }
        buffer.delete(buffer.length() - 2, buffer.length());
        buffer.append("]");
        return buffer;
    }
}
