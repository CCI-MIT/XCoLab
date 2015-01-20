package org.xcolab.portlets.userprofile.validators;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.utils.validation.ConstraintValidatorHelper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, Object> {
    private String emailProperty;
    
    private long DEFAULT_COMPANY_ID = 10112L;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        emailProperty = constraintAnnotation.emailProperty();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean isValid = true;
        boolean uniqueEmail = true;

        String email = ConstraintValidatorHelper.getPropertyValue(String.class, emailProperty, value);
        
        if (email == null) {
            // ignore in case of null
            return true;
        }

        try {
            UserLocalServiceUtil.getUserByEmailAddress(DEFAULT_COMPANY_ID, email);
            uniqueEmail = false;
            isValid = false;
        } catch (PortalException | SystemException e) {
            // user not found .. it's ok
        }
        
        
        if(!isValid) {
            boolean isDefaultMessage = "".equals(context.getDefaultConstraintMessageTemplate());
            /* if custom message was provided, don't touch it, otherwise build the default message */
            if(isDefaultMessage) {
                StringBuilder sb = new StringBuilder();
                sb.append("User with given");
                if (! uniqueEmail) {
                    sb.append(" email");
                }
                sb.append(" already exists");
                context.disableDefaultConstraintViolation();
                ConstraintViolationBuilder violationBuilder = context.buildConstraintViolationWithTemplate(sb.toString());
                violationBuilder.addConstraintViolation();
            }
        }
        
        return isValid;
    
    }
    

}
