package org.xcolab.portlets.loginregister.singlesignon;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ParamUtil;
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
import org.xcolab.portlets.loginregister.MainViewController;
import org.xcolab.portlets.loginregister.exception.UserLocationNotResolvableException;
import org.xcolab.util.exceptions.InternalException;

import java.io.IOException;
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

    private final static Logger _log = LoggerFactory.getLogger(FacebookController.class);

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

        String redirectUrl = (String) session
                .getAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY);
        if (Validator.isNull(redirectUrl) || Validator.isBlank(redirectUrl)) {
            redirectUrl = themeDisplay.getURLHome();
        }

        String code = ParamUtil.getString(request, "code");
        String token = FacebookUtil.getAccessToken(request, code);

        JSONObject jsonObject = FacebookUtil.getGraphResources("/me", token,
                "id,email,first_name,last_name,gender,verified,location,locale");

        if ((jsonObject == null) || (jsonObject.getJSONObject("error") != null)) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("SSO", "general");
            request.setAttribute("error", "No data received.");
            return;
        }

        if (ConfigurationAttributeKey.FACEBOOK_VERIFIED_REQUIRED.get()
                && !jsonObject.getBoolean("verified")) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("SSO", "general");
            request.setAttribute("error", "Verified account required.");
            return;
        }

        User liferayUser = null;

        long facebookId = jsonObject.getLong("id");
        String fbProfilePictureURL = String
                .format(FacebookUtil.FB_PROFILE_PIC_URL_FORMAT_STRING, facebookId);


        PortletSession portletSession = request.getPortletSession();

        if (facebookId > 0) {
            // SSO Attribute
            portletSession.setAttribute(SSOKeys.FACEBOOK_USER_ID, String.valueOf(facebookId),
                    PortletSession.APPLICATION_SCOPE);

            try {
                liferayUser = UserLocalServiceUtil.getUserByFacebookId(themeDisplay.getCompanyId(), facebookId);

                /*
                String realPictureURLString = FacebookUtil.getFacebookPictureURLString(fbProfilePictureURL);
                if (realPictureURLString != null) {
                    String path = httpReq.getSession().getServletContext().getRealPath("/");
                    ImageUploadUtils.updateProfilePicture(path, liferayUser, realPictureURLString);
                }*/

                MembersClient.createLoginLog(liferayUser.getUserId(), httpReq.getRemoteAddr(), redirectUrl);
                response.sendRedirect(redirectUrl);
                return;
            } catch (NoSuchUserException ignored) {
            } catch (PortalException | IOException e) {
                throw new InternalException(e);
            }
        }

        // if email matches -> autologin
        String emailAddress = jsonObject.getString("email");
        if ((liferayUser == null) && Validator.isNotNull(emailAddress)) {
            try {
                liferayUser = UserLocalServiceUtil.getUserByEmailAddress(
                        themeDisplay.getCompanyId(), emailAddress);
                updateUserWithFBId(liferayUser, facebookId);

                /*
                String realPictureURLString = FacebookUtil.getFacebookPictureURLString(fbProfilePictureURL);
                if (realPictureURLString != null) {
                    String path = httpReq.getSession().getServletContext().getRealPath("/");
                    ImageUploadUtils.updateProfilePicture(path, liferayUser, realPictureURLString);
                }*/

                updateUserAccountInformation(liferayUser, jsonObject);

                MembersClient.createLoginLog(liferayUser.getUserId(), httpReq.getRemoteAddr(), redirectUrl);
                response.sendRedirect(redirectUrl);
                return;
            } catch (NoSuchUserException ignored) {
            } catch (PortalException | IOException e) {
                throw new InternalException(e);
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
            if (userBean.getScreenName() == null ||
                    userBean.getScreenName().isEmpty() ||
                    userBean.getEmail() == null ||
                    userBean.getEmail().isEmpty()) {
                response.setRenderParameter("status", "registerOrLogin");
                response.setRenderParameter("SSO", "general");
                request.setAttribute("credentialsError", true);
            } else {

                MainViewController.completeRegistration(request, response, userBean, redirectUrl, true);
            }
        } else {
            response.setRenderParameter("status", "registerOrLogin");
            response.setRenderParameter("SSO", "general");
            request.setAttribute("credentialsError", false);
        }
    }

    @RequestMapping(params = "status=registerOrLogin")
    public String registerOrLogin(PortletRequest request, Model model) {
        model.addAttribute("colabName", ConfigurationAttributeKey.COLAB_NAME.get());
        model.addAttribute("colabShortName", ConfigurationAttributeKey.COLAB_SHORT_NAME.get());
        return "SSO/registerOrLogin";
    }

    private void updateUserWithFBId(User u, long fbId) throws SystemException, PortalException {
        u.setFacebookId(fbId);
        UserLocalServiceUtil.updateUser(u);
    }

    private void updateUserAccountInformation(User user, JSONObject jsonObject) throws SystemException {
        Member member = MembersClient.getMemberUnchecked(user.getUserId());
        String country = member.getCountry();

        if (StringUtils.isEmpty(country)) {
            try {
                member.setCountry(getCountry(jsonObject));
            } catch (UserLocationNotResolvableException ignored) {
            }
        }
        MembersClient.updateMember(member);
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
}
