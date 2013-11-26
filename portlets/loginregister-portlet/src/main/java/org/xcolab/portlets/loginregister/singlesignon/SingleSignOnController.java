package org.xcolab.portlets.loginregister.singlesignon;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 22/11/13
 * Time: 11:33
 * To change this template use File | Settings | File Templates.
 */

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.facebook.FacebookConnectUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import com.liferay.portal.kernel.util.Validator;
import org.xcolab.commons.utils.PwdEncryptor;
import org.xcolab.portlets.loginregister.CreateUserBean;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class SingleSignOnController {

    private static long DEFAULT_COMPANY_ID = 10112L;

    @RequestMapping(value = "view", params = "SSO=facebook")
    public String fbcallback(PortletRequest request, PortletResponse response) {

        String redirect = ParamUtil.getString(request, "redirect");
        String code = ParamUtil.getString(request, "code");
        String token=null;
        try{
            token = FacebookConnectUtil.getAccessToken(
                    DEFAULT_COMPANY_ID, redirect, code);
        } catch (Exception e) { e.printStackTrace(); }

        JSONObject jsonObject = FacebookConnectUtil.getGraphResources(
                DEFAULT_COMPANY_ID, "/me", token,
                "id,email,first_name,last_name,gender,verified");

        if ((jsonObject == null) || (jsonObject.getJSONObject("error") != null)) return "SSO/error";
        //if (!jsonObject.getBoolean("verified")) return;


        User user = null;

        long facebookId = jsonObject.getLong("id");



        HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

        HttpSession session = httpServletRequest.getSession();

        PortletSession portletSession = request.getPortletSession();


        if (facebookId > 0) {
            portletSession.setAttribute("FACEBOOK_USER_ID", String.valueOf(facebookId),PortletSession.APPLICATION_SCOPE);

            try {
                user = UserLocalServiceUtil.getUserByFacebookId(
                        DEFAULT_COMPANY_ID, facebookId);
                if (!updateUserWithFBId(user,facebookId)) return "SSO/error";
            }
            catch (Exception e) { e.printStackTrace(); }
        }


        // if email matches -> autologin
        String emailAddress = jsonObject.getString("email");
        if ((user == null) && Validator.isNotNull(emailAddress)) {
            portletSession.setAttribute("FACEBOOK_USER_EMAIL_ADDRESS", emailAddress,PortletSession.APPLICATION_SCOPE);

            try {
                user = UserLocalServiceUtil.getUserByEmailAddress(
                        DEFAULT_COMPANY_ID, emailAddress);
                if (!updateUserWithFBId(user,facebookId)) return "SSO/error"; // update fbId
            }
            catch (Exception e) { e.printStackTrace(); }
        }

        if (Validator.isNotNull(jsonObject.getString("first_name"))){
            portletSession.setAttribute("FACEBOOK_FIRST_NAME", jsonObject.getString("first_name"),PortletSession.APPLICATION_SCOPE);
        }

        if (Validator.isNotNull(jsonObject.getString("last_name"))){
            portletSession.setAttribute("FACEBOOK_LAST_NAME", jsonObject.getString("last_name"),PortletSession.APPLICATION_SCOPE);
        }
        if (Validator.isNotNull(jsonObject.getString("email"))){
            portletSession.setAttribute("FACEBOOK_EMAIL", jsonObject.getString("email"),PortletSession.APPLICATION_SCOPE);
        }


        if (user != null) {
            return "SSO/autoLoginSuccessful";

        }
        else {
            return "SSO/redirectToRegisterOrLogin";
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


    @RequestMapping(value = "view", params = "action=provideSSOCredentials")
    public void registerUser(ActionRequest request, Model model, ActionResponse response,
                             @RequestParam String login, @RequestParam String password) throws PortalException,SystemException{

        User u = null;
        try{
            u = UserLocalServiceUtil.getUserByScreenName(DEFAULT_COMPANY_ID,login);
        } catch (NoSuchUserException nsu){
            // username incorrect
            response.setRenderParameter("error", "true");
            response.setRenderParameter("SSO", "facebook");
            return;
        }

        // username incorrect
        if (u == null){
            response.setRenderParameter("error", "true");
            response.setRenderParameter("SSO", "facebook");
            return;
        }

        String encPassword = PwdEncryptor.encrypt(password);

        // passwords don't match
        if (!encPassword.equalsIgnoreCase(u.getPassword())) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("SSO", "facebook");
            return;
        }

        PortletSession portletSession = request.getPortletSession();

        String fbIdString = (String) portletSession.getAttribute("FACEBOOK_USER_ID",PortletSession.APPLICATION_SCOPE);
        try{
            long fbId = Long.parseLong(fbIdString);
            u.setFacebookId(fbId);
            UserLocalServiceUtil.updateUser(u);
            ThemeDisplay themeDisplay = (ThemeDisplay) request
                    .getAttribute(WebKeys.THEME_DISPLAY);
            response.sendRedirect(themeDisplay.getURLHome());
        } catch (Exception e){
            // fb id is not numeric
            response.setRenderParameter("error", "true");
            response.setRenderParameter("SSO", "facebook");
            return;
        }
    }

    @RequestMapping(value = "view", params = {"SSO=facebook" , "login=successful"})
    public String autoLogin(PortletRequest request, PortletResponse response) {
        return "SSO/autoLoginSuccessful";
    }

    @RequestMapping(value = "view", params = {"SSO=facebook" , "error=true"})
    public String registerError(PortletRequest request) {
        return "SSO/error";
    }

    @RequestMapping(value = "view", params = {"SSO=facebook" , "status=registerOrLogin"})
    public String registerOrLogin(PortletRequest request) {
        return "SSO/registerOrLogin";
    }

}
