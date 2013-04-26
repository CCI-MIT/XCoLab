package org.xcolab.portlets.redballoon;

import javax.faces.event.ActionEvent;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.mail.MailEngineException;

public class BalloonBean {
	private String email = "";
	private boolean redirectUserToGetURL = false;
	private View forceNextPage = null;
	private BalloonCookie cookie;
	private HttpServletRequest httpReq;
	private boolean setCookie = false;

	/**
	 * views
	 * 
	 * @author pdeboer
	 * 
	 */
	private enum View {
		ABOUT_REFERRAL, ABOUT_COLAB, SHARE
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		if (forceNextPage != null) {
			return forceNextPage.toString();
		} else if (cookie != null && cookie.getUuid() != null) {
			return View.SHARE.toString();
		} else {
			return View.ABOUT_COLAB.toString();
		}
	}

	public BalloonBean() {
		this(false);
	}

	/**
	 * if testing, set skipinit to true
	 * 
	 * @param skipInit
	 */
	public BalloonBean(boolean skipInit) {
		setCookie = false;
		
		if (!skipInit) {
			init();
		}
	}
	
	/**
	 * @return the cookie
	 */
	public BalloonCookie getCookie() {
		return cookie;
	}

	private void init() {
		email = getEmailOfCurrentUser();
		httpReq = PortalUtil.getOriginalServletRequest(PortalUtil
				.getHttpServletRequest(Helper.getPortletRequest()));

		cookie = BalloonCookie.fromCookieArray(httpReq.getCookies());

		String GETParamURL = httpReq.getParameter("url");

		if (GETParamURL != null) {
			cookie = new BalloonCookie();
			cookie.setUuid(GETParamURL);

			setCookie = true;
		}
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
		return setCookie;
	}

	public void requestURL(ActionEvent e) throws AddressException,
			SystemException, PortalException, MailEngineException {
		System.out.println("I was executed!");
		redirectUserToGetURL = true;
	}
}
