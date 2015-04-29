package org.xcolab.portlets.loginregister.singlesignon;

import com.ext.portlet.community.CommunityConstants;
import com.ext.portlet.service.LoginLogLocalServiceUtil;
import com.ext.utils.iptranslation.Location;
import com.ext.utils.iptranslation.service.IpTranslationServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.*;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

import org.json.*;

import org.xcolab.portlets.loginregister.CreateUserBean;
import org.xcolab.portlets.loginregister.ImageUploadUtils;
import org.xcolab.portlets.loginregister.MainViewController;
import org.xcolab.portlets.loginregister.exception.UserLocationNotResolveableException;

@Controller
@RequestMapping(value = "view", params = "SSO=google")
public class OpenIdController {

    private Log _log = LogFactoryUtil.getLog(OpenIdController.class);

    private static final String GOOGLE_OAUTH_REQUEST_STATE_TOKEN = "GOOGLE_OAUTH_REQUEST_STATE_TOKEN";


    @RequestMapping(params = "action=initiateOpenIdRegistration")
    public void initiateOpenIdRegistration(ActionRequest actionRequest, Model model, ActionResponse actionResponse) throws Exception{
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
        HttpSession session = request.getSession();
        session.setAttribute(MainViewController.SSO_TARGET_KEY, MainViewController.SSO_TARGET_REGISTRATION);

        initiateOpenIdRequest(actionRequest, model, actionResponse);
    }

    @RequestMapping(params = "action=initiateOpenIdLogin")
    public void initiateOpenIdLogin(ActionRequest actionRequest, Model model, ActionResponse actionResponse) throws Exception{

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
        HttpSession session = request.getSession();
        session.setAttribute(MainViewController.SSO_TARGET_KEY, MainViewController.SSO_TARGET_LOGIN);

        initiateOpenIdRequest(actionRequest, model, actionResponse);

    }

    private void initiateOpenIdRequest(ActionRequest actionRequest, Model model, ActionResponse actionResponse) throws Exception{
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
        HttpSession session = request.getSession();

        GoogleAuthHelper helper = new GoogleAuthHelper(themeDisplay.getPortalURL() + SSOKeys.OPEN_ID_RESPONSE_URL);
        String requestUrl = helper.buildLoginUrl();
        // Add the openid.realm parameter in order to get an OpenId 2.0 identifier
        requestUrl += "&openid.realm=" + themeDisplay.getPortalURL() + SSOKeys.OPEN_ID_RESPONSE_URL;

        session.setAttribute(GOOGLE_OAUTH_REQUEST_STATE_TOKEN, helper.getStateToken());
        actionResponse.sendRedirect(requestUrl);

        String referrer = request.getHeader("referer");
        session.setAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY, referrer);
    }


    @RequestMapping(params = "action=readOpenIdResponse")
    public void readOpenIdResponse(ActionRequest actionRequest, Model model, ActionResponse actionResponse) throws Exception{

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
        HttpSession session = request.getSession();
		session.removeAttribute(SSOKeys.FACEBOOK_USER_ID);

        String redirectUrl = (String)session.getAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY);
        if(Validator.isNull(redirectUrl) || Validator.isBlank(redirectUrl)) redirectUrl =  themeDisplay.getURLHome();
        session.removeAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY);

        // Check whether the state token matches => CSRF protection
        String stateToken = request.getParameter("state");
        String authCode = request.getParameter("code");
        if (authCode != null && stateToken != null && stateToken.equals(session.getAttribute(GOOGLE_OAUTH_REQUEST_STATE_TOKEN))) {
            JSONObject json = new GoogleAuthHelper(themeDisplay.getPortalURL() + SSOKeys.OPEN_ID_RESPONSE_URL).getUserInfoJson(authCode);

            String openId = json.getString("openid_id");
            String firstName = json.getString("given_name").replaceAll("[^0-9a-zA-Z\\-\\_\\.]", "");
            String lastName = json.getString("family_name").replaceAll("[^0-9a-zA-Z\\-\\_\\.]", "");
            String emailAddress = json.getString("email");
            String profilePicURL = json.getString("picture");

			String city = null;
			String country = null;
			try {
				city = getCity(request.getRemoteAddr());

			} catch (UserLocationNotResolveableException e) {
				_log.warn(e);
			}

			try {
				country = getCountry(request, json.getString("locale"));
			} catch (UserLocationNotResolveableException e) {
				_log.warn(e);
			}

            User user = null;
            try {
                user = UserLocalServiceUtil.getUserByOpenId(
                        themeDisplay.getCompanyId(), openId);
                session.setAttribute(SSOKeys.OPEN_ID_LOGIN, new Long(user.getUserId()));

                actionResponse.sendRedirect(redirectUrl);
                ImageUploadUtils.updateProfilePicture(user, profilePicURL);
				LoginLogLocalServiceUtil.createLoginLog(user, request.getRemoteAddr(), redirectUrl);
            }
            catch (NoSuchUserException nsue) {
                // try to get user by email
                try {
                    user = UserLocalServiceUtil.getUserByEmailAddress(themeDisplay.getCompanyId(),emailAddress);
                    user.setOpenId(openId);
					if (Validator.isNotNull(city)) {
						setExpandoValue(user, CommunityConstants.CITY, city);
					}
					if (Validator.isNotNull(country)) {
						setExpandoValue(user, CommunityConstants.COUNTRY, country);
					}

                    UserLocalServiceUtil.updateUser(user);
                    session.setAttribute(SSOKeys.OPEN_ID_LOGIN, new Long(user.getUserId()));
                    actionResponse.sendRedirect(redirectUrl);
					LoginLogLocalServiceUtil.createLoginLog(user, request.getRemoteAddr(), redirectUrl);

                    ImageUploadUtils.updateProfilePicture(user, profilePicURL);
                    user.getPortraitURL(themeDisplay);
                }catch (NoSuchUserException nsue2){
                    // forward to login or register
                    session.setAttribute(SSOKeys.SSO_OPENID_ID, openId);
                    if (Validator.isNotNull(emailAddress)) {
                        session.setAttribute(SSOKeys.SSO_EMAIL, emailAddress);
                        // Screenname = email prefix until @ character
                        String screenName = emailAddress.substring(0, emailAddress.indexOf(CharPool.AT));
                        screenName = screenName.replaceAll("[^0-9a-zA-Z\\-\\_\\.]", "");
                        session.setAttribute(SSOKeys.SSO_SCREEN_NAME, screenName);
                    }
                    if (Validator.isNotNull(firstName)) session.setAttribute(SSOKeys.SSO_FIRST_NAME, firstName);
                    if (Validator.isNotNull(lastName)) session.setAttribute(SSOKeys.SSO_LAST_NAME, lastName);
					session.setAttribute(SSOKeys.SSO_CITY, city);
					session.setAttribute(SSOKeys.SSO_COUNTRY, country);

					if (Validator.isNotNull(profilePicURL)) {
                        long imageId = ImageUploadUtils.linkProfilePicture(profilePicURL);
                        session.setAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID, Long.toString(imageId));
                    }

                    if (session.getAttribute(MainViewController.SSO_TARGET_KEY).equals(MainViewController.SSO_TARGET_LOGIN)) {
                        actionResponse.setRenderParameter("SSO", "general");
                        actionResponse.setRenderParameter("status", "registerOrLogin");
                        actionRequest.setAttribute("credentialsError", false);
                    }

                    // Create the user and login
                    else if (session.getAttribute(MainViewController.SSO_TARGET_KEY).equals(MainViewController.SSO_TARGET_REGISTRATION)) {
                        // append SSO attributes
                        CreateUserBean userBean = new CreateUserBean();
                        String password = RandomStringUtils.random(12, true, true);
                        userBean.setPassword(password);
                        userBean.setRetypePassword(password);

                        MainViewController.getSSOUserInfo(actionRequest.getPortletSession(), userBean);

                        // Validate uniqueness of the screen name
                        // The chance of a collision among 40 equal screennames is 50% -> 5 tries should be sufficient
                        String screenName = userBean.getScreenName();
                        for (int i = 0; i < 5; i++) {
                            try {
                                User duplicateUser = UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(), screenName);
                                // Generate a random suffix for the non-unique screenName
                                screenName = userBean.getScreenName().concat(RandomStringUtils.random(4, false, true));
                            } catch (PortalException e) {
                                break;
                            }
                        }

                        userBean.setScreenName(screenName);

                        MainViewController.completeRegistration(actionRequest, actionResponse, userBean, redirectUrl, true);
                    }
                }
            }
        } else {
            actionResponse.sendRedirect(redirectUrl);
        }
    }

    protected String getFirstValue(List<String> values) {
        if ((values == null) || (values.size() < 1)) {
            return null;
        }
        return values.get(0);
    }

	private String getCountry(HttpServletRequest req, String localeCountryString) throws UserLocationNotResolveableException {
		try {
			return getCountryFromLocaleObject(localeCountryString);
		} catch (UserLocationNotResolveableException e1) {
			return getCountryFromRemoteAddress(req.getRemoteAddr());
		}
	}

	private String getCountryFromLocaleObject(String localeCountryString) throws UserLocationNotResolveableException {
		if (Validator.isNotNull(localeCountryString)) {
			Locale locale = new Locale("en", localeCountryString);

			return locale.getDisplayCountry();
		}

		throw new UserLocationNotResolveableException("Could not retrieve country from Google locale");
	}

	private String getCountryFromRemoteAddress(String ipAddr) throws UserLocationNotResolveableException {
		try {
			Location location = IpTranslationServiceUtil.getLocationForIp(ipAddr);
			if (Validator.isNotNull(location)) {
				return location.getCountryName();
			}
		} finally {
			throw new UserLocationNotResolveableException("Could not retrieve country from IP address");
		}
	}

	private String getCity(String ipAddr) throws UserLocationNotResolveableException {
		try {
			Location location = IpTranslationServiceUtil.getLocationForIp(ipAddr);
			if (Validator.isNotNull(location)) {
				return location.getCity();
			}
		} finally {
			throw new UserLocationNotResolveableException("Could not retrieve city from IP address");
		}
	}

	private void setExpandoValue(User user, String valueName, Object data) throws SystemException, PortalException {
		ExpandoValueLocalServiceUtil.addValue(
				user.getCompanyId(),
				User.class.getName(),
				CommunityConstants.EXPANDO,
				valueName,
				user.getUserId(),
				data);
	}
}