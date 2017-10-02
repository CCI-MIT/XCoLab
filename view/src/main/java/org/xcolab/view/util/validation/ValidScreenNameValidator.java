package org.xcolab.view.util.validation;

import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.view.i18n.ResourceMessageResolver;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

public class ValidScreenNameValidator implements ConstraintValidator<ValidScreenName, Object> {

    private String screenNameProperty;

    private final ResourceMessageResolver resourceMessageResolver;

    @Autowired
    public ValidScreenNameValidator(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void initialize(ValidScreenName constraintAnnotation) {
        screenNameProperty = constraintAnnotation.screenNameProperty();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String screenName =
                ConstraintValidatorHelper.getPropertyValue(String.class, screenNameProperty, value);
        if (screenName == null) {
            // ignore in case of null. Another validator will care about this
            return true;
        }

        if (validateScreenName(screenName)) {
            return true;
        } else {
            boolean isDefaultMessage = "".equals(context.getDefaultConstraintMessageTemplate());
            /*
             * if custom message was provided, don't touch it, otherwise build
			 * the default message
			 */
            if (isDefaultMessage) {
                String message = resourceMessageResolver.getLocalizedMessage(
                        "register.form.validation.screenNameContent.screenNameInvalid");
                context.disableDefaultConstraintViolation();
                ConstraintViolationBuilder violationBuilder =
                        context.buildConstraintViolationWithTemplate(message);
                violationBuilder.addConstraintViolation();
            }

            return false;
        }
    }

    public boolean validateScreenName(String name) {
        return name != null && name.matches("^[a-zA-Z0-9]+$");
    }
}
