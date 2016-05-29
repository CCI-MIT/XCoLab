package org.xcolab.portlets.userprofile.validators;

import org.springframework.util.StringUtils;
import org.xcolab.enums.ColabConstants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public abstract class CustomValidator<T extends Annotation> implements ConstraintValidator<T, Object> {
    protected static final long DEFAULT_COMPANY_ID = ColabConstants.COLAB_COMPANY_ID;

    protected void processDefaultErrorMessage(String message, boolean isValid, ConstraintValidatorContext context) {
        if (isValid && StringUtils.isEmpty(context.getDefaultConstraintMessageTemplate())) {
            context.disableDefaultConstraintViolation();
            ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder =
                    context.buildConstraintViolationWithTemplate(message);
            violationBuilder.addConstraintViolation();
        }
    }
}
