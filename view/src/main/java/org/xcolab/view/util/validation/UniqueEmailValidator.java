package org.xcolab.view.util.validation;

import org.jooq.tools.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.client.user.IUserClient;
import org.xcolab.view.i18n.ResourceMessageResolver;

import javax.validation.ConstraintValidatorContext;

/**
 * Validates that the given email address is unique.
 *
 * An empty or null value is considered valid.
 */
public class UniqueEmailValidator extends CustomValidator<UniqueEmail> {

    private final ResourceMessageResolver resourceMessageResolver;
    private final IUserClient userClient;

    @Autowired
    public UniqueEmailValidator(ResourceMessageResolver resourceMessageResolver, IUserClient userClient) {
        this.resourceMessageResolver = resourceMessageResolver;
        this.userClient = userClient;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String email = (String) value;

        if (StringUtils.isEmpty(email)) {
            return true;
        }

        boolean isValid = !userClient.isEmailUsed(email);

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
