package org.xcolab.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=CompareStringsValidator.class)
@Documented
public @interface CompareStrings {
    String[] propertyNames();
    StringComparisonMode matchMode() default StringComparisonMode.EQUAL;
    boolean allowNull() default true;
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}