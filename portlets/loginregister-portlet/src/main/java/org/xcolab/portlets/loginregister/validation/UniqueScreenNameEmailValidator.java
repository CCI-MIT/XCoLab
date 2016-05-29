package org.xcolab.portlets.loginregister.validation;

import org.xcolab.client.members.MembersClient;
import org.xcolab.utils.validation.ConstraintValidatorHelper;

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

        String screenName = ConstraintValidatorHelper.getPropertyValue(String.class, screenNameProperty, value);
        String email = ConstraintValidatorHelper.getPropertyValue(String.class, emailProperty, value);

        if (email == null || screenName == null) {
            return true;
        }
        boolean uniqueScreenName = !MembersClient.isScreenNameUsed(screenName);
        boolean uniqueEmail = !MembersClient.isEmailUsed(email);
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
                sb.append(" already exists");
                context.disableDefaultConstraintViolation();
                ConstraintViolationBuilder violationBuilder =
                        context.buildConstraintViolationWithTemplate(sb.toString());
                violationBuilder.addConstraintViolation();
            }
        }

        return isValid;

    }
}
