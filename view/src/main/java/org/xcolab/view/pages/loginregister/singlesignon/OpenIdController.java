package org.xcolab.view.pages.loginregister.singlesignon;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.pages.loginregister.CreateUserBean;
import org.xcolab.view.pages.loginregister.ImageUploadUtils;
import org.xcolab.view.pages.loginregister.MainViewController;
import org.xcolab.view.pages.loginregister.exception.UserLocationNotResolvableException;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "view", params = "SSO=google")
public class OpenIdController {

    private static final String GOOGLE_OAUTH_REQUEST_STATE_TOKEN = "GOOGLE_OAUTH_REQUEST_STATE_TOKEN";

    @RequestMapping(params = "action=initiateOpenIdRegistration")
    public void initiateOpenIdRegistration(HttpServletRequest request, Model model, HttpServletResponse HttpServletResponse)
            throws IOException, SystemException {
        HttpSession session = request.getSession();
        session.setAttribute(MainViewController.SSO_TARGET_KEY, MainViewController.SSO_TARGET_REGISTRATION);

        initiateOpenIdRequest(request, HttpServletResponse);
    }

    private void initiateOpenIdRequest(HttpServletRequest request, HttpServletResponse HttpServletResponse)
            throws IOException, SystemException {
        HttpSession session = request.getSession();

        GoogleAuthHelper helper = new GoogleAuthHelper(ConfigurationAttributeKey.COLAB_URL.get() + SSOKeys.OPEN_ID_RESPONSE_URL);
        String requestUrl = helper.buildLoginUrl();
        // Add the openid.realm parameter in order to get an OpenId 2.0 identifier
        requestUrl += "&openid.realm=" + ConfigurationAttributeKey.COLAB_URL.get() + SSOKeys.OPEN_ID_RESPONSE_URL;

        session.setAttribute(GOOGLE_OAUTH_REQUEST_STATE_TOKEN, helper.getStateToken());
        HttpServletResponse.sendRedirect(requestUrl);

        String referrer = request.getHeader("referer");
        session.setAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY, referrer);
    }

    @RequestMapping(params = "action=initiateOpenIdLogin")
    public void initiateOpenIdLogin(HttpServletRequest request, Model model, HttpServletResponse HttpServletResponse)
            throws IOException, SystemException {
        HttpSession session = request.getSession();
        session.setAttribute(MainViewController.SSO_TARGET_KEY, MainViewController.SSO_TARGET_LOGIN);

        initiateOpenIdRequest(request, HttpServletResponse);
    }

    @RequestMapping(params = "action=readOpenIdResponse")
    public void readOpenIdResponse(HttpServletRequest request, Model model,
            HttpServletResponse response)
            throws IOException, JSONException, MemberNotFoundException {

        HttpSession portletSession = request.getSession();
        HttpSession session = request.getSession();
        session.removeAttribute(SSOKeys.FACEBOOK_USER_ID);

        String redirectUrl = (String) session.getAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY);
        if (StringUtils.isBlank(redirectUrl)) {
            redirectUrl = "/";
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

            String path = request.getSession().getServletContext().getRealPath("/");

            Member member;
            try {
                member = MembersClient.findMemberByOpenId(openId);
                portletSession.setAttribute(SSOKeys.OPEN_ID_LOGIN, member.getUserId());

                response.sendRedirect(redirectUrl);

                ImageUploadUtils.updateProfilePicture(path, member, profilePicURL);
                MembersClient.createLoginLog(member.getUserId(), request.getRemoteAddr(), redirectUrl);
            } catch (MemberNotFoundException e) {
                // try to get user by email
                try {
                    // Do not try to login via email address if not set
                    if (StringUtils.isBlank(emailAddress)) {
                        throw e;
                    }
                    member = MembersClient.findMemberByEmailAddress(emailAddress);
                    member.setOpenId(openId);
                    if (StringUtils.isNotBlank(country)) {
                        member.setCountry(country);
                        MembersClient.updateMember(member);
                    }

                    MembersClient.updateMember(member);
                    portletSession
                            .setAttribute(SSOKeys.OPEN_ID_LOGIN, member.getUserId());
                    response.sendRedirect(redirectUrl);
                    MembersClient.createLoginLog(member.getUserId(), request.getRemoteAddr(), redirectUrl);

                    ImageUploadUtils.updateProfilePicture(path, member, profilePicURL);
                } catch (MemberNotFoundException e2) {
                    // forward to login or register
                    portletSession.setAttribute(SSOKeys.SSO_OPENID_ID, openId);
                    String screenName = null;
                    if (StringUtils.isNotBlank(emailAddress)) {
                        portletSession.setAttribute(SSOKeys.SSO_EMAIL, emailAddress);
                        // Screenname = email prefix until @ character
                        screenName = emailAddress.substring(0, emailAddress.indexOf("@"));
                        screenName = screenName.replaceAll("[^0-9a-zA-Z\\-\\_\\.]", "");
                        portletSession
                                .setAttribute(SSOKeys.SSO_SCREEN_NAME, screenName);
                    }

                    if (StringUtils.isNotBlank(firstName)) {
                        portletSession
                                .setAttribute(SSOKeys.SSO_FIRST_NAME, firstName);
                    }
                    if (StringUtils.isNotBlank(lastName)) {
                        portletSession.setAttribute(SSOKeys.SSO_LAST_NAME, lastName);
                    }
                    portletSession.setAttribute(SSOKeys.SSO_COUNTRY, country);

                    long imageId = 0;
                    if (StringUtils.isNotBlank(profilePicURL)) {
                        imageId = ImageUploadUtils.linkProfilePicture(path, profilePicURL);
                        portletSession.setAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID, Long.toString(imageId));
                    }

                    if (session.getAttribute(MainViewController.SSO_TARGET_KEY)
                            .equals(MainViewController.SSO_TARGET_LOGIN)) {
                        //TODO: redirect
//                        response.setRenderParameter("SSO", "general");
//                        response.setRenderParameter("status", "registerOrLogin");
                        request.setAttribute("credentialsError", false);
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

                        if (StringUtils.isNotBlank(screenName)) {
                            userBean.setScreenName(screenName);

                            // Validate uniqueness of the screen name
                            // The chance of a collision among 40 equal screennames is 50% -> 5 tries should be sufficient
                            for (int i = 0; i < 5; i++) {
                                try {
                                    MembersClient.findMemberByScreenName(screenName);
                                    // Generate a random suffix for the non-unique screenName
                                    screenName =
                                            userBean.getScreenName().concat(RandomStringUtils.random(4, false, true));
                                } catch (MemberNotFoundException e3) {
                                    //screen name not used - we can continue
                                    break;
                                }
                            }
                        }

                        userBean.setScreenName(screenName);

                        MainViewController
                                .completeRegistration(request, response, userBean, redirectUrl, true);
                    }
                }
            }
        } else {
            response.sendRedirect(redirectUrl);
        }
    }

    private String getCountry(String localeCountryString) throws UserLocationNotResolvableException {
        return getCountryFromLocaleObject(localeCountryString);
    }

    private String getCountryFromLocaleObject(String localeCountryString) throws UserLocationNotResolvableException {
        if (StringUtils.isNotEmpty(localeCountryString)) {
            Locale locale = new Locale("en", localeCountryString);

            return locale.getDisplayCountry();
        }

        throw new UserLocationNotResolvableException("Could not retrieve country from Google locale");
    }
}