package org.xcolab.portlets.redballoon.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailListValidator implements Validator {

    @Override
        public void validate(FacesContext facesContext, 
                UIComponent uIComponent, Object object) throws ValidatorException {
        
        String values = (String) object;
        String[] emails = values.split(",");
        for (String email: emails) {
            if (!email.matches("(\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3})")) {
                FacesMessage message = new FacesMessage();
                message.setDetail("Email not valid");
                message.setSummary("Email not valid");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        }
    }
}
