package org.xcolab.portlets.loginregister;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.xcolab.portlets.loginregister.validation.UniqueScreenNameAndEmail;
import org.xcolab.utils.validation.CompareStrings;

@CompareStrings(propertyNames = { "password", "retypePassword" })
@UniqueScreenNameAndEmail(emailProperty = "email", screenNameProperty = "screenName")
public class CreateUserBean {

	@NotBlank
	private String screenName;

	@NotBlank
	@Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	private String email;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	@Length(min = 8, max = 24)
	private String password;

	@NotBlank
	@Length(min = 8, max = 24)
	private String retypePassword;

	@Length(min = 0, max = 1000)
	private String shortBio;

	@Length(min = 0, max = 300)
	private String country;

	private String recaptcha_response_field;

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the shortBio
	 */
	public String getShortBio() {
		return shortBio;
	}

	/**
	 * @param shortBio
	 *            the shortBio to set
	 */
	public void setShortBio(String shortBio) {
		this.shortBio = shortBio;
	}

	public String getRecaptcha_response_field() {
		return recaptcha_response_field;
	}

	public void setRecaptcha_response_field(String recaptcha_response_field) {
		this.recaptcha_response_field = recaptcha_response_field;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "CreateUserBean [screenName=" + screenName + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", retypePassword="
				+ retypePassword + ", recaptcha_response_field="
				+ recaptcha_response_field + "]";
	}
}
