package org.xcolab.portlets.registration.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class UserPasswordValidator implements Validator {

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
        
        String type = arg1.getAttributes().get("type").toString();

        UIInput newPasswordInput = ((UIInput) arg1.getParent().findComponent("password"));
        String newPassword = newPasswordInput.getValue() != null ? newPasswordInput.getValue().toString() : null;
        
        FacesMessage errorMsg = new FacesMessage();
        errorMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
        boolean isError = false;
        
        if (type.equals("new")) {
            if ( value.toString().trim().length() < 6) {
                errorMsg.setDetail("Password needs to have at least 6 characters");
                errorMsg.setSummary("Password needs to have at least 6 characters");
                isError = true;
            }
        }
        else if (type.equals("newRetype")) {
                if (! value.equals(newPassword)) {
                    errorMsg.setDetail("Passwords don't match");
                    errorMsg.setSummary("Passwords don't match");
                    isError = true;
                }
            }
            
        
        if (isError) {
            throw new ValidatorException(errorMsg);
        }
    }

}
