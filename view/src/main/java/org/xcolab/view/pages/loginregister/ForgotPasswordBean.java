package org.xcolab.view.pages.loginregister;

import org.hibernate.validator.constraints.NotBlank;

import org.xcolab.view.util.validation.CompareStrings;
import org.xcolab.view.util.validation.PasswordLength;

@CompareStrings(propertyNames = {"password", "retypePassword"},
        message = "{register.form.validation.passwordequal}")
public class ForgotPasswordBean {

    @NotBlank
    @PasswordLength
    private String password;

    @NotBlank
    private String retypePassword;

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
}
