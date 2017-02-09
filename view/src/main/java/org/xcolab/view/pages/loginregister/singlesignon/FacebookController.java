package org.xcolab.view.pages.loginregister.singlesignon;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.flash.ErrorMessage;
import org.xcolab.entity.utils.portlet.RequestParamUtil;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.view.auth.AuthenticationContext;
import org.xcolab.view.auth.login.AuthenticationError;
import org.xcolab.view.pages.loginregister.CreateUserBean;
import org.xcolab.view.pages.loginregister.MainViewController;
import org.xcolab.view.pages.loginregister.exception.UserLocationNotResolvableException;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/sso/facebook")
public class FacebookController {

    private final static Logger _log = LoggerFactory.getLogger(FacebookController.class);

    private final AuthenticationContext authenticationContext;

    @Autowired
    public FacebookController(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    @GetMapping("login")
    public void initiateFbLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute(MainViewController.SSO_TARGET_KEY,
                MainViewController.SSO_TARGET_LOGIN);
        initiateFbRequest(request, response);
    }

    @GetMapping("register")
    public void initiateFbRegistration(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute(MainViewController.SSO_TARGET_KEY,
                MainViewController.SSO_TARGET_REGISTRATION);
        initiateFbRequest(request, response);
    }

    private void initiateFbRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();

        String referrer = request.getHeader(HttpHeaders.REFERER);
        session.setAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY, referrer);

        //TODO: potentially replace by current URL
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
                .getAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY);
        if ((redirectUrl) == null || (redirectUrl.isEmpty())) {
            redirectUrl = "/";
        }

        String code = RequestParamUtil.getString(request, "code");
        String token = FacebookUtil.getAccessToken(request, code);

        JSONObject jsonObject = FacebookUtil.getGraphResources("/me", token,
                "id,email,first_name,last_name,gender,verified,location,locale");

        if (jsonObject == null || jsonObject.optJSONObject("error") != null) {
            ErrorMessage.error("No data received from Facebook")
                    .flashAndRedirect(request, response);
            return;
        }

        if (ConfigurationAttributeKey.FACEBOOK_VERIFIED_REQUIRED.get()
                && !jsonObject.getBoolean("verified")) {
            ErrorMessage
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

                authenticationContext.authenticate(request, member);
                MembersClient.createLoginLog(member.getUserId(), request.getRemoteAddr(), redirectUrl);
                response.sendRedirect(redirectUrl);
                return;
            } catch (MemberNotFoundException ignored) {
            } catch (IOException e) {
                throw new InternalException(e);
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

                authenticationContext.authenticate(request, member);
                MembersClient.createLoginLog(member.getUserId(), request.getRemoteAddr(), redirectUrl);
                response.sendRedirect(redirectUrl);
                return;
            } catch (MemberNotFoundException ignored) {
            } catch (IOException e) {
                throw new InternalException(e);
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

        try {
            session.setAttribute(SSOKeys.SSO_COUNTRY, getCountry(jsonObject));
        } catch (UserLocationNotResolvableException e) {
            session.setAttribute(SSOKeys.SSO_COUNTRY, null);
            _log.warn("", e);
        }

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
        if (session.getAttribute(MainViewController.SSO_TARGET_KEY) != null &&
                session.getAttribute(MainViewController.SSO_TARGET_KEY)
                        .equals(MainViewController.SSO_TARGET_REGISTRATION)) {

            // append SSO attributes
            CreateUserBean userBean = new CreateUserBean();
            String password = RandomStringUtils.random(12, true, true);
            userBean.setPassword(password);
            userBean.setRetypePassword(password);

            MainViewController.getSSOUserInfo(request.getSession(), userBean);

            // Validate uniqueness of the screen name
            // The chance of a collision among 40 equal screennames is 50% -> 5 tries should be sufficient
            String screenName = userBean.getScreenName();
            for (int i = 0; i < 5; i++) {
                try {
                    MembersClient.findMemberByScreenName(screenName);
                    //TODO: find better way to resolve conflicts
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
                ErrorMessage
                        .error(AuthenticationError.CREDENTIALS.getMessage())
                        .flashAndRedirect(request, response, SsoEndpoint.REGISTER_OR_LOGIN.getUrl());
            } else {
                MainViewController.completeRegistration(request, response, userBean, redirectUrl, true);
            }
        } else {
            ErrorMessage
                    .error(AuthenticationError.UNKNOWN.getMessage())
                    .flashAndRedirect(request, response, SsoEndpoint.REGISTER_OR_LOGIN.getUrl());
        }
    }

    private void updateUserWithFBId(Member member, long fbId) {
        member.setFacebookId(fbId);
        MembersClient.updateMember(member);
    }

    private void updateUserAccountInformation(Member member, JSONObject jsonObject)
            throws JSONException {
        String country = member.getCountry();

        if (StringUtils.isEmpty(country)) {
            try {
                member.setCountry(getCountry(jsonObject));
            } catch (UserLocationNotResolvableException ignored) {
            }
        }
        MembersClient.updateMember(member);
    }

    private String getCountry(JSONObject response) throws UserLocationNotResolvableException, JSONException {
        try {
            return getCountryFromLocationObject(response);
        } catch (UserLocationNotResolvableException e) {
            return getCountryFromLocaleObject(response);
        }
    }

    private String getCountryFromLocationObject(JSONObject response) throws UserLocationNotResolvableException, JSONException {

        final JSONObject location = response.optJSONObject("location");
        if (location != null) {
            String locationString = location.getString("name");

            if (locationString.contains(",")) {
                return locationString.split(",")[1].trim();
            }
        }

        throw new UserLocationNotResolvableException("Could not retrieve country from Facebook location");
    }

    private String getCountryFromLocaleObject(JSONObject response) throws UserLocationNotResolvableException , JSONException  {

        final String localeString = response.optString("locale");
        if (localeString != null) {
            String[] localeParts = localeString.split("_");
            Locale locale = new Locale(localeParts[0], localeParts[1]);

            return locale.getDisplayCountry();
        }

        throw new UserLocationNotResolvableException("Could not retrieve country from Facebook locale");
    }
}
