package org.xcolab.view.util.validation;

import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.view.i18n.ResourceMessageResolver;

import javax.validation.ConstraintValidatorContext;

public class UniqueScreenNameValidator extends CustomValidator<UniqueScreenName> {

    private String screenNameProperty;

    private final ResourceMessageResolver resourceMessageResolver;

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
                StringBuilder message = new StringBuilder();
                message.append(resourceMessageResolver
                        .getLocalizedMessage("register.form.validation.uniqueScreenName.message"));

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
