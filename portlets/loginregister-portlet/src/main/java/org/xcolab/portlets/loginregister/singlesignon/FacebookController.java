package org.xcolab.portlets.loginregister.singlesignon;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.facebook.FacebookConnectUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.loginregister.CreateUserBean;
import org.xcolab.portlets.loginregister.ImageUploadUtils;
import org.xcolab.portlets.loginregister.MainViewController;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@RequestMapping(value = "view", params = "SSO=facebook")
public class FacebookController {

    private static final String FB_PROFILE_PIC_URL_FORMAT_STRING = "https://graph.facebook.com/%d/?fields=picture.type(large)";

    @RequestMapping(params = "action=initiateLogin")
    public void initiateFbLogin(ActionRequest request, ActionResponse response) throws IOException, SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
        HttpSession session = httpReq.getSession();

        // Set property and do redirect
        session.setAttribute(MainViewController.SSO_TARGET_KEY, MainViewController.SSO_TARGET_LOGIN);

        initiateFbRequest(request, response);
    }

    @RequestMapping(params = "action=initiateRegistration")
    public void initiateFbRegistration(ActionRequest request, ActionResponse response) throws IOException, SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
        HttpSession session = httpReq.getSession();

        // Set property and do redirect
        session.setAttribute(MainViewController.SSO_TARGET_KEY, MainViewController.SSO_TARGET_REGISTRATION);

        initiateFbRequest(request, response);
    }

    private void initiateFbRequest(ActionRequest request, ActionResponse response) throws SystemException, IOException {
        HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
        HttpSession session = httpReq.getSession();

        String referrer = httpReq.getHeader("referer");
        session.setAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY, referrer);

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String facebookAuthRedirectURL = FacebookConnectUtil.getRedirectURL(themeDisplay.getCompanyId());
        facebookAuthRedirectURL = HttpUtil.addParameter(facebookAuthRedirectURL, "redirect", HttpUtil.encodeURL(themeDisplay.getURLCurrent().toString()));
        String facebookAuthURL = FacebookConnectUtil.getAuthURL(themeDisplay.getCompanyId());
        facebookAuthURL = HttpUtil.addParameter(facebookAuthURL, "client_id", FacebookConnectUtil.getAppId(themeDisplay.getCompanyId()));
        facebookAuthURL = HttpUtil.addParameter(facebookAuthURL, "redirect_uri", facebookAuthRedirectURL);
        facebookAuthURL = HttpUtil.addParameter(facebookAuthURL, "scope", "email");

        response.sendRedirect(facebookAuthURL);
    }

    @RequestMapping(params = "action=fbCallback")
    public void fbCallback(ActionRequest request, ActionResponse response) throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
        HttpSession session = httpReq.getSession();
        String redirectUrl = (String)session.getAttribute(MainViewController.PRE_LOGIN_REFERRER_KEY);


        String redirect = ParamUtil.getString(request, "redirect");
        String code = ParamUtil.getString(request, "code");
        String token=null;
        try{
            token = FacebookConnectUtil.getAccessToken(
                    themeDisplay.getCompanyId(), redirect, code);
        } catch (Exception e) { e.printStackTrace(); }

        JSONObject jsonObject = FacebookConnectUtil.getGraphResources(
                themeDisplay.getCompanyId(), "/me", token,
                "id,email,first_name,last_name,gender,verified");

        if ((jsonObject == null) || (jsonObject.getJSONObject("error") != null)){
            response.setRenderParameter("error", "true");
            response.setRenderParameter("SSO", "general");
            request.setAttribute("error","No data received.");
            return;
        }

        if (FacebookConnectUtil.isVerifiedAccountRequired(themeDisplay.getCompanyId()) && !jsonObject.getBoolean("verified")){
            response.setRenderParameter("error", "true");
            response.setRenderParameter("SSO", "general");
            request.setAttribute("error","Verified account required.");
            return;
        }


        User user = null;

        long facebookId = jsonObject.getLong("id");
        String fbProfilePictureURL = String.format(FB_PROFILE_PIC_URL_FORMAT_STRING, facebookId);

        PortletSession portletSession = request.getPortletSession();


        if (facebookId > 0) {
            // SSO Attribute
            portletSession.setAttribute(SSOKeys.FACEBOOK_USER_ID, String.valueOf(facebookId),PortletSession.APPLICATION_SCOPE);

            try {
                user = UserLocalServiceUtil.getUserByFacebookId(
                        themeDisplay.getCompanyId(), facebookId);

                String realPictureURLString = getFacebookPictureURLString(fbProfilePictureURL);
                if (realPictureURLString != null) {
                    ImageUploadUtils.updateProfilePicture(user, realPictureURLString);
                }
            }
            catch (NoSuchUserException e){

            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }


        // if email matches -> autologin
        String emailAddress = jsonObject.getString("email");
        if ((user == null) && Validator.isNotNull(emailAddress)) {
            try {
                user = UserLocalServiceUtil.getUserByEmailAddress(
                        themeDisplay.getCompanyId(), emailAddress);
                updateUserWithFBId(user,facebookId);

                String realPictureURLString = getFacebookPictureURLString(fbProfilePictureURL);
                if (realPictureURLString != null) {
                    ImageUploadUtils.updateProfilePicture(user, realPictureURLString);
                }

            }
            catch (NoSuchUserException e){

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (Validator.isNotNull(jsonObject.getString("first_name"))){
            portletSession.setAttribute(SSOKeys.SSO_FIRST_NAME, jsonObject.getString("first_name"),PortletSession.APPLICATION_SCOPE);
        }

        if (Validator.isNotNull(jsonObject.getString("last_name"))){
            portletSession.setAttribute(SSOKeys.SSO_LAST_NAME, jsonObject.getString("last_name"),PortletSession.APPLICATION_SCOPE);
        }
        if (Validator.isNotNull(jsonObject.getString("email"))){
            portletSession.setAttribute(SSOKeys.SSO_EMAIL, jsonObject.getString("email"),PortletSession.APPLICATION_SCOPE);
            // Screenname = email prefix until @ character
            String screenName = emailAddress.substring(0, emailAddress.indexOf(CharPool.AT));
            screenName = screenName.replaceAll("[^a-zA-Z0-9]","");
            portletSession.setAttribute(SSOKeys.SSO_SCREEN_NAME, screenName, PortletSession.APPLICATION_SCOPE);
        }

        // Get the FB image url
        if (facebookId > 0) {
            String realPictureURLString = getFacebookPictureURLString(fbProfilePictureURL);
            if (realPictureURLString != null) {
                long imageId = ImageUploadUtils.linkProfilePicture(getFacebookPictureURLString(fbProfilePictureURL));
                portletSession.setAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID, Long.toString(imageId), PortletSession.APPLICATION_SCOPE);
            }
        }

        if (user != null) {
            response.sendRedirect(redirectUrl);
        }
        else {
            // Finish registration
            if (portletSession.getAttribute(MainViewController.SSO_TARGET_KEY).equals(MainViewController.SSO_TARGET_REGISTRATION)) {
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
                        User duplicateUser = UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(), screenName);
                        // Generate a random suffix for the non-unique screenName
                        screenName = userBean.getScreenName().concat(RandomStringUtils.random(4, false, true));
                    } catch (PortalException e) {
                        break;
                    }
                }

                userBean.setScreenName(screenName);

                MainViewController.completeRegistration(request, response, userBean, redirectUrl, true);
            } else {
                response.setRenderParameter("status", "registerOrLogin");
                response.setRenderParameter("SSO", "general");
                request.setAttribute("credentialsError",false);
            }
        }
    }

    @RequestMapping(params = "status=registerOrLogin")
    public String registerOrLogin(PortletRequest request) {
        return "SSO/registerOrLogin";
    }

    private void updateUserWithFBId(User u, long fbId) throws SystemException, PortalException{
        u.setFacebookId(fbId);
        UserLocalServiceUtil.updateUser(u);
    }

    /**
     * Get the real image URL
     * @return
     */
    private String getFacebookPictureURLString(String fbProfilePictureURL) {
        try {
            // Get real facebook image URL
            InputStream is = new URL(fbProfilePictureURL).openStream();
            StringWriter writer = new StringWriter();
            IOUtils.copy(is, writer, "UTF-8");
            String json = writer.toString();

            JSONObject fbJson = JSONFactoryUtil.createJSONObject(json);
            return fbJson.getJSONObject("picture").getJSONObject("data").getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
