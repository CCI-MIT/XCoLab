package org.xcolab.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by kmang on 27/04/14.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=ValidBioLengthValidator.class)
@Documented
public @interface ValidBioLength {
	String bioProperty();
	StringComparisonMode matchMode() default StringComparisonMode.EQUAL;
	boolean allowNull() default false;
	String message() default "";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
