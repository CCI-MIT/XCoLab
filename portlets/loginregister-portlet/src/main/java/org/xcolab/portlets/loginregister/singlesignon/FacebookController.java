package org.xcolab.portlets.loginregister.singlesignon;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.facebook.FacebookConnectUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(value = "view", params = "SSO=facebook")
public class FacebookController {

    @RequestMapping(params = "action=fbCallback")
    public void fbCallback(ActionRequest request, ActionResponse response) throws IOException {

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
            return;
        }
        //if (!jsonObject.getBoolean("verified")) return;


        User user = null;

        long facebookId = jsonObject.getLong("id");

        PortletSession portletSession = request.getPortletSession();


        if (facebookId > 0) {
            portletSession.setAttribute(SSOKeys.FACEBOOK_USER_ID, String.valueOf(facebookId),PortletSession.APPLICATION_SCOPE);

            try {
                user = UserLocalServiceUtil.getUserByFacebookId(
                        themeDisplay.getCompanyId(), facebookId);
                if (!updateUserWithFBId(user,facebookId)) {   // is this needed?
                    response.setRenderParameter("error", "true");
                    response.setRenderParameter("SSO", "general");
                    return;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }


        // if email matches -> autologin
        String emailAddress = jsonObject.getString("email");
        if ((user == null) && Validator.isNotNull(emailAddress)) {
            portletSession.setAttribute(SSOKeys.FACEBOOK_USER_EMAIL_ADDRESS, emailAddress,PortletSession.APPLICATION_SCOPE);

            try {
                user = UserLocalServiceUtil.getUserByEmailAddress(
                        themeDisplay.getCompanyId(), emailAddress);
                if (!updateUserWithFBId(user,facebookId)){
                    response.setRenderParameter("error", "true");
                    response.setRenderParameter("SSO", "general");
                    return;
                }
            }
            catch (Exception e) { e.printStackTrace(); }
        }

        if (Validator.isNotNull(jsonObject.getString("first_name"))){
            portletSession.setAttribute(SSOKeys.SSO_FIRST_NAME, jsonObject.getString("first_name"),PortletSession.APPLICATION_SCOPE);
        }

        if (Validator.isNotNull(jsonObject.getString("last_name"))){
            portletSession.setAttribute(SSOKeys.SSO_LAST_NAME, jsonObject.getString("last_name"),PortletSession.APPLICATION_SCOPE);
        }
        if (Validator.isNotNull(jsonObject.getString("email"))){
            portletSession.setAttribute(SSOKeys.SSO_EMAIL, jsonObject.getString("email"),PortletSession.APPLICATION_SCOPE);
        }

        if (user != null) {
            response.sendRedirect(themeDisplay.getPortalURL());
        }
        else {
            response.setRenderParameter("status", "registerOrLogin");
            response.setRenderParameter("SSO", "general");
        }
    }

    private boolean updateUserWithFBId(User u, long fbId){
        if (u.getFacebookId() != fbId){
            u.setFacebookId(fbId);
            try {
                UserLocalServiceUtil.updateUser(u);
            } catch (SystemException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
