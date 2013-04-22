package org.xcolab.portlets.contactform;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.xcolab.utils.PropertiesUtils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;

public class ContactBean {
	private String name;
	private String email;
	private String message;
	private int requestCount;
	private boolean expanded;
	private int tryNumber;
	private final String captchaScriptURL;
	private final String captchaNoScriptURL;
	private final String captchaPublicKey;
	private final String fromAddress;

	private ContactPreferences contactPreferences;
	private String captchaText;
	private String captchaChallenge;

	public ContactBean() {
		captchaScriptURL = PropertiesUtils
				.get("captcha.engine.recaptcha.url.script");
		captchaNoScriptURL = PropertiesUtils
				.get("captcha.engine.recaptcha.url.noscript");
		captchaPublicKey = PropertiesUtils
				.get("captcha.engine.recaptcha.key.public");
		fromAddress = PropertiesUtils.get("contact.form.from.email");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCaptchaText(String captchaText) {
		this.captchaText = captchaText;
	}

	public String getCaptchaText() {
		return captchaText;
	}

	public void sendMessage(ActionEvent e) throws AddressException,
			SystemException, PortalException, MailEngineException {
		String messageSubject = applyFilters(contactPreferences
				.getMessageSubject());
		String messageBody = applyFilters(contactPreferences.getMessageFormat());

		InternetAddress addressFrom = new InternetAddress(fromAddress);

		String[] receipients = contactPreferences.getReceipientsArray();
		InternetAddress[] addressTo = new InternetAddress[receipients.length];
		for (int i = 0; i < receipients.length; i++) {
			addressTo[i] = new InternetAddress(receipients[i]);
		}

		InternetAddress replyTo[] = { new InternetAddress(email) };

		MailEngine.send(addressFrom, addressTo, null, null, null,
				messageSubject, messageBody, false, replyTo, null, null);
		toggleExpanded(e);

		FacesMessage fm = new FacesMessage();
		fm.setSummary("Message has been sent.");
		fm.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}

	private String applyFilters(String msg) {
		msg = msg.replaceAll("USER_NAME", name);
		msg = msg.replaceAll("USER_EMAIL", email);
		msg = msg.replaceAll("USER_MESSAGE", message);
		return msg;
	}

	public int getRequestCount() {
		return requestCount++;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void toggleExpanded(ActionEvent e) {
		expanded = !expanded;

		captchaText = "";
		captchaChallenge = "";
		name = "";
		message = "";
		email = "";
	}

	public String getExpandFormMessage() {
		return contactPreferences.getExpandLinkText();
	}

	public void setContactPreferences(ContactPreferences contactPreferences) {
		this.contactPreferences = contactPreferences;
	}

	public String getCaptchaScriptURL() {
		return captchaScriptURL;
	}

	public String getCaptchaNoScriptURL() {
		return captchaNoScriptURL;
	}

	public String getCaptchaPublicKey() {
		return captchaPublicKey;
	}

	public void setCaptchaChallenge(String captchaChallenge) {
		this.captchaChallenge = captchaChallenge;
	}

	public String getCaptchaChallenge() {
		return captchaChallenge;
	}

	public int getTryNumber() {
		return tryNumber++;
	}

}
