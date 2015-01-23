package org.xcolab.portlets.userprofile.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UniqueScreenNameValidator.class)
@Documented
public @interface UniqueScreenName {
    String screenNameProperty();
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}