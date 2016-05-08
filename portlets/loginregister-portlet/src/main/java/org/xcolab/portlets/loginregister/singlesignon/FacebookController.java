package org.xcolab.portlets.loginregister.singlesignon;

import com.ext.portlet.community.CommunityConstants;
import com.ext.portlet.service.LoginLogLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.portlets.loginregister.CreateUserBean;
import org.xcolab.portlets.loginregister.ImageUploadUtils;
import org.xcolab.portlets.loginregister.MainViewController;
import org.xcolab.portlets.loginregister.exception.UserLocationNotResolvableException;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "view", params = "SSO=facebook")
public class FacebookController {

    private final static Log _log = LogFactoryUtil.getLog(FacebookController.class);

    @RequestMapping(params = "action=initiateLogin")
    public void initiateFbLogin(ActionRequest request, ActionResponse response) throws IOException, SystemException {
        HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
        HttpSession session = httpReq.getSession();

        // Set property and do redirect
        session.setAttribute(MainViewController.SSO_TARGET_KEY, MainViewController.SSO_TARGET_LOGIN);

        initiateFbRequest(request, response);
    }

    private void initiateFbRequest(ActionRequest request, ActionResponse response) throws SystemException, IOException {
        HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
        HttpSession session = httpReq.getSession();

        String referrer = httpReq.getHeader("referer");
        session.setAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY, referrer);

        UriComponentsBuilder facebookAuthRedirectURL = UriComponentsBuilder
                .fromHttpUrl(FacebookUtil.REDIRECT_URL)
                //TODO: potentially replace by current URL
                .queryParam("redirect", URLEncoder.encode(referrer, "UTF-8"));

        UriComponentsBuilder facebookAuthURL = UriComponentsBuilder.fromHttpUrl(
                FacebookUtil.AUTH_URL)
                .queryParam("client_id",
                        ConfigurationAttributeKey.FACEBOOK_APPLICATION_ID.getStringValue())
                .queryParam("redirect_uri", facebookAuthRedirectURL.build().toString())
                .queryParam("scope", "email");

