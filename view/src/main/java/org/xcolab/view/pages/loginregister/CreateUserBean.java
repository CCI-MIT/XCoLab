package org.xcolab.view.pages.loginregister;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import org.xcolab.view.util.validation.CompareStrings;
import org.xcolab.view.util.validation.UniqueEmail;
import org.xcolab.view.util.validation.UniqueScreenName;
import org.xcolab.view.util.validation.ValidBioLength;
import org.xcolab.view.util.validation.ValidScreenName;

import java.io.Serializable;

@CompareStrings(propertyNames = {"password,retypePassword", "email,retypeEmail"})
@UniqueEmail(emailProperty = "email")
@UniqueScreenName(screenNameProperty = "screenName")
@ValidScreenName(screenNameProperty = "screenName")
@ValidBioLength(bioProperty = "shortBio")
public class CreateUserBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String EMAIL_REGEX =
            "(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @NotBlank
    @Length(min = 3, max = 26)
    private String screenName;

    @NotBlank
    @Email(regexp = EMAIL_REGEX)
    private String email;

    @NotBlank
    @Email(regexp = EMAIL_REGEX)
    private String retypeEmail;

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

    private String shortBio;

    @NotBlank
    @Length(max = 300)
    private String country;


    @NotBlank(message = "register.form.validation.language")
    @Length(max = 300)
    private String language;

    private String recaptcha_response_field;

    private Long imageId;

    private String captchaText;

    private boolean isCaptchaNeeded = true;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShortBio() {
        return shortBio;
    }

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

    public String getRetypeEmail() {
        return retypeEmail;
    }

    public void setRetypeEmail(String retypeEmail) {
        this.retypeEmail = retypeEmail;
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
                + ", retypeEmail=" + retypeEmail + ", firstName=" + firstName + ", lastName=" + lastName
                + ", password=" + password + ", retypePassword="
                + retypePassword + ", recaptcha_response_field="
                + recaptcha_response_field + "]";
    }

    public String getCaptchaText() {
        return captchaText;
    }

    public void setCaptchaText(String captchaText) {
        this.captchaText = captchaText;
    }

    public boolean getCaptchaNeeded() {
        return isCaptchaNeeded;
    }

    public void setCaptchaNeeded(boolean captchaNeeded) {
        isCaptchaNeeded = captchaNeeded;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
