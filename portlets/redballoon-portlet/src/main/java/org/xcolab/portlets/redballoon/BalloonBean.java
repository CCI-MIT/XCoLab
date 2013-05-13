package org.xcolab.portlets.redballoon;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.ext.portlet.community.CommunityConstants;
import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;

public class BalloonBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email = "";
	private boolean checkBoxActive = false;
	private boolean redirectUserToGetURL = false;
	private BalloonCookie balloonCookie;
	private HttpServletRequest httpReq;
	private boolean showShareWidgets;
	private List<Contest> contests;

	private final static String EMAIL_SUBJECT = "Help find the winner of MIT's Climate CoLab Grand Prize!";
	private final static String EMAIL_BODY = "<p>Here is your unique link, which you can use to tell your friends and family about the MIT Climate CoLab contest:</p>\n\n<p><a href=\"URL_PLACEHOLDER/e\">URL_PLACEHOLDER</a></p>\n<a href=\"http://www.facebook.com/share.php?u=URL_PLACEHOLDER\" title=\"Facebook\" href=\"#\"><img src=\"http://inventas-it.ch/mit/fb.png\" height=\"30\" border=\"0\" /></a> <a href=\"http://twitter.com/share?url=URL_PLACEHOLDER&text=ClimateColab\"><img src=\"http://inventas-it.ch/mit/twitter.png\" height=\"33\" border=\"0\" /></a>\n\n<p>Remember: If your sharing eventually leads to the winner of the $10,000 Grand Prize, you\'ll win some of the $2,000 referral prize, even if that person is up to 10 degrees of separation from you!</p>\n\n<p>For your convenience, we\'ve included a blurb about the contest below for you to send to your friends, but you can spread the word any way you\'d like (just remember to include your unique link, so you can win the referral prize!)</p>\n\n<br/><br/><p><strong>MIT\'s Climate CoLab: Your ideas can help combat climate change, and you might win $10,000</strong></p>\n\n<p>At MIT\u2019s Climate CoLab you can work with people from all over the world to develop ideas for what we can actually do about climate change.</p>\n \n<p>If you submit one of the winning ideas, you\u2019ll be able to present it to the media, government officials, business executives, and scientists at an MIT conference on November 6-7, where a grand prize of $10,000 will be awarded.</p>\n\n \n<p>Even if you don\u2019t have new ideas yourself, you can help improve other people\u2019s ideas and support the ones you find most promising.  And that\'s not all: If you refer one of your friends or colleagues to the contest via Facebook, Twitter or e-mail, and one of them\u2014or a friend of a friend, or friend of a friend of a friend, etc.\u2014wins the Grand Prize, you\u2019ll receive a share of a $2,000 referral prize!</p>\n \n<p>Current contests address low-carbon energy, building efficiency, adaptation, geoengineering, and many other topics. Entries are due May 31.</p>";

	private final static String FROM_ADDRESS = "no-reply@climatecolab.org";
	private final static String EMAIL_SENT = BalloonBean.class.getName()
			+ "EMAIL_SENT";

	private final static String USER_EMAIL = BalloonBean.class.getName()
			+ "USER_EMAIL";

	public BalloonBean() throws Exception {
		this(false);
	}

	/**
	 * if testing, set skipinit to true
	 * 
	 * @param skipInit
	 * @throws SystemException
	 */
	public BalloonBean(boolean skipInit) throws Exception {
		if (!skipInit) {
			init();
		}
	}

	private void init() throws Exception {
		httpReq = PortalUtil.getOriginalServletRequest(PortalUtil
				.getHttpServletRequest(Helper.getPortletRequest()));

		balloonCookie = BalloonCookie.fromCookieArray(httpReq.getCookies());
		email = (balloonCookie != null && balloonCookie.getEmail() != null) ? balloonCookie
				.getEmail() : getEmailOfCurrentUser();

		String GETParamURL = httpReq.getParameter("url");
		if (GETParamURL != null) {
			balloonCookie.setUrl(GETParamURL);
			sendNotificationEmail();
		}

		String GETParamID = httpReq.getParameter("user");

		if (GETParamID != null) {
			balloonCookie.setUuid(GETParamID);
		}

		showShareWidgets = balloonCookie != null
				&& StringUtils.isNotBlank(balloonCookie.getUrl());

		contests = ContestLocalServiceUtil.findByActiveFlag(true, 0);
		Collections.shuffle(contests);

		ensureSignedInUserHasExpandoValue();
	}

	private void sendNotificationEmail() throws AddressException,
			MailEngineException {
		System.out.println("sending mail");
		PortletSession session = Helper.getPortletRequest().getPortletSession();
		if (session.getAttributeMap().containsKey(EMAIL_SENT)) {
			System.out.println("mail already sent");
			return;
		}
		String messageSubject = EMAIL_SUBJECT;
		String messageBody = EMAIL_BODY.replaceAll("URL_PLACEHOLDER",
				balloonCookie.getUrl());

		InternetAddress addressFrom = new InternetAddress(FROM_ADDRESS);

		String mailAdr = session.getAttribute(USER_EMAIL) == null ? balloonCookie.getEmail()
				: session.getAttribute(USER_EMAIL).toString();
		if(StringUtils.isBlank(mailAdr)) mailAdr = email;
		
		if(StringUtils.isBlank(mailAdr)) return;
		
		String[] receipients = new String[] { mailAdr };
		InternetAddress[] addressTo = new InternetAddress[receipients.length];
		for (int i = 0; i < receipients.length; i++) {
			addressTo[i] = new InternetAddress(receipients[i]);
		}

		InternetAddress replyTo[] = { new InternetAddress(FROM_ADDRESS) };

		MailEngine.send(addressFrom, addressTo, null, null, null,
				messageSubject, messageBody, true, replyTo, null, null);
		session.setAttribute(EMAIL_SENT, true);

	}

	public void ensureSignedInUserHasExpandoValue() throws Exception {
		// check if logged in
		try {
			if (Long.parseLong(Helper.getLiferayUserId()) < 1) {
				return;
			}
		} catch (Exception e) {
			return;
		}

		// set user id in expando
		if (balloonCookie != null
				&& StringUtils.isNotBlank(balloonCookie.getUuid())) {
			// add user id to expando table to track his registration
			ExpandoTable table = ExpandoTableLocalServiceUtil.getTable(
					User.class.getName(), CommunityConstants.EXPANDO);
			ExpandoColumn redBalloonColumn = null;
			try {
				redBalloonColumn = ExpandoColumnLocalServiceUtil.getColumn(
						table.getTableId(), CommunityConstants.RED_BALLOON);
			} catch (Exception e) {
				// create column
			}
			if (redBalloonColumn == null) {
				redBalloonColumn = ExpandoColumnLocalServiceUtil.addColumn(
						table.getTableId(), CommunityConstants.RED_BALLOON,
						ExpandoColumnConstants.STRING);
			}

			ExpandoValueLocalServiceUtil.addValue(User.class.getName(),
					CommunityConstants.EXPANDO, CommunityConstants.RED_BALLOON,
					Long.parseLong(Helper.getLiferayUserId()),
					balloonCookie.getUuid());
		}
	}

	public boolean getShowShareWidgets() {
		return showShareWidgets;
	}

	public List<Contest> getContests() {
		return contests;
	}

	/**
	 * @return the cookie
	 */
	public BalloonCookie getCookie() {
		return balloonCookie;
	}

	/**
	 * @return the redirectUserToGetURL
	 */
	public boolean isRedirectUserToGetURL() {
		return redirectUserToGetURL;
	}

	/**
	 * gets email of user which is currently logged in.
	 * 
	 * @return "" if not logged in
	 */
	private String getEmailOfCurrentUser() {
		try {
			return Helper.getLiferayUserEmail();
		} catch (Exception e) {
			return "";
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		Helper.getPortletRequest().getPortletSession()
				.setAttribute(USER_EMAIL, email);
	}

	/**
	 * @return the setCookie
	 */
	public boolean isSetCookie() {
		return balloonCookie != null
				&& (StringUtils.isNotBlank(balloonCookie.getUuid()) || StringUtils
						.isNotBlank(balloonCookie.getUrl()));
	}

	public void requestURL(ActionEvent e) throws AddressException,
			SystemException, PortalException, MailEngineException {

		/**
		 * store email in cookie
		 */
		balloonCookie = new BalloonCookie(balloonCookie);
		balloonCookie.setEmail(email);
		setEmail(email);

		redirectUserToGetURL = true;
	}

	public String getShareUrl() {
		return balloonCookie.getUrl();
	}

	public boolean isCheckBoxActive() {
		return checkBoxActive;
	}

	public void setCheckBoxActive(boolean checkBoxActive) {
		this.checkBoxActive = checkBoxActive;
	}

}
