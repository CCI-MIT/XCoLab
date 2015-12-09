package org.xcolab.portlets.userprofile.validators;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.utils.validation.ConstraintValidatorHelper;

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
            // ignore in case of null
            return true;
        }

        boolean isValid = true;
        try {
            UserLocalServiceUtil.getUserByEmailAddress(DEFAULT_COMPANY_ID, email);
            isValid = false;
        } catch (PortalException | SystemException e) {
            // user not found .. it's ok
        }

        processDefaultErrorMessage("User with given email already exists", isValid, context);
        
        return isValid;
    }
}
