package org.xcolab.view.util.validation;

import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.view.i18n.ResourceMessageResolver;

import javax.validation.ConstraintValidatorContext;

public class UniqueScreenNameValidator extends CustomValidator<UniqueScreenName> {

    private final ResourceMessageResolver resourceMessageResolver;
    private String screenNameProperty;

    @Autowired
    public UniqueScreenNameValidator(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void initialize(UniqueScreenName constraintAnnotation) {
        this.screenNameProperty = constraintAnnotation.screenNameProperty();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String screenName =
                ConstraintValidatorHelper.getPropertyValue(String.class, screenNameProperty, value);

        if (screenName == null) {
            return true;
        }

        boolean isValid = !SharedColabClient.isScreenNameUsed(screenName);

        if (!isValid) {
            boolean isDefaultMessage = "".equals(context.getDefaultConstraintMessageTemplate());

            if (isDefaultMessage) {
                String message = resourceMessageResolver
                        .getLocalizedMessage("register.form.validation.uniqueScreenName.message");
                context.disableDefaultConstraintViolation();
                processDefaultErrorMessage(message, false, context);
            }
        }

        return isValid;
    }
}
