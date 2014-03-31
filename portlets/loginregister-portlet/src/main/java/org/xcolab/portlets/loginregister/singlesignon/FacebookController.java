package org.xcolab.portlets.loginregister.singlesignon;

import com.google.api.client.http.HttpRequest;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.facebook.FacebookConnectUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.loginregister.ImageUploadUtils;

import javax.imageio.ImageIO;
import javax.portlet.*;
import javax.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

@Controller
@RequestMapping(value = "view", params = "SSO=facebook")
public class FacebookController {

    private static final String FB_PROFILE_PIC_URL_FORMAT_STRING = "http://graph.facebook.com/%d/?fields=picture&type=large";

    @RequestMapping(params = "action=fbCallback")
    public void fbCallback(ActionRequest request, ActionResponse response) throws IOException, SystemException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

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
            response.sendRedirect(themeDisplay.getPortalURL());
        }
        else {
            response.setRenderParameter("status", "registerOrLogin");
            response.setRenderParameter("SSO", "general");
            request.setAttribute("credentialsError",false);
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
