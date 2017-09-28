package org.xcolab.view.util.validation;

import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.view.i18n.ResourceMessageResolver;

import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator extends CustomValidator<UniqueEmail> {

    private String emailProperty;

    private final ResourceMessageResolver resourceMessageResolver;

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
                StringBuilder message = new StringBuilder();
                message.append(resourceMessageResolver
                        .getLocalizedMessage("register.form.validation.uniqueEmail.message"));

                if (ConfigurationAttributeKey.IS_SHARED_COLAB.get()) {
                    message.append("<br />").append(resourceMessageResolver
                            .getLocalizedMessage("register.form.validation.uniqueX.sharedColab",
                                    new String[]{
                                            ConfigurationAttributeKey.PARTNER_COLAB_NAME.get()}));
                }
                context.disableDefaultConstraintViolation();
                processDefaultErrorMessage(message.toString(), false, context);
            }
        }

        return isValid;
    }
}
