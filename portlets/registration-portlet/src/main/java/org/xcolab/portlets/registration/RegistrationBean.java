package org.xcolab.portlets.registration;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.xcolab.utils.PropertiesUtils;

import com.icesoft.faces.component.inputfile.InputFile;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.service.persistence.PortletUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

/**
 * <pre>
 * ISSUES:
 * - image upload wont display img
 * - password gets deleted if next element selected 
 * - validator for REQUIRED fields
 * - integrate CAPTCHA
 * - perform registration
 * </pre>
 * 
 * @author pdeboer
 * 
 */
public class RegistrationBean {
	private UserWrapper currentUser;
	private final static Log _log = LogFactoryUtil
			.getLog(RegistrationBean.class);
	private String about;
	private String realName;
	private String firstName;
	private String lastName;
	private String email;
	private String screenName;
	private File newUserProfile;
	private String filteredAbout;
	private String fileErrorMessage;
	private String password;
	private String retypePassword;
	private String country;

	private final String captchaScriptURL;
	private final String captchaNoScriptURL;
	private final String captchaPublicKey;
	private String captchaChallenge;
	private String captchaText;

	private ServiceContext serviceContext;
	private PortletRequest req;
	private PortletResponse resp;

	private Boolean registrationSuccessful = false;

	private long DEFAULT_COMPANY_ID = 10112L;

	public RegistrationBean() throws Exception {
		captchaScriptURL = PropertiesUtils
				.get("captcha.engine.recaptcha.url.script");
		captchaNoScriptURL = PropertiesUtils
				.get("captcha.engine.recaptcha.url.noscript");
		captchaPublicKey = PropertiesUtils
				.get("captcha.engine.recaptcha.key.public");

		serviceContext = ServiceContextFactory.getInstance(
				User.class.getName(), Helper.getPortletRequest());

		req = Helper.getPortletRequest();
		resp = Helper.getPortletResponse();
		
		System.out.println(PortalUtil.getHttpServletRequest(req));
	}

	public void signalPictureUploaded(ActionEvent e) {
		// do nothing, this method will be called thanks to javascript and will
		// cause parent page to detect a file upload in an iframe
	}

	public void register(javax.faces.event.ActionEvent e) throws Exception {
		ThemeDisplay themeDisplay = Helper.getThemeDisplay();

		PermissionChecker permissionChecker = PermissionCheckerFactoryUtil
				.create(themeDisplay.getUser(), true);

		PermissionThreadLocal.setPermissionChecker(permissionChecker);

		User user = UserServiceUtil.addUserWithWorkflow(DEFAULT_COMPANY_ID,
				false, password, retypePassword, false, screenName, email, 0L,
				"", themeDisplay.getLocale(), firstName, "", lastName, 0, 0,
				true, 1, 1, 1970, "", new long[] {}, new long[] {},
				new long[] {}, new long[] {}, false, serviceContext);

		LoginUtil.logUserIn(req, resp, screenName, password);

		registrationSuccessful = true;
	}

	public void uploadPortrait(ActionEvent e) throws IOException {
		InputFile inputFile = (InputFile) e.getSource();
		if (inputFile.getStatus() == InputFile.INVALID) {
			fileErrorMessage = "Provided file isn't a valid image.";
			_log.error("There was an error when uploading file", inputFile
					.getFileInfo().getException());
			return;
		}

		if (!inputFile.getFileInfo().getContentType().startsWith("image")) {
			fileErrorMessage = "Provided file isn't a valid image.";
			_log.error("There was an error when uploading file", inputFile
					.getFileInfo().getException());
			return;
		}
		fileErrorMessage = null;
		resizeAndCropImage(inputFile.getFile());
		newUserProfile = inputFile.getFile();
	}

	public String getUploadedFileName() throws UnsupportedEncodingException {
		if (newUserProfile != null) {
			return URLEncoder.encode(newUserProfile.getName(), "UTF-8");
		}
		return null;
	}

