package org.xcolab.portlets.redballoon;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.mail.internet.AddressException;
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
		}

		String GETParamID = httpReq.getParameter("user");

		if (GETParamID != null) {
			balloonCookie.setUuid(GETParamID);
		}

		if (StringUtils.isNotBlank(GETParamURL)
				|| (balloonCookie != null && StringUtils
						.isNotBlank(balloonCookie.getUrl()))) {
			page = View.ABOUT_REFERRAL;
		} else {
			page = View.ABOUT_COLAB;
		}
		showShareWidgets = balloonCookie != null
				&& StringUtils.isNotBlank(balloonCookie.getUrl());

		contests = ContestLocalServiceUtil.findByActiveFlag(true, 0);
		Collections.shuffle(contests);

		ensureSignedInUserHasExpandoValue();
	}

	public void ensureSignedInUserHasExpandoValue() throws Exception {
		//check if logged in
		try {
			if(Long.parseLong(Helper.getLiferayUserId()) < 1) {
				return;
			}
		}catch(Exception e){
			return;
		}
		
		//set user id in expando
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

		redirectUserToGetURL = true;
	}

	public void toggleReferralPage(ActionEvent e) {
		page = View.ABOUT_REFERRAL;
	}

	public void showColabPage(ActionEvent e) {
		page = View.ABOUT_COLAB;
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
