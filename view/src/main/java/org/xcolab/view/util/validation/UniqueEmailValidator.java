package org.xcolab.view.util.validation;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.sharedcolab.SharedColabClient;

import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator extends CustomValidator<UniqueEmail> {
    private String emailProperty;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        emailProperty = constraintAnnotation.emailProperty();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String email = ConstraintValidatorHelper.getPropertyValue(String.class, emailProperty, value);

        if (email == null) {
            return true;
        }


        boolean isValid = !SharedColabClient.isEmailUsed(email);

        if(!isValid) {
            context.disableDefaultConstraintViolation();

            processDefaultErrorMessage("User with given email already exists", isValid, context);
        }

        return isValid;
    }
}
