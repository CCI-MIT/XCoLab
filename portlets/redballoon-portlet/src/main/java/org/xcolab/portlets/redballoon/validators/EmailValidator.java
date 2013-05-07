package org.xcolab.portlets.redballoon.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("EmailValidator")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent uIComponent,
			Object object) throws ValidatorException {

		if (!((String) object)
				.matches("\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3}")) {
			String msg = "Please enter you e-Mail address";
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, msg));
		}
	}
}
