package org.xcolab.portlets.loginregister.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.xcolab.utils.validation.ConstraintValidatorHelper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;

public class UniqueScreenNameEmailValidator implements ConstraintValidator<UniqueScreenNameAndEmail, Object> {
    private String screenNameProperty;
    private String emailProperty;
    
    private long DEFAULT_COMPANY_ID = 10112L;

    @Override
    public void initialize(UniqueScreenNameAndEmail constraintAnnotation) {
        screenNameProperty = constraintAnnotation.screenNameProperty();
        emailProperty = constraintAnnotation.emailProperty();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean isValid = true;
        boolean uniqueEmail = true, uniqueScreenName = true;
        
        String screenName = ConstraintValidatorHelper.getPropertyValue(String.class, screenNameProperty, value);
        String email = ConstraintValidatorHelper.getPropertyValue(String.class, emailProperty, value);
        
        if (email == null || screenName == null) {
            // ignore in case of null
            return true;
        }
        try {
            UserLocalServiceUtil.getUserByScreenName(DEFAULT_COMPANY_ID, screenName);
            isValid = false;
            uniqueScreenName = false;
        } catch (PortalException | SystemException e) {
            // user not found .. it's ok
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
                if (! uniqueScreenName) {
                    if (! uniqueEmail) {
                        sb.append(" and");
                    }
                    sb.append(" screen name");
                    
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
