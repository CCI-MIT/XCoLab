package org.xcolab.portlets.userprofile.beans;

import com.ext.portlet.community.CommunityConstants;
import com.ext.portlet.messaging.MessageUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.xcolab.portlets.userprofile.utils.Helper;
import org.xcolab.portlets.userprofile.validators.UniqueEmail;
import org.xcolab.portlets.userprofile.validators.UniqueScreenName;
import org.xcolab.utils.validation.CompareStrings;
import org.xcolab.utils.validation.ValidBioLength;
import org.xcolab.utils.validation.ValidScreenName;

import java.io.Serializable;

@CompareStrings(propertyNames = {"password,retypePassword", "email,retypeEmail"}, groups = {UserBean.PasswordChanged.class, UserBean.EmailChanged.class})
@UniqueEmail(emailProperty = "email", groups = {UserBean.EmailChanged.class})
@UniqueScreenName(screenNameProperty = "screenName", groups = {UserBean.ScreenNameChanged.class})
@ValidScreenName(screenNameProperty = "screenName", groups = {UserBean.ScreenNameChanged.class})
@ValidBioLength(bioProperty = "shortBio")
public class UserBean implements Serializable{


	private static final long serialVersionUID = 1L;
    private static final String EMAIL_REGEX ="(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

	@NotBlank(groups = {UserBean.ScreenNameChanged.class})
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

	@Length(min = 0, max = 300)
	private String country;

	private Long userId;
	private Long imageId;
	private boolean isFemale;
	private boolean sendEmailOnMessage;
	private boolean sendEmailOnActivity;
	private boolean sendDailyEmailOnActivity;

	private static final long DEFAULT_COMPANY_ID = 10112L;

	public UserBean(){
	}

	public UserBean(User user) throws PortalException, SystemException {

		userId = user.getUserId();
		screenName = user.getScreenName();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		emailStored = user.getEmailAddress();
		imageId = user.getPortraitId();
		isFemale = user.getFemale();

		country = Helper.getCodeForCounty(ExpandoValueLocalServiceUtil.getData(User.class.getName(),
				CommunityConstants.EXPANDO, CommunityConstants.COUNTRY,
				user.getUserId(), StringPool.BLANK));

		shortBio = ExpandoValueLocalServiceUtil.getData(DEFAULT_COMPANY_ID, User.class.getName(),
				CommunityConstants.EXPANDO, CommunityConstants.BIO,
				user.getUserId(), StringPool.BLANK);

		sendEmailOnMessage = MessageUtil.getMessagingPreferences(user.getUserId()).getEmailOnReceipt();
		sendEmailOnActivity = MessageUtil.getMessagingPreferences(user.getUserId()).getEmailOnActivity();
		sendDailyEmailOnActivity = MessageUtil.getMessagingPreferences(user.getUserId()).getEmailActivityDailyDigest();
		
	}

	private String getPortraitString() {

		try {
			return  "/user_" + (this.isFemale ? "female" : "male") + "_portrait?img_id="
                    + this.imageId;
		} catch (Exception e) {
		}

		return "";
	}

	public String getPortrait() { return getPortraitString(); }

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

	public String getCurrentPassword(){ return currentPassword;}

	public void setCurrentPassword(String currentPassword){this.currentPassword=currentPassword;}

	public String getPassword() { return password; }

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

	public boolean getSendEmailOnMessage() { return sendEmailOnMessage; }

	public void setSendEmailOnMessage(boolean send) { sendEmailOnMessage = send; }
	
	public boolean getSendEmailOnActivity() { return sendEmailOnActivity; }

	public void setSendEmailOnActivity(boolean send) { sendEmailOnActivity = send; }

	public boolean getSendDailyEmailOnActivity() { return sendDailyEmailOnActivity; }

	public void setSendDailyEmailOnActivity(boolean send) { sendDailyEmailOnActivity = send; }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserBean [screenName=" + screenName + ", email=" + email
				+ ", retypeEmail=" + retypeEmail + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", retypePassword="
				+ retypePassword + "]";
	}


	public interface PasswordChanged {

	}

	public interface EmailChanged {

	}

	public interface ScreenNameChanged {

	}
}

