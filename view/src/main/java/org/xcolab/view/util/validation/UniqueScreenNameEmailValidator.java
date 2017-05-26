package org.xcolab.view.util.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.view.i18n.ResourceMessageResolver;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

public class UniqueScreenNameEmailValidator
        implements ConstraintValidator<UniqueScreenNameAndEmail, Object> {

    private String screenNameProperty;
    private String emailProperty;

    @Autowired
    ResourceMessageResolver resourceMessageResolver;

    @Override
    public void initialize(UniqueScreenNameAndEmail constraintAnnotation) {
        screenNameProperty = constraintAnnotation.screenNameProperty();
        emailProperty = constraintAnnotation.emailProperty();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {


        String screenName =
                ConstraintValidatorHelper.getPropertyValue(String.class, screenNameProperty, value);
        String email =
                ConstraintValidatorHelper.getPropertyValue(String.class, emailProperty, value);

        if (email == null || screenName == null) {
            return true;
        }
        boolean uniqueScreenName = !SharedColabClient.isScreenNameUsed(screenName);
        boolean uniqueEmail = !SharedColabClient.isEmailUsed(email);
        boolean isValid = uniqueEmail && uniqueScreenName;

        if (!isValid) {
            boolean isDefaultMessage = "".equals(context.getDefaultConstraintMessageTemplate());
            /* if custom message was provided, don't touch it, otherwise build the default
            message */
            if (isDefaultMessage) {
                StringBuilder sb = new StringBuilder();
                sb.append(resourceMessageResolver.getLocalizedMessage(
                        "register.form.validation.screenNameEmail.memberwith"));
                if (!uniqueEmail) {
                    sb.append(" "+resourceMessageResolver
                            .getLocalizedMessage("register.form.validation.screenNameEmail.email"));
                }
                if (!uniqueScreenName) {
                    if (!uniqueEmail) {
                        sb.append(" "+resourceMessageResolver.getLocalizedMessage(
                                "register.form.validation.screenNameEmail.and"));
                    }
                    sb.append(" "+resourceMessageResolver.getLocalizedMessage(
                            "register.form.validation.screenNameEmail.screenName"));

                }
                if (!ConfigurationAttributeKey.IS_SHARED_COLAB.get()) {
                    sb.append(" "+resourceMessageResolver
                            .getLocalizedMessage("register.form.validation.screenNameEmail.alreadyexists"));
                } else {
                    sb.append(" "+(resourceMessageResolver
                            .getLocalizedMessage(
                                    "register.form.validation.screenNameEmail.alreadyexists.sharedcolab1")+" "
                            + ConfigurationAttributeKey.PARTNER_COLAB_NAME.get()
                            +" "+resourceMessageResolver
                                    .getLocalizedMessage(
                                            "register.form.validation.screenNameEmail.alreadyexists.sharedcolab2")
                            ));
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
