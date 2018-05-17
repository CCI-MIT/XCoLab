package org.xcolab.view.util.validation;

import org.jooq.tools.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.view.i18n.ResourceMessageResolver;

import javax.validation.ConstraintValidatorContext;

/**
 * Validates that the given screen name is unique.
 *
 * An empty or null value is considered valid.
 */
public class UniqueScreenNameValidator extends CustomValidator<UniqueScreenName> {

    private final ResourceMessageResolver resourceMessageResolver;

    @Autowired
    public UniqueScreenNameValidator(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void initialize(UniqueScreenName constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String screenName = (String) value;

        if (StringUtils.isEmpty(screenName)) {
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
