package org.xcolab.portlets.redballoon;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.mail.MailEngineException;

public class BalloonBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email = "";
	private boolean redirectUserToGetURL = false;
	private BalloonCookie cookie;
	private HttpServletRequest httpReq;
	private View page = View.ABOUT_COLAB;
	private boolean showShareWidgets;
	private List<Contest> contests;

	/**
	 * views
	 * 
	 * @author pdeboer
	 * 
	 */
	public enum View {
		ABOUT_REFERRAL, ABOUT_COLAB;
	}

	/**
	 * @return the page
	 */
	public View getPage() {
		return page;
	}

	public BalloonBean() throws SystemException {
		this(false);
	}

	/**
	 * if testing, set skipinit to true
	 * 
	 * @param skipInit
	 * @throws SystemException
	 */
	public BalloonBean(boolean skipInit) throws SystemException {
		if (!skipInit) {
			init();
		}
	}

	private void init() throws SystemException {
		httpReq = PortalUtil.getOriginalServletRequest(PortalUtil
				.getHttpServletRequest(Helper.getPortletRequest()));

		cookie = BalloonCookie.fromCookieArray(httpReq.getCookies());
		email = (cookie != null && cookie.getEmail() != null) ? cookie
				.getEmail() : getEmailOfCurrentUser();

		String GETParamURL = httpReq.getParameter("url");
		if (GETParamURL != null) {
			cookie.setUrl(GETParamURL);
		}

		String GETParamID = httpReq.getParameter("user");

		if (GETParamID != null) {
			cookie.setUuid(GETParamID);
		}

		if (StringUtils.isNotBlank(GETParamURL)
				|| (cookie != null && StringUtils.isNotBlank(cookie.getUrl()))) {
			page = View.ABOUT_REFERRAL;
		} else {
			page = View.ABOUT_COLAB;
		}
		showShareWidgets = cookie != null
				&& StringUtils.isNotBlank(cookie.getUrl());

		contests = ContestLocalServiceUtil.findByActiveFlag(true, 0);
		Collections.shuffle(contests);
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
		return cookie;
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
	}

	/**
	 * @return the setCookie
	 */
	public boolean isSetCookie() {
		return cookie != null
				&& (StringUtils.isNotBlank(cookie.getUuid()) || StringUtils
						.isNotBlank(cookie.getUrl()));
	}

	public void requestURL(ActionEvent e) throws AddressException,
			SystemException, PortalException, MailEngineException {

		/**
		 * store email in cookie
		 */
		cookie = new BalloonCookie(cookie);
		cookie.setEmail(email);

		redirectUserToGetURL = true;
	}

	public void toggleReferralPage(ActionEvent e) {
		page = View.ABOUT_REFERRAL;
	}
	
	public void showColabPage(ActionEvent e) {
		page = View.ABOUT_COLAB;
	}

	public String getShareUrl() {
		return cookie.getUrl();
	}

}
