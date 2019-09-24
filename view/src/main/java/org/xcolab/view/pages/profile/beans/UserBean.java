package org.xcolab.view.pages.profile.beans;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.MessagingUserPreferenceWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.CountryUtil;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.util.validation.CompareStrings;
import org.xcolab.view.util.validation.HtmlMaxLength;
import org.xcolab.view.util.validation.PasswordLength;
import org.xcolab.view.util.validation.UniqueEmail;

import java.io.Serializable;

@CompareStrings(propertyNames = {"password","retypePassword"}, groups = {
        UserBean.PasswordChanged.class}, message = "register.form.validation.passwordequal")
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String screenName;

    private String displayName;
    private String displayNameShort;

    @NotBlank(groups = {UserBean.EmailChanged.class})
    @Email(groups = {UserBean.EmailChanged.class})
    private String emailStored;

    @NotBlank(groups = {UserBean.EmailChanged.class})
    @Email(groups = {UserBean.EmailChanged.class})
    @UniqueEmail(groups = {UserBean.EmailChanged.class})
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank(groups = {UserBean.PasswordChanged.class})
    private String currentPassword;

    @NotBlank(groups = {UserBean.PasswordChanged.class})
    @PasswordLength(groups = {UserBean.PasswordChanged.class})
    private String password;

    @NotBlank(groups = {UserBean.PasswordChanged.class})
    private String retypePassword;

    @HtmlMaxLength(2000)
    private String shortBio;

    @NotBlank(message = "A country selection is required. Please selected a country from the list above.")
    private String countryCode;

    private String defaultLocale;

    private long userId;
    private long imageId;
    private boolean sendEmailOnMessage;
    private boolean sendEmailOnActivity;
    private boolean sendDailyEmailOnActivity;

    public UserBean() { }

    public UserBean(UserWrapper member) {
        userId = member.getId();
        screenName = member.getScreenName();
        firstName = member.getFirstName();
        lastName = member.getLastName();
        displayName = member.getDisplayName();
        displayNameShort = StringUtils.isNotBlank(firstName) ? firstName : screenName;
        emailStored = member.getEmailAddress();
        countryCode = member.getCountry();
        shortBio = member.getShortBio();

        if(member.getDefaultLocale()!=null){
            defaultLocale = member.getDefaultLocale();
        }else{
            defaultLocale = I18nUtils.DEFAULT_LOCALE.getLanguage();
        }
        if(member.getPortraitFileEntryId()==null){
            imageId = 0;
        } else {
            imageId = member.getPortraitFileEntryId();
        }
        final MessagingUserPreferenceWrapper messagingPreferences = StaticUserContext.getMessagingClient()
                .getMessagingPreferences(member.getId());
        sendEmailOnMessage = messagingPreferences.getEmailOnReceipt();
        sendEmailOnActivity = messagingPreferences.getEmailOnActivity();
        sendDailyEmailOnActivity = messagingPreferences
                .getEmailActivityDailyDigest();
    }

    public String getPortrait() {
        return getPortraitString();
    }

    public String getPortraitString() {
        final String userImageDomain = PlatformAttributeKey.CDN_URL_IMAGES_UPLOADED.get();
        return userImageDomain + "/image/member/" + this.imageId;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getCountryName() {
        if (countryCode == null || countryCode.equals("CS")) {
            return "Country not set";
        }
        return CountryUtil.getCountryForCode(countryCode);
    }

    public String getDefaultLocale() {
        return defaultLocale;
    }

    public void setDefaultLocale(String defaultLocale) {
        this.defaultLocale = defaultLocale;
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

    public String getDisplayName() {
        return displayName;
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

    public void setEmailStored(String emailStored) {
        this.emailStored = emailStored;
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
                + ", firstName=" + firstName + ", lastName=" + lastName
                + ", password=" + password + ", retypePassword="
                + retypePassword + "]";
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDisplayNameShort() {
        return displayNameShort;
    }


    public interface PasswordChanged {

    }

    public interface EmailChanged {

    }
}