	private void resizeAndCropImage(File file) throws IOException {
		int newW = 150;
		int newH = 150;

		BufferedImage img = ImageIO.read(file);
		// crop image

		int w = img.getWidth();
		int h = img.getHeight();

		int cropSize = 0;
		int cropX = 0;
		int cropY = 0;

		if (h < w) {
			cropSize = h;
			cropX = (w - h) / 2;
		} else {
			cropSize = w;
			cropY = (h - w) / 2;
		}

		BufferedImage cropedImage = img.getSubimage(cropX, cropY, cropSize,
				cropSize);

		int imgType = BufferedImage.TYPE_3BYTE_BGR;
		if (img.getType() != BufferedImage.TYPE_CUSTOM) {
			imgType = img.getType();
		}

		BufferedImage dimg = new BufferedImage(newW, newH, imgType);
		Graphics2D g = dimg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(cropedImage, 0, 0, newW, newH, 0, 0, cropSize, cropSize,
				null);
		g.dispose();

		String imageType = file.getName();
		imageType = imageType.substring(imageType.lastIndexOf(".") + 1);

		ImageIO.write(dimg, imageType, file);

	}

	/**
	 * @return the about
	 */
	public String getAbout() {
		return about;
	}

	/**
	 * @param about
	 *            the about to set
	 */
	public void setAbout(String about) {
		this.about = about;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @return the captchaChallenge
	 */
	public String getCaptchaChallenge() {
		return captchaChallenge;
	}

	/**
	 * @param captchaChallenge
	 *            the captchaChallenge to set
	 */
	public void setCaptchaChallenge(String captchaChallenge) {
		this.captchaChallenge = captchaChallenge;
	}

	/**
	 * @param realName
	 *            the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * @param screenName
	 *            the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * @return the newUserProfile
	 */
	public File getNewUserProfile() {
		return newUserProfile;
	}

	/**
	 * @param newUserProfile
	 *            the newUserProfile to set
	 */
	public void setNewUserProfile(File newUserProfile) {
		this.newUserProfile = newUserProfile;
	}

	/**
	 * @return the filteredAbout
	 */
	public String getFilteredAbout() {
		return filteredAbout;
	}

	/**
	 * @param filteredAbout
	 *            the filteredAbout to set
	 */
	public void setFilteredAbout(String filteredAbout) {
		this.filteredAbout = filteredAbout;
	}

	/**
	 * @return the fileErrorMessage
	 */
	public String getFileErrorMessage() {
		return fileErrorMessage;
	}

	/**
	 * @param fileErrorMessage
	 *            the fileErrorMessage to set
	 */
	public void setFileErrorMessage(String fileErrorMessage) {
		this.fileErrorMessage = fileErrorMessage;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the retypePassword
	 */
	public String getRetypePassword() {
		return retypePassword;
	}

	/**
	 * @param retypePassword
	 *            the retypePassword to set
	 */
	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

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
	 * @param currentUser
	 *            the currentUser to set
	 */
	public void setCurrentUser(UserWrapper currentUser) {
		this.currentUser = currentUser;
	}

	public UserWrapper getCurrentUser() {
		return currentUser;
	}

	/**
	 * @return the captchaScriptURL
	 */
	public String getCaptchaScriptURL() {
		return captchaScriptURL;
	}

	/**
	 * @return the captchaNoScriptURL
	 */
	public String getCaptchaNoScriptURL() {
		return captchaNoScriptURL;
	}

	/**
	 * @return the captchaPublicKey
	 */
	public String getCaptchaPublicKey() {
		return captchaPublicKey;
	}

	/**
	 * @return the captchaText
	 */
	public String getCaptchaText() {
		return captchaText;
	}

	/**
	 * @param captchaText
	 *            the captchaText to set
	 */
	public void setCaptchaText(String captchaText) {
		this.captchaText = captchaText;
	}

	/**
	 * @return the registrationSuccessful
	 */
	public Boolean getRegistrationSuccessful() {
		return registrationSuccessful;
	}

	/**
	 * @param registrationSuccessful
	 *            the registrationSuccessful to set
	 */
	public void setRegistrationSuccessful(Boolean registrationSuccessful) {
		this.registrationSuccessful = registrationSuccessful;
	}
}
