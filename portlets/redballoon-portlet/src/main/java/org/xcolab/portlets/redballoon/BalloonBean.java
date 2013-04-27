package org.xcolab.portlets.redballoon;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

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
	private View forceNextPage;
	private BalloonCookie cookie;
	private HttpServletRequest httpReq;
	private boolean setCookie = false;

	/**
	 * views
	 * 
	 * @author pdeboer
	 * 
	 */
	public enum View {
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
		httpReq = PortalUtil.getOriginalServletRequest(PortalUtil
				.getHttpServletRequest(Helper.getPortletRequest()));

		cookie = BalloonCookie.fromCookieArray(httpReq.getCookies());

		email = (cookie != null && cookie.getEmail() != null) ? cookie
				.getEmail() : getEmailOfCurrentUser();

		String GETParamURL = httpReq.getParameter("url");

		if (GETParamURL != null) {
			cookie = new BalloonCookie(cookie);
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

		/**
		 * store email in cookie
		 */
		cookie = new BalloonCookie(cookie);
		cookie.setEmail(email);
		setCookie = true;

		redirectUserToGetURL = true;
		forceNextPage = View.ABOUT_REFERRAL;
	}

	public void aboutColabToAboutReferral(ActionEvent e) {
		System.out.println("change sites");
		forceNextPage = View.ABOUT_REFERRAL;
	}

}
