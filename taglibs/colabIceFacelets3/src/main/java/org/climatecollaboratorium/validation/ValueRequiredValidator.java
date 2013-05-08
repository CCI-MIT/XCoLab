package org.climatecollaboratorium.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

public class ValueRequiredValidator {
    
    public static boolean validateComponent(UIInput input) {
        Object value = input.getValue();
        boolean isValid = true;
        if (value == null) {
            isValid = false;
        }
        else if (value.toString().trim().length() == 0) {
            isValid = false;
        }
        
        if (! isValid) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value is required", null);
            FacesContext.getCurrentInstance().addMessage(input.getClientId(FacesContext.getCurrentInstance()), fm);
        }
        return isValid;
    }

}
