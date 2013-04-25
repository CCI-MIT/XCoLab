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
	private String page = "2aboutReferral";
	private boolean redirectUserToGetURL = false;
	private String display = "nothing";

	/**
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * 
	 */
	public BalloonBean() {
		email = getEmailOfCurrentUser();
		HttpServletRequest httpReq = PortalUtil
				.getOriginalServletRequest(PortalUtil
						.getHttpServletRequest(Helper.getPortletRequest()));
		display = httpReq.getParameter("param");
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

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void requestURL(ActionEvent e) throws AddressException,
			SystemException, PortalException, MailEngineException {
		System.out.println("I was executed!");
		redirectUserToGetURL = true;
	}
}
