package org.xcolab.view.pages.redballon.web.beans;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;

public class UserEmailBean {
	
	@AssertTrue(message = "Please read and accept the consent form.")
	private boolean acceptTos;

	@NotBlank(message = "Please enter your email address.")
	@Email(message = "Must be a valid email address.")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAcceptTos() {
		return acceptTos;
	}

	public void setAcceptTos(boolean acceptTos) {
		this.acceptTos = acceptTos;
	}

}
