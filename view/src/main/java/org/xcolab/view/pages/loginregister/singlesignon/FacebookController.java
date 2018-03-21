package org.xcolab.view.pages.loginregister.singlesignon;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.auth.handlers.AuthenticationSuccessHandler;
import org.xcolab.view.auth.login.AuthenticationError;
import org.xcolab.view.pages.loginregister.CreateUserBean;
import org.xcolab.view.pages.loginregister.LoginRegisterController;
import org.xcolab.view.pages.loginregister.LoginRegisterService;
import org.xcolab.view.pages.loginregister.exception.UserLocationNotResolvableException;
import org.xcolab.commons.servlet.flash.ErrorPage;
import org.xcolab.commons.servlet.RequestParamUtil;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/sso/facebook")
public class FacebookController {

    private final AuthenticationService authenticationService;
    private final LoginRegisterService loginRegisterService;

    @Autowired
    public FacebookController(AuthenticationService authenticationService,
            AuthenticationSuccessHandler authenticationSuccessHandler,
            LoginRegisterService loginRegisterService) {
        this.authenticationService = authenticationService;
        this.loginRegisterService = loginRegisterService;
    }

    @GetMapping("login")
    public void initiateFbLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute(LoginRegisterController.SSO_TARGET_KEY,
                LoginRegisterController.SSO_TARGET_LOGIN);
        initiateFbRequest(request, response);
    }

    @GetMapping("register")
    public void initiateFbRegistration(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute(LoginRegisterController.SSO_TARGET_KEY,
                LoginRegisterController.SSO_TARGET_REGISTRATION);
        initiateFbRequest(request, response);
    }

    private void initiateFbRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();

        String referrer = request.getHeader(HttpHeaders.REFERER);
        session.setAttribute(LoginRegisterController.PRE_LOGIN_REFERRER_KEY, referrer);

        //TODO COLAB-2172: potentially replace by current URL
        String facebookAuthRedirectURL = FacebookUtil.getAuthRedirectURL(request);

        UriComponentsBuilder facebookAuthURL = UriComponentsBuilder.fromHttpUrl(
                FacebookUtil.AUTH_URL)
                .queryParam("client_id",
                        ConfigurationAttributeKey.FACEBOOK_APPLICATION_ID.get())
                .queryParam("redirect_uri", facebookAuthRedirectURL)
                .queryParam("scope", "email");

        response.sendRedirect(facebookAuthURL.build().toString());
    }

    @GetMapping("callback")
    public void fbCallback(HttpServletRequest request, HttpServletResponse response)
            throws JSONException, MemberNotFoundException, IOException {

        HttpSession session = request.getSession();

        session.removeAttribute(SSOKeys.SSO_GOOGLE_ID);

        String redirectUrl = (String) session
                .getAttribute(LoginRegisterController.PRE_LOGIN_REFERRER_KEY);
        redirectUrl = StringUtils.isEmpty(redirectUrl) ? "/"
                : LinkUtils.getRelativeUri(redirectUrl);

        String code = RequestParamUtil.getString(request, "code");
        String token = FacebookUtil.getAccessToken(request, code);

        JSONObject jsonObject = FacebookUtil.getGraphResources("/me", token,
                "id,email,first_name,last_name,gender,verified,location,locale");

        if (jsonObject == null || jsonObject.optJSONObject("error") != null) {
            ErrorPage.error("No data received from Facebook")
                    .flashAndRedirect(request, response);
            return;
        }

        if (ConfigurationAttributeKey.FACEBOOK_VERIFIED_REQUIRED.get()
                && !jsonObject.getBoolean("verified")) {
            ErrorPage
                    .error("A verified Facebook account is required to complete the registration")
                    .withTitle("Could not complete the Facebook registration")
                    .flashAndRedirect(request, response);
            return;
        }

        long facebookId = jsonObject.getLong("id");
        String fbProfilePictureURL =
                String.format(FacebookUtil.FB_PROFILE_PIC_URL_FORMAT_STRING, facebookId);

        Member member;

        if (facebookId > 0) {
            // SSO Attribute
            session.setAttribute(SSOKeys.FACEBOOK_USER_ID, String.valueOf(facebookId));

            try {
                member = MembersClient.findMemberByFacebookId(facebookId);

                /*
                String realPictureURLString = FacebookUtil.getFacebookPictureURLString(fbProfilePictureURL);
                if (realPictureURLString != null) {
                    String path = httpReq.getSession().getServletContext().getRealPath("/");
                    ImageUploadUtils.updateProfilePicture(path, liferayUser, realPictureURLString);
                }*/

                loginRegisterService.logIn(request, response, member);
                response.sendRedirect(redirectUrl);
                return;
            } catch (MemberNotFoundException ignored) {
            }
        }

        // if email matches -> autologin
        String emailAddress = jsonObject.optString("email");
        if (StringUtils.isNotBlank(emailAddress) && emailAddress.contains("@")) {
            try {
                member = MembersClient.findMemberByEmailAddress(emailAddress);
                updateUserWithFBId(member, facebookId);

                /*
                String realPictureURLString = FacebookUtil.getFacebookPictureURLString(fbProfilePictureURL);
                if (realPictureURLString != null) {
                    String path = httpReq.getSession().getServletContext().getRealPath("/");
                    ImageUploadUtils.updateProfilePicture(path, liferayUser, realPictureURLString);
                }*/

                updateUserAccountInformation(member, jsonObject);

                loginRegisterService.logIn(request, response, member);
                response.sendRedirect(redirectUrl);
                return;
            } catch (MemberNotFoundException ignored) {
            }
        }

        final String firstName = jsonObject.optString("first_name");
        if ((firstName != null)) {
            session.setAttribute(SSOKeys.SSO_FIRST_NAME, firstName);
        }

        final String lastName = jsonObject.optString("last_name");
        if ((lastName != null)) {
            session.setAttribute(SSOKeys.SSO_LAST_NAME, lastName);
        }
        if (StringUtils.isNotBlank(emailAddress) && emailAddress.contains("@")) {
            session.setAttribute(SSOKeys.SSO_EMAIL, emailAddress);
            // Screen name = email prefix until @ character
            String screenName = emailAddress.substring(0, emailAddress.indexOf('@'));
            screenName = screenName.replaceAll("[^a-zA-Z0-9]", "");
            session.setAttribute(SSOKeys.SSO_SCREEN_NAME, screenName);
        }

        session.setAttribute(SSOKeys.SSO_COUNTRY, getCountry(jsonObject));

        // Get the FB image url
        /*
        if (facebookId > 0) {
            String realPictureURLString = FacebookUtil.getFacebookPictureURLString(fbProfilePictureURL);
            if (realPictureURLString != null) {
                String path = httpReq.getSession().getServletContext().getRealPath("/");
                long imageId = ImageUploadUtils.linkProfilePicture(path,
                        FacebookUtil.getFacebookPictureURLString(fbProfilePictureURL));
                portletSession.setAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID, Long.toString(imageId),
                        PortletSession.APPLICATION_SCOPE);
            }
        }*/

        // Finish registration
        if (session.getAttribute(LoginRegisterController.SSO_TARGET_KEY) != null &&
                session.getAttribute(LoginRegisterController.SSO_TARGET_KEY)
                        .equals(LoginRegisterController.SSO_TARGET_REGISTRATION)) {

            // append SSO attributes
            CreateUserBean userBean = new CreateUserBean();
            String password = RandomStringUtils.random(12, true, true);
            userBean.setPassword(password);
            userBean.setRetypePassword(password);

            LoginRegisterController.getSSOUserInfo(request.getSession(), userBean);

            // Validate uniqueness of the screen name
            // The chance of a collision among 40 equal screennames is 50% -> 5 tries should be sufficient
            String screenName = userBean.getScreenName();
            for (int i = 0; i < 5; i++) {
                try {
                    MembersClient.findMemberByScreenName(screenName);
                    //TODO COLAB-2172: find better way to resolve conflicts
                    screenName = userBean.getScreenName().concat(RandomStringUtils.random(4, false, true));
                } catch (MemberNotFoundException e) {
                    //user name is not in use -> we can continue
                    break;
                }
            }

            userBean.setScreenName(screenName);
            if (userBean.getScreenName() == null ||
                    userBean.getScreenName().isEmpty() ||
                    userBean.getEmail() == null ||
                    userBean.getEmail().isEmpty()) {
                ErrorPage
                        .error(AuthenticationError.CREDENTIALS.getMessage())
                        .flashAndRedirect(request, response, SsoEndpoint.REGISTER_OR_LOGIN.getUrl());
            } else {
                loginRegisterService
                    .completeRegistration(request, response, userBean, redirectUrl, true);
                //completeRegistration already sends a redirect -> return to avoid sending another
                return;
            }
        }
        response.sendRedirect(SingleSignOnController.REGISTER_OR_LOGIN_URL);
    }

    private void updateUserWithFBId(Member member, long fbId) {
        member.setFacebookId(fbId);
        MembersClient.updateMember(member);
    }

    private void updateUserAccountInformation(Member member, JSONObject jsonObject)
            throws JSONException {
        String country = member.getCountry();

        if (StringUtils.isEmpty(country)) {
            member.setCountry(getCountry(jsonObject));
        }
        MembersClient.updateMember(member);
    }

    private String getCountry(JSONObject response) {
        try {
            return getCountryFromLocaleObject(response);
        } catch (UserLocationNotResolvableException e) {
            return null;
        }
    }

    private String getCountryFromLocaleObject(JSONObject response) throws UserLocationNotResolvableException , JSONException  {

        final String localeString = response.optString("locale");
        if (StringUtils.isNotEmpty(localeString)) {
            Locale locale = LocaleUtils.toLocale(localeString.replace("-", "_"));
            return locale.getCountry();
        }

        throw new UserLocationNotResolvableException("Could not retrieve country from Facebook locale");
    }
}
