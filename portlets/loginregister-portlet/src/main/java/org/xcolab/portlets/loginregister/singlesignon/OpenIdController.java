package org.xcolab.portlets.loginregister.singlesignon;

import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.portlets.loginregister.CreateUserBean;
import org.xcolab.portlets.loginregister.ImageUploadUtils;
import org.xcolab.portlets.loginregister.MainViewController;
import org.xcolab.portlets.loginregister.exception.UserLocationNotResolvableException;

import java.io.IOException;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "view", params = "SSO=google")
public class OpenIdController {

    private static final String GOOGLE_OAUTH_REQUEST_STATE_TOKEN = "GOOGLE_OAUTH_REQUEST_STATE_TOKEN";

    @RequestMapping(params = "action=initiateOpenIdRegistration")
    public void initiateOpenIdRegistration(ActionRequest actionRequest, Model model, ActionResponse actionResponse)
            throws IOException, SystemException {
        HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
        HttpSession session = request.getSession();
        session.setAttribute(MainViewController.SSO_TARGET_KEY, MainViewController.SSO_TARGET_REGISTRATION);

        initiateOpenIdRequest(actionRequest, actionResponse);
    }

    private void initiateOpenIdRequest(ActionRequest actionRequest, ActionResponse actionResponse)
            throws IOException, SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
        HttpSession session = request.getSession();

        GoogleAuthHelper helper = new GoogleAuthHelper(ConfigurationAttributeKey.COLAB_URL.get() + SSOKeys.OPEN_ID_RESPONSE_URL);
        String requestUrl = helper.buildLoginUrl();
        // Add the openid.realm parameter in order to get an OpenId 2.0 identifier
        requestUrl += "&openid.realm=" + ConfigurationAttributeKey.COLAB_URL.get() + SSOKeys.OPEN_ID_RESPONSE_URL;

        session.setAttribute(GOOGLE_OAUTH_REQUEST_STATE_TOKEN, helper.getStateToken());
        actionResponse.sendRedirect(requestUrl);

        String referrer = request.getHeader("referer");
        session.setAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY, referrer);
    }

    @RequestMapping(params = "action=initiateOpenIdLogin")
    public void initiateOpenIdLogin(ActionRequest actionRequest, Model model, ActionResponse actionResponse)
            throws IOException, SystemException {
        HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
        HttpSession session = request.getSession();
        session.setAttribute(MainViewController.SSO_TARGET_KEY, MainViewController.SSO_TARGET_LOGIN);

        initiateOpenIdRequest(actionRequest, actionResponse);
    }

    @RequestMapping(params = "action=readOpenIdResponse")
    public void readOpenIdResponse(ActionRequest actionRequest, Model model,
            ActionResponse actionResponse) throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
        PortletSession portletSession = actionRequest.getPortletSession();
        HttpSession session = request.getSession();
        session.removeAttribute(SSOKeys.FACEBOOK_USER_ID);

        String redirectUrl = (String) session.getAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY);
        if (Validator.isNull(redirectUrl) || Validator.isBlank(redirectUrl)) {
            redirectUrl = themeDisplay.getURLHome();
        }
        session.removeAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY);

        // Check whether the state token matches => CSRF protection
        String stateToken = request.getParameter("state");
        String authCode = request.getParameter("code");
        if (authCode != null && stateToken != null && stateToken
                .equals(session.getAttribute(GOOGLE_OAUTH_REQUEST_STATE_TOKEN))) {
            JSONObject json = new GoogleAuthHelper(ConfigurationAttributeKey.COLAB_URL.get() + SSOKeys.OPEN_ID_RESPONSE_URL)
                    .getUserInfoJson(authCode);

            String openId = json.getString("openid_id");
            String firstName = json.getString("given_name").replaceAll("[^0-9a-zA-Z\\-\\_\\.]", "");
            String lastName = json.getString("family_name").replaceAll("[^0-9a-zA-Z\\-\\_\\.]", "");
            String emailAddress = json.getString("email");
            String profilePicURL = json.getString("picture");

            String country = null;

            try {
                country = getCountry(json.getString("locale"));
            } catch (UserLocationNotResolvableException ignored) { }

            HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(actionRequest);
            String path = httpReq.getSession().getServletContext().getRealPath("/");

            User user;
            try {
                user = UserLocalServiceUtil.getUserByOpenId(
                        themeDisplay.getCompanyId(), openId);
                portletSession.setAttribute(SSOKeys.OPEN_ID_LOGIN, user.getUserId(), PortletSession.APPLICATION_SCOPE);

                actionResponse.sendRedirect(redirectUrl);

                ImageUploadUtils.updateProfilePicture(path, user, profilePicURL);
                MembersClient.createLoginLog(user.getUserId(), request.getRemoteAddr(), redirectUrl);
            } catch (NoSuchUserException nsue) {
                // try to get user by email
                try {
                    // Do not try to login via email address if not set
                    if (Validator.isNull(emailAddress)) {
                        throw nsue;
                    }
                    user = UserLocalServiceUtil.getUserByEmailAddress(themeDisplay.getCompanyId(), emailAddress);
                    Member member = MembersClient.getMember(user.getUserId());
                    user.setOpenId(openId);
                    if (Validator.isNotNull(country)) {
                        member.setCountry(country);
                        MembersClient.updateMember(member);
                    }

                    UserLocalServiceUtil.updateUser(user);
                    portletSession
                            .setAttribute(SSOKeys.OPEN_ID_LOGIN, user.getUserId(), PortletSession.APPLICATION_SCOPE);
                    actionResponse.sendRedirect(redirectUrl);
                    MembersClient.createLoginLog(user.getUserId(), request.getRemoteAddr(), redirectUrl);

                    ImageUploadUtils.updateProfilePicture(path, user, profilePicURL);
                    user.getPortraitURL(themeDisplay);
                } catch (NoSuchUserException nsue2) {
                    // forward to login or register
                    portletSession.setAttribute(SSOKeys.SSO_OPENID_ID, openId, PortletSession.APPLICATION_SCOPE);
                    String screenName = null;
                    if (Validator.isNotNull(emailAddress)) {
                        portletSession.setAttribute(SSOKeys.SSO_EMAIL, emailAddress, PortletSession.APPLICATION_SCOPE);
                        // Screenname = email prefix until @ character
                        screenName = emailAddress.substring(0, emailAddress.indexOf(CharPool.AT));
                        screenName = screenName.replaceAll("[^0-9a-zA-Z\\-\\_\\.]", "");
                        portletSession
                                .setAttribute(SSOKeys.SSO_SCREEN_NAME, screenName, PortletSession.APPLICATION_SCOPE);
                    }

                    if (Validator.isNotNull(firstName)) {
                        portletSession
                                .setAttribute(SSOKeys.SSO_FIRST_NAME, firstName, PortletSession.APPLICATION_SCOPE);
                    }
                    if (Validator.isNotNull(lastName)) {
                        portletSession.setAttribute(SSOKeys.SSO_LAST_NAME, lastName, PortletSession.APPLICATION_SCOPE);
                    }
                    portletSession.setAttribute(SSOKeys.SSO_COUNTRY, country, PortletSession.APPLICATION_SCOPE);

                    long imageId = 0;
                    if (Validator.isNotNull(profilePicURL)) {
                        imageId = ImageUploadUtils.linkProfilePicture(path, profilePicURL);
                        portletSession.setAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID, Long.toString(imageId));
                    }

                    if (session.getAttribute(MainViewController.SSO_TARGET_KEY)
                            .equals(MainViewController.SSO_TARGET_LOGIN)) {
                        actionResponse.setRenderParameter("SSO", "general");
                        actionResponse.setRenderParameter("status", "registerOrLogin");
                        actionRequest.setAttribute("credentialsError", false);
                    }

                    // Create the user and login
                    else if (session.getAttribute(MainViewController.SSO_TARGET_KEY)
                            .equals(MainViewController.SSO_TARGET_REGISTRATION)) {
                        // append SSO attributes
                        CreateUserBean userBean = new CreateUserBean();
                        String password = RandomStringUtils.random(12, true, true);
                        userBean.setPassword(password);
                        userBean.setRetypePassword(password);
                        userBean.setFirstName(firstName);
                        userBean.setLastName(lastName);
                        userBean.setEmail(emailAddress);
                        userBean.setCountry(country);
                        userBean.setImageId(Long.toString(imageId));

                        if (Validator.isNotNull(screenName)) {
                            userBean.setScreenName(screenName);

                            // Validate uniqueness of the screen name
                            // The chance of a collision among 40 equal screennames is 50% -> 5 tries should be sufficient
                            for (int i = 0; i < 5; i++) {
                                try {
                                    UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(), screenName);
                                    // Generate a random suffix for the non-unique screenName
                                    screenName =
                                            userBean.getScreenName().concat(RandomStringUtils.random(4, false, true));
                                } catch (PortalException e) {
                                    break;
                                }
                            }
                        }

                        userBean.setScreenName(screenName);

                        MainViewController
                                .completeRegistration(actionRequest, actionResponse, userBean, redirectUrl, true);
                    }
                }
            }
        } else {
            actionResponse.sendRedirect(redirectUrl);
        }
    }

    private String getCountry(String localeCountryString) throws UserLocationNotResolvableException {
        return getCountryFromLocaleObject(localeCountryString);
    }

    private String getCountryFromLocaleObject(String localeCountryString) throws UserLocationNotResolvableException {
        if (Validator.isNotNull(localeCountryString)) {
            Locale locale = new Locale("en", localeCountryString);

            return locale.getDisplayCountry();
        }

        throw new UserLocationNotResolvableException("Could not retrieve country from Google locale");
    }
}