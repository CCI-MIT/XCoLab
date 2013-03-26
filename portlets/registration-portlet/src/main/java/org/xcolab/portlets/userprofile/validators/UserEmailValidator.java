package org.xcolab.portlets.userprofile.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.xcolab.portlets.userprofile.Helper;
import org.xcolab.portlets.userprofile.UserWrapper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class UserEmailValidator implements Validator {

    private String EMAILVALIDATION_REGEXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    @Override
    public void validate(FacesContext ctx, UIComponent comp, Object obj) throws ValidatorException {
        String email = obj.toString().trim();
        UserWrapper currentUser = (UserWrapper) comp.getAttributes().get("currentUser");
        
        if (email.equals(currentUser.getEmail())) {
            // screen name hasn't changed don't do anything
            return;
        }
        
        if (email.equals("")) {
            FacesMessage fm = new FacesMessage();
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            throwError("Email can't be empty.");
            throw new ValidatorException(fm);   
        }
        
        if (!email.matches(EMAILVALIDATION_REGEXP)) {
            throwError("Email is invalid.");
        }
         
        try {
            
            User user = UserLocalServiceUtil.getUserByEmailAddress(Helper.getThemeDisplay().getCompanyId(), email);
            if (user != null) {
                // user with given screen name has been found this is forbidden
                throwError("Email is already used by other user.");
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