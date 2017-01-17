package org.xcolab.view.util.validation;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.sharedcolab.SharedColabClient;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

public class UniqueScreenNameEmailValidator implements ConstraintValidator<UniqueScreenNameAndEmail, Object> {

    private String screenNameProperty;
    private String emailProperty;

    @Override
    public void initialize(UniqueScreenNameAndEmail constraintAnnotation) {
        screenNameProperty = constraintAnnotation.screenNameProperty();
        emailProperty = constraintAnnotation.emailProperty();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String screenName = ConstraintValidatorHelper
                .getPropertyValue(String.class, screenNameProperty, value);
        String email = ConstraintValidatorHelper.getPropertyValue(String.class, emailProperty, value);

        if (email == null || screenName == null) {
            return true;
        }
        boolean uniqueScreenName = !SharedColabClient.isScreenNameUsed(screenName);
        boolean uniqueEmail = !SharedColabClient.isEmailUsed(email);
        boolean isValid = uniqueEmail && uniqueScreenName;

        if (!isValid) {
            boolean isDefaultMessage = "".equals(context.getDefaultConstraintMessageTemplate());
            /* if custom message was provided, don't touch it, otherwise build the default message */
            if (isDefaultMessage) {
                StringBuilder sb = new StringBuilder();
                sb.append("User with given");
                if (!uniqueEmail) {
                    sb.append(" email");
                }
                if (!uniqueScreenName) {
                    if (!uniqueEmail) {
                        sb.append(" and");
                    }
                    sb.append(" screen name");

                }
                if (!ConfigurationAttributeKey.IS_SHARED_COLAB.get()) {
                    sb.append(" already exists");
                } else {
                    sb.append(" already exists , or is already registered on another colab! Please use the Colab logo above to sign in!");
                }
                context.disableDefaultConstraintViolation();
                ConstraintViolationBuilder violationBuilder =
                        context.buildConstraintViolationWithTemplate(sb.toString());
                violationBuilder.addConstraintViolation();
            }
        }

        return isValid;

    }
}
