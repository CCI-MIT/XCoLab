package org.xcolab.portlets.userprofile.validators;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.utils.validation.ConstraintValidatorHelper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

public class UniqueScreenNameValidator implements ConstraintValidator<UniqueScreenName, Object> {
    private String screenNameProperty;

    private long DEFAULT_COMPANY_ID = 10112L;

    @Override
    public void initialize(UniqueScreenName constraintAnnotation) {
        screenNameProperty = constraintAnnotation.screenNameProperty();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean isValid = true;
        boolean uniqueScreenName = true;
        
        String screenName = ConstraintValidatorHelper.getPropertyValue(String.class, screenNameProperty, value);

        
        if (screenName == null) {
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

        if(!isValid) {
            boolean isDefaultMessage = "".equals(context.getDefaultConstraintMessageTemplate());
            /* if custom message was provided, don't touch it, otherwise build the default message */
            if(isDefaultMessage) {
                StringBuilder sb = new StringBuilder();
                sb.append("User with given");

                if (! uniqueScreenName) {
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
