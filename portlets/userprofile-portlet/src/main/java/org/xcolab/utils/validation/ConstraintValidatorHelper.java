package org.xcolab.utils.validation;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

public abstract class ConstraintValidatorHelper {

    public static <T> T getPropertyValue(Class<T> requiredType, String propertyName, Object instance) {
            if(requiredType == null) {
                throw new IllegalArgumentException("Invalid argument. requiredType must NOT be null!");
            }
            if(propertyName == null) {
                throw new IllegalArgumentException("Invalid argument. PropertyName must NOT be null!");
            }
            if(instance == null) {
                throw new IllegalArgumentException("Invalid argument. Object instance must NOT be null!");
            }
            T returnValue = null;
            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, instance.getClass());
                Method readMethod = descriptor.getReadMethod();
                if(readMethod == null) {
                    throw new IllegalStateException("Property '" + propertyName + "' of " + instance.getClass().getName() + " is NOT readable!");
                }
                if(requiredType.isAssignableFrom(readMethod.getReturnType())) {
                    try {
                        Object propertyValue = readMethod.invoke(instance);
                        returnValue = requiredType.cast(propertyValue);
                    } catch (Exception e) {
                        e.printStackTrace(); // unable to invoke readMethod
                    }
                }
            } catch (IntrospectionException e) {
                throw new IllegalArgumentException("Property '" + propertyName + "' is NOT defined in " + instance.getClass().getName() + "!", e);
            }
            return returnValue; 
        }

        public static boolean isValid(Collection<String> propertyValues, StringComparisonMode comparisonMode) {
            boolean ignoreCase = false;
            switch (comparisonMode) {
            case EQUAL_IGNORE_CASE:
            case NOT_EQUAL_IGNORE_CASE:
                ignoreCase = true;
            }

            List<String> values = new ArrayList<String> (propertyValues.size());
            for(String propertyValue : propertyValues) {
                if(ignoreCase) {
                    values.add(propertyValue.toLowerCase());
                } else {
                    values.add(propertyValue);
                }
            }

            switch (comparisonMode) {
            case EQUAL:
            case EQUAL_IGNORE_CASE:
                Set<String> uniqueValues = new HashSet<String> (values);
                // support all nulls
                return uniqueValues.size() == 1 || uniqueValues.size() == 0 ? true : false;
            case NOT_EQUAL:
            case NOT_EQUAL_IGNORE_CASE:
                Set<String> allValues = new HashSet<String> (values);
                return allValues.size() == values.size() ? true : false;
            }

            return true;
        }

        public static String resolveMessage(String[] propertyNames, StringComparisonMode comparisonMode) {
            StringBuffer buffer = concatPropertyNames(propertyNames);
            buffer.append(" must");
            switch(comparisonMode) {
            case EQUAL:
            case EQUAL_IGNORE_CASE:
                buffer.append(" be equal");
                break;
            case NOT_EQUAL:
            case NOT_EQUAL_IGNORE_CASE:
                buffer.append(" not be equal");
                break;
            }
            buffer.append('.');
            return buffer.toString();
        }

        private static StringBuffer concatPropertyNames(String[] propertyNames) {
            //TODO improve concating algorithm
            StringBuffer buffer = new StringBuffer();
            buffer.append('[');
            for(String propertyName : propertyNames) {
                char firstChar = Character.toUpperCase(propertyName.charAt(0));
                buffer.append(firstChar);
                buffer.append(propertyName.substring(1));
                buffer.append(", ");
            }
            buffer.delete(buffer.length()-2, buffer.length());
            buffer.append("]");
            return buffer;
        }
    }