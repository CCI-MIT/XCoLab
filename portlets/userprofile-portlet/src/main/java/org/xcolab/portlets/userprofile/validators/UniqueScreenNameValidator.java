package org.xcolab.portlets.userprofile.validators;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.utils.validation.ConstraintValidatorHelper;

import javax.validation.ConstraintValidatorContext;

public class UniqueScreenNameValidator extends CustomValidator<UniqueScreenName> {
    private String screenNameProperty;

    @Override
    public void initialize(UniqueScreenName constraintAnnotation) {
        screenNameProperty = constraintAnnotation.screenNameProperty();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String screenName = ConstraintValidatorHelper.getPropertyValue(String.class, screenNameProperty, value);

        if (screenName == null) {
            // ignore in case of null
            return true;
        }
        boolean isValid = true;
        try {
            UserLocalServiceUtil.getUserByScreenName(DEFAULT_COMPANY_ID, screenName);
            isValid = false;
        } catch (PortalException | SystemException e) {
            // user not found .. it's ok
        }

        processDefaultErrorMessage("User with screen name already exists", isValid, context);
        
        return isValid;
    }
}
