package org.xcolab.portlets.userprofile.beans;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MessagingUserPreferences;
import org.xcolab.portlets.userprofile.validators.UniqueEmail;
import org.xcolab.utils.CountryUtil;
import org.xcolab.utils.validation.CompareStrings;
import org.xcolab.utils.validation.ValidBioLength;

import java.io.Serializable;

@CompareStrings(propertyNames = {"password,retypePassword", "email,retypeEmail"}, groups = {
        UserBean.PasswordChanged.class, UserBean.EmailChanged.class})
@UniqueEmail(emailProperty = "email", groups = {UserBean.EmailChanged.class})
@ValidBioLength(bioProperty = "shortBio")
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String EMAIL_REGEX =
            "(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final long DEFAULT_COMPANY_ID = 10112L;

    private String screenName;

    @NotBlank(groups = {UserBean.EmailChanged.class})
    @Email(regexp = EMAIL_REGEX, groups = {UserBean.EmailChanged.class})
    private String emailStored;

    @NotBlank(groups = {UserBean.EmailChanged.class})
    @Email(regexp = EMAIL_REGEX, groups = {UserBean.EmailChanged.class})
    private String email;

    @NotBlank(groups = {UserBean.EmailChanged.class})
    @Email(regexp = EMAIL_REGEX, groups = {UserBean.EmailChanged.class})
    private String retypeEmail;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank(groups = {UserBean.PasswordChanged.class})
    @Length(min = 8, max = 24, groups = {UserBean.PasswordChanged.class})
    private String currentPassword;

    @NotBlank(groups = {UserBean.PasswordChanged.class})
    @Length(min = 8, max = 24, groups = {UserBean.PasswordChanged.class})
    private String password;

    @NotBlank(groups = {UserBean.PasswordChanged.class})
    @Length(min = 8, max = 24, groups = {UserBean.PasswordChanged.class})
    private String retypePassword;

    private String shortBio;

    @NotBlank(message = "please select your country from the list")
    private String countryCode;

    private long userId;
    private long imageId;
    private boolean sendEmailOnMessage;
    private boolean sendEmailOnActivity;
    private boolean sendDailyEmailOnActivity;

    public UserBean() { }

    public UserBean(Member member) {
        userId = member.getId_();
        screenName = member.getScreenName();
        firstName = member.getFirstName();
        lastName = member.getLastName();
        emailStored = member.getEmailAddress();
        countryCode = member.getCountry();
        shortBio = member.getShortBio();

        imageId = member.getPortraitFileEntryId();

        final MessagingUserPreferences messagingPreferences = MessagingClient
                .getMessagingPreferencesForMember(member.getId_());
        sendEmailOnMessage = messagingPreferences.getEmailOnReceipt();
        sendEmailOnActivity = messagingPreferences.getEmailOnActivity();
        sendDailyEmailOnActivity = messagingPreferences
                .getEmailActivityDailyDigest();
    }

    public String getPortrait() {
        return getPortraitString();
    }

    private String getPortraitString() {
        return "/user_male_portrait?img_id=" + this.imageId;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getCountryName() {
        //return CountryUtil.getCountryForCode(countryCode);
        return countryCode;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
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

    public String getEmailStored() {
        return emailStored;
    }

    public String setEmailStored(String emailStored) {
        return this.emailStored = emailStored;
    }

    public String getRetypeEmail() {
        return retypeEmail;
    }

    public void setRetypeEmail(String retypeEmail) {
        this.retypeEmail = retypeEmail;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
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

    public boolean getSendEmailOnMessage() {
        return sendEmailOnMessage;
    }

    public void setSendEmailOnMessage(boolean send) {
        sendEmailOnMessage = send;
    }

    public boolean getSendEmailOnActivity() {
        return sendEmailOnActivity;
    }

    public void setSendEmailOnActivity(boolean send) {
        sendEmailOnActivity = send;
    }

    public boolean getSendDailyEmailOnActivity() {
        return sendDailyEmailOnActivity;
    }

    public void setSendDailyEmailOnActivity(boolean send) {
        sendDailyEmailOnActivity = send;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserBean [screenName=" + screenName + ", email=" + email
                + ", retypeEmail=" + retypeEmail + ", firstName=" + firstName + ", lastName=" + lastName
                + ", password=" + password + ", retypePassword="
                + retypePassword + "]";
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    public interface PasswordChanged {

    }

    public interface EmailChanged {

    }
}