        response.sendRedirect(facebookAuthURL.build().toString());
    }

    @RequestMapping(params = "action=initiateRegistration")
    public void initiateFbRegistration(ActionRequest request, ActionResponse response)
            throws IOException, SystemException {
        HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
        HttpSession session = httpReq.getSession();

        // Set property and do redirect
        session.setAttribute(MainViewController.SSO_TARGET_KEY, MainViewController.SSO_TARGET_REGISTRATION);

        initiateFbRequest(request, response);
    }

    @RequestMapping(params = "action=fbCallback")
    public void fbCallback(ActionRequest request, ActionResponse response) throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
        HttpSession session = httpReq.getSession();

        session.removeAttribute(SSOKeys.SSO_OPENID_ID);

        String redirectUrl = (String) session.getAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY);
        if (Validator.isNull(redirectUrl) || Validator.isBlank(redirectUrl)) {
            redirectUrl = themeDisplay.getURLHome();
        }

        String redirect = ParamUtil.getString(request, "redirect");
        String code = ParamUtil.getString(request, "code");
        String token = FacebookUtil.getAccessToken(redirect, code);

        JSONObject jsonObject = FacebookUtil.getGraphResources("/me", token,
                "id,email,first_name,last_name,gender,verified,location,locale");

        if ((jsonObject == null) || (jsonObject.getJSONObject("error") != null)) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("SSO", "general");
            request.setAttribute("error", "No data received.");
            return;
        }

        if (ConfigurationAttributeKey.FACEBOOK_VERIFIED_REQUIRED.getBooleanValue()
                && !jsonObject.getBoolean("verified")) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("SSO", "general");
            request.setAttribute("error", "Verified account required.");
            return;
        }

        User liferayUser = null;

        long facebookId = jsonObject.getLong("id");
        String fbProfilePictureURL = String.format(FacebookUtil.FB_PROFILE_PIC_URL_FORMAT_STRING, facebookId);

        PortletSession portletSession = request.getPortletSession();

        if (facebookId > 0) {
            // SSO Attribute
            portletSession.setAttribute(SSOKeys.FACEBOOK_USER_ID, String.valueOf(facebookId),
                    PortletSession.APPLICATION_SCOPE);

            try {
                liferayUser = UserLocalServiceUtil.getUserByFacebookId(themeDisplay.getCompanyId(), facebookId);

                String realPictureURLString = FacebookUtil.getFacebookPictureURLString(fbProfilePictureURL);
                if (realPictureURLString != null) {
                    ImageUploadUtils.updateProfilePicture(liferayUser, realPictureURLString);
                }

                LoginLogLocalServiceUtil.createLoginLog(liferayUser, httpReq.getRemoteAddr(), redirectUrl);
                response.sendRedirect(redirectUrl);
                return;
            } catch (NoSuchUserException ignored) {
            } catch (PortalException | IOException e) {
                e.printStackTrace();
            }
        }

        // if email matches -> autologin
        String emailAddress = jsonObject.getString("email");
        if ((liferayUser == null) && Validator.isNotNull(emailAddress)) {
            try {
                liferayUser = UserLocalServiceUtil.getUserByEmailAddress(
                        themeDisplay.getCompanyId(), emailAddress);
                updateUserWithFBId(liferayUser, facebookId);

                String realPictureURLString = FacebookUtil.getFacebookPictureURLString(fbProfilePictureURL);
                if (realPictureURLString != null) {
                    ImageUploadUtils.updateProfilePicture(liferayUser, realPictureURLString);
                }

                updateUserAccountInformation(liferayUser, jsonObject);

                LoginLogLocalServiceUtil.createLoginLog(liferayUser, httpReq.getRemoteAddr(), redirectUrl);
                response.sendRedirect(redirectUrl);
                return;
            } catch (NoSuchUserException ignored) {
            } catch (PortalException | IOException e) {
                e.printStackTrace();
            }
        }

        if (Validator.isNotNull(jsonObject.getString("first_name"))) {
            portletSession.setAttribute(SSOKeys.SSO_FIRST_NAME, jsonObject.getString("first_name"),
                    PortletSession.APPLICATION_SCOPE);
        }

        if (Validator.isNotNull(jsonObject.getString("last_name"))) {
            portletSession.setAttribute(SSOKeys.SSO_LAST_NAME, jsonObject.getString("last_name"),
                    PortletSession.APPLICATION_SCOPE);
        }
        if (Validator.isNotNull(jsonObject.getString("email"))) {
            portletSession
                    .setAttribute(SSOKeys.SSO_EMAIL, jsonObject.getString("email"), PortletSession.APPLICATION_SCOPE);
            // Screenname = email prefix until @ character
            String screenName = emailAddress.substring(0, emailAddress.indexOf(CharPool.AT));
            screenName = screenName.replaceAll("[^a-zA-Z0-9]", "");
            portletSession.setAttribute(SSOKeys.SSO_SCREEN_NAME, screenName, PortletSession.APPLICATION_SCOPE);
        }

        try {
            portletSession.setAttribute(SSOKeys.SSO_COUNTRY, getCountry(jsonObject), PortletSession.APPLICATION_SCOPE);
        } catch (UserLocationNotResolvableException e) {
            portletSession.setAttribute(SSOKeys.SSO_COUNTRY, null, PortletSession.APPLICATION_SCOPE);
            _log.warn(e);
        }

        // Get the FB image url
        if (facebookId > 0) {
            String realPictureURLString = FacebookUtil.getFacebookPictureURLString(fbProfilePictureURL);
            if (realPictureURLString != null) {
                long imageId = ImageUploadUtils.linkProfilePicture(
                        FacebookUtil.getFacebookPictureURLString(fbProfilePictureURL));
                portletSession.setAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID, Long.toString(imageId),
                        PortletSession.APPLICATION_SCOPE);
            }
        }

        // Finish registration
        if (session.getAttribute(MainViewController.SSO_TARGET_KEY) != null &&
                session.getAttribute(MainViewController.SSO_TARGET_KEY)
                        .equals(MainViewController.SSO_TARGET_REGISTRATION)) {

            // append SSO attributes
            CreateUserBean userBean = new CreateUserBean();
            String password = RandomStringUtils.random(12, true, true);
            userBean.setPassword(password);
            userBean.setRetypePassword(password);

            MainViewController.getSSOUserInfo(request.getPortletSession(), userBean);

            // Validate uniqueness of the screen name
            // The chance of a collision among 40 equal screennames is 50% -> 5 tries should be sufficient
            String screenName = userBean.getScreenName();
            for (int i = 0; i < 5; i++) {
                try {
                    UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(), screenName);
                    //TODO: find better way to resolve conflicts
                    screenName = userBean.getScreenName().concat(RandomStringUtils.random(4, false, true));
                } catch (NoSuchUserException e) {
                    //user name is not in use -> we can continue
                    break;
                }
            }

            userBean.setScreenName(screenName);

            MainViewController.completeRegistration(request, response, userBean, redirectUrl, true);
        } else {
            response.setRenderParameter("status", "registerOrLogin");
            response.setRenderParameter("SSO", "general");
            request.setAttribute("credentialsError", false);
        }
    }

    @RequestMapping(params = "status=registerOrLogin")
    public String registerOrLogin(PortletRequest request) {
        return "SSO/registerOrLogin";
    }

    private void updateUserWithFBId(User u, long fbId) throws SystemException, PortalException {
        u.setFacebookId(fbId);
        UserLocalServiceUtil.updateUser(u);
    }

    private void updateUserAccountInformation(User user, JSONObject jsonObject) throws SystemException {
        try {
            Member member = MembersClient.getMember(user.getUserId());
            String country = member.getCountry();

            if (StringUtils.isEmpty(country)) {
                try {
                    member.setCountry(getCountry(jsonObject));
                } catch (UserLocationNotResolvableException ignored) { }
            }

            //TODO: do we need this?
            ExpandoValue city = getExpandoValue(user, CommunityConstants.CITY);

            if (Validator.isNull(city) || Validator.isNull(city.getString())) {
                try {
                    setExpandoValue(user, CommunityConstants.CITY, getCountry(jsonObject));

                } catch (UserLocationNotResolvableException ignored) {}
            }
            MembersClient.updateMember(member);
        } catch (PortalException | MemberNotFoundException e) {
            _log.error(e);
        }
    }

    private String getCountry(JSONObject response) throws UserLocationNotResolvableException {
        try {
            return getCountryFromLocationObject(response);
        } catch (UserLocationNotResolvableException e) {
            return getCountryFromLocaleObject(response);
        }
    }

    private String getCountryFromLocationObject(JSONObject response) throws UserLocationNotResolvableException {
        if (Validator.isNotNull(response.getJSONObject("location"))) {
            String locationString = response.getJSONObject("location").getString("name");

            if (locationString.contains(",")) {
                return locationString.split(",")[1].trim();
            }
        }

        throw new UserLocationNotResolvableException("Could not retrieve country from Facebook location");
    }

    private String getCountryFromLocaleObject(JSONObject response) throws UserLocationNotResolvableException {
        if (Validator.isNotNull(response.getString("locale"))) {
            String[] localeParts = response.getString("locale").split("_");
            Locale locale = new Locale(localeParts[0], localeParts[1]);

            return locale.getDisplayCountry();
        }

        throw new UserLocationNotResolvableException("Could not retrieve country from Facebook locale");
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

    private ExpandoValue getExpandoValue(User user, String valueName) throws SystemException {
        return ExpandoValueLocalServiceUtil.getValue(
                user.getCompanyId(),
                User.class.getName(),
                CommunityConstants.EXPANDO,
                valueName,
                user.getUserId());
    }
}
