package org.xcolab.portlets.contactform.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {

        
        if (!((String) object).matches("(\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3})")) {
            FacesMessage message = new FacesMessage();
            message.setDetail("Email not valid");
            message.setSummary("Email not valid");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
