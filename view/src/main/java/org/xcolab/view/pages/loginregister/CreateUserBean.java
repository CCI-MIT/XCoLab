package org.xcolab.view.pages.loginregister;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import org.xcolab.view.util.validation.CompareStrings;
import org.xcolab.view.util.validation.HtmlMaxLength;
import org.xcolab.view.util.validation.PasswordLength;
import org.xcolab.view.util.validation.UniqueEmail;
import org.xcolab.view.util.validation.UniqueScreenName;
import org.xcolab.view.util.validation.ValidScreenName;

import java.io.Serializable;

@CompareStrings(propertyNames = {"password,retypePassword", "email,retypeEmail"})
public class CreateUserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Length(min = 3, max = 26)
    @ValidScreenName
    @UniqueScreenName
    private String screenName;

    @NotBlank
    @Email
    @UniqueEmail
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @PasswordLength
    private String password;

    @NotBlank
    private String retypePassword;

    @HtmlMaxLength(2000)
    private String shortBio;

    @NotBlank
    private String country;

    @NotBlank
    private String language;

    private String recaptcha_response_field;

    private String imageId;

    private String captchaText;

    private boolean isCaptchaNeeded = true;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
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
