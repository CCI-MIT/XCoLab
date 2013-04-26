package org.xcolab.portlets.userprofile.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;
import org.xcolab.portlets.userprofile.UserWrapper;
import org.xcolab.portlets.userprofile.utils.PwdEncryptor;

import com.liferay.portal.PwdEncryptorException;
import com.liferay.portal.model.User;

public class UserPasswordValidator implements Validator {

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
        
        String type = arg1.getAttributes().get("type").toString();
        
        UIInput currentPasswordInput = ((UIInput) arg1.getParent().findComponent("currentPasswordInput"));
        String currentPassword = currentPasswordInput.getValue() != null ? currentPasswordInput.getValue().toString() : null;
        
        UIInput newPasswordInput = ((UIInput) arg1.getParent().findComponent("newPasswordInput"));
        String newPassword = newPasswordInput.getValue() != null ? newPasswordInput.getValue().toString() : null;
        
        UIInput newPasswordRetypeInput = ((UIInput) arg1.getParent().findComponent("newPasswordRetypeInput"));
        String newPasswordRetype = newPasswordRetypeInput.getValue() != null ? newPasswordRetypeInput.getValue().toString() : null;
        
        User currentUser = ((UserWrapper) arg1.getAttributes().get("currentUser")).getWrapped();
        
        FacesMessage errorMsg = new FacesMessage();
        errorMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
        boolean isError = false;
        
        if (type.equals("current") && ! StringUtils.isBlank(value.toString())) {
            try {
                if (! PwdEncryptor.encrypt(value.toString()).equals(currentUser.getPassword())) {
                    isError = true;
                    errorMsg.setDetail("Invalid password");
                    errorMsg.setSummary("Invalid password");
                }
            } catch (PwdEncryptorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                isError = true;
                errorMsg.setDetail("Invalid password");
                errorMsg.setSummary("Invalid password");
            }
        }
        else if (type.equals("new")) {
            if (! StringUtils.isBlank(value.toString()) && value.toString().trim().length() < 6) {
                errorMsg.setDetail("Password needs to have at least 6 characters");
                errorMsg.setSummary("Password needs to have at least 6 characters");
                isError = true;
            }
        }
        else if (type.equals("newRetype")) {
            if (! StringUtils.isBlank(value.toString())) {
                if (! value.equals(newPassword)) {
                    errorMsg.setDetail("Passwords don't match");
                    errorMsg.setSummary("Passwords don't match");
                    isError = true;
                }
                if (value.toString().trim().length() > 0 && (currentPassword != null && currentPassword.length() <= 0)) {
                    errorMsg.setDetail("You need to provide current password");
                    errorMsg.setSummary("You need to provide current password");
                    isError = true;
                }
            }
        }
            
        
        if (isError) {
            throw new ValidatorException(errorMsg);
        }
    }

}
