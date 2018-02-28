package org.xcolab.view.util.validation;

import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.view.i18n.ResourceMessageResolver;

import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator extends CustomValidator<UniqueEmail> {

    private final ResourceMessageResolver resourceMessageResolver;
    private String emailProperty;

    @Autowired
    public UniqueEmailValidator(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        emailProperty = constraintAnnotation.emailProperty();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String email =
                ConstraintValidatorHelper.getPropertyValue(String.class, emailProperty, value);

        if (email == null) {
            return true;
        }

        boolean isValid = !SharedColabClient.isEmailUsed(email);

        if (!isValid) {
            boolean isDefaultMessage = "".equals(context.getDefaultConstraintMessageTemplate());

            if (isDefaultMessage) {
                String message = resourceMessageResolver
                        .getLocalizedMessage("register.form.validation.uniqueEmail.message");
                context.disableDefaultConstraintViolation();
                processDefaultErrorMessage(message, false, context);
            }
        }

        return isValid;
    }
}
