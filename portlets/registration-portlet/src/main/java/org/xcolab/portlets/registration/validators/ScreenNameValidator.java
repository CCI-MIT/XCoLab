package org.xcolab.portlets.registration.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.xcolab.portlets.registration.Helper;
import org.xcolab.portlets.registration.RegistrationBean;
import org.xcolab.portlets.registration.UserWrapper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class ScreenNameValidator implements Validator {

    private String SCREEN_NAME_VALIDATION_REGEXP = "[_0-9a-zA-Z_\\-\\.]*";

    @Override
    public void validate(FacesContext ctx, UIComponent comp, Object obj) throws ValidatorException {
        String screenName = obj.toString().trim();
        
        if (screenName.equals("")) {
            FacesMessage fm = new FacesMessage();
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            throwError("Screen name can't be empty.");
            throw new ValidatorException(fm);   
        }
        
        if (!screenName.matches(SCREEN_NAME_VALIDATION_REGEXP)) {
            throwError("Screen name is invalid.");
        }
         
        try {
            
            User user = UserLocalServiceUtil.getUserByScreenName(Helper.getThemeDisplay().getCompanyId(), screenName);
            if (user != null) {
                // user with given screen name has been found this is forbidden
                throwError("Screen name has been already taken.");
            }
        } catch (PortalException e) {
            // user has not been found, this is good
        } catch (SystemException e) {
            // user has not been found, this is good
        }
    }
    
    private void throwError(String message) {
        FacesMessage fm = new FacesMessage();
        fm.setSeverity(FacesMessage.SEVERITY_ERROR);
        fm.setSummary(message);
        throw new ValidatorException(fm);
    }

}