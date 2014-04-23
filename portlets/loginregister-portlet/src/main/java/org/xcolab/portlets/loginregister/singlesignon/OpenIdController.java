package org.xcolab.portlets.loginregister.singlesignon;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import org.json.*;

import org.xcolab.portlets.loginregister.CreateUserBean;
import org.xcolab.portlets.loginregister.ImageUploadUtils;
import org.xcolab.portlets.loginregister.MainViewController;

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

        String redirectUrl = (String)session.getAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY);
        session.removeAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY);

        // Check whether the state token matches => CSRF protection
        String stateToken = request.getParameter("state");
        String authCode = request.getParameter("code");
        if (authCode != null && stateToken != null && stateToken.equals(session.getAttribute(GOOGLE_OAUTH_REQUEST_STATE_TOKEN))) {
            JSONObject json = new GoogleAuthHelper(themeDisplay.getPortalURL() + SSOKeys.OPEN_ID_RESPONSE_URL).getUserInfoJson(authCode);

            String openId = json.getString("openid_id");
            String firstName = json.getString("given_name");
            String lastName = json.getString("family_name");
            String emailAddress = json.getString("email");
            String profilePicURL = json.getString("picture");
            User user = null;
            try {
                user = UserLocalServiceUtil.getUserByOpenId(
                        themeDisplay.getCompanyId(), openId);
                session.setAttribute(SSOKeys.OPEN_ID_LOGIN, new Long(user.getUserId()));

                actionResponse.sendRedirect(redirectUrl);
                ImageUploadUtils.updateProfilePicture(user, profilePicURL);
            }
            catch (NoSuchUserException nsue) {
                // try to get user by email
                try {
                    user = UserLocalServiceUtil.getUserByEmailAddress(themeDisplay.getCompanyId(),emailAddress);
                    user.setOpenId(openId);
                    UserLocalServiceUtil.updateUser(user);
                    session.setAttribute(SSOKeys.OPEN_ID_LOGIN, new Long(user.getUserId()));
                    actionResponse.sendRedirect(redirectUrl);

                    ImageUploadUtils.updateProfilePicture(user, profilePicURL);
                    user.getPortraitURL(themeDisplay);
                }catch (NoSuchUserException nsue2){
                    // forward to login or register
                    session.setAttribute(SSOKeys.SSO_OPENID_ID, openId);
                    if (Validator.isNotNull(emailAddress)) {
                        session.setAttribute(SSOKeys.SSO_EMAIL, emailAddress);
                        // Screenname = email prefix until @ character
                        String screenName = emailAddress.substring(0, emailAddress.indexOf(CharPool.AT));
                        screenName = screenName.replaceAll("[^a-zA-Z0-9]","");
                        session.setAttribute(SSOKeys.SSO_SCREEN_NAME, screenName);
                    }
                    if (Validator.isNotNull(firstName)) session.setAttribute(SSOKeys.SSO_FIRST_NAME, firstName);
                    if (Validator.isNotNull(lastName)) session.setAttribute(SSOKeys.SSO_LAST_NAME, lastName);
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
}