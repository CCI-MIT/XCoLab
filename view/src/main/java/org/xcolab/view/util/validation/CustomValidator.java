package org.xcolab.view.util.validation;

import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public abstract class CustomValidator<T extends Annotation>
        implements ConstraintValidator<T, Object> {

    protected void processDefaultErrorMessage(String message, boolean isValid,
            ConstraintValidatorContext context) {
        if (!isValid && StringUtils.isEmpty(context.getDefaultConstraintMessageTemplate())) {
            context.disableDefaultConstraintViolation();
            ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder =
                    context.buildConstraintViolationWithTemplate(message);
            violationBuilder.addConstraintViolation();
        }
    }
}
