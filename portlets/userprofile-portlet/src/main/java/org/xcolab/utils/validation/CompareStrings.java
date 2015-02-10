package org.xcolab.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

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