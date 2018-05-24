package org.xcolab.view.util.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.view.i18n.ResourceMessageResolver;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

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

    public static String resolveMessage(String[] propertyNames,
            ResourceMessageResolver messageResolver) {
        return messageResolver.getLocalizedMessage("register.form.validation.equalfields",
                propertyNames[0], propertyNames[1]);
    }
}
