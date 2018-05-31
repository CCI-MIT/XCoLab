package org.xcolab.view.util.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validates if two strings are equal.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CompareStringsValidator.class)
@Documented
public @interface CompareStrings {

    /**
     * Names of exactly two properties in the bean to be compared.
     */
    String[] propertyNames();

    /**
     * Whether to consider case when comparing the strings.
     */
    StringComparisonMode comparisonMode() default StringComparisonMode.EQUAL;

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
