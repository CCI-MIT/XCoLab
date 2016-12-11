package org.xcolab.portlets.loginregister.singlesignon;

import com.ext.utils.authentication.service.AuthenticationServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.Authenticator;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

@Controller
@RequestMapping(value = "view", params = "SSO=general")
public class SingleSignOnController {

    private final static Logger _log = LoggerFactory.getLogger(SingleSignOnController.class);

    @RequestMapping(params = "action=provideSSOCredentials")
    public void linkUser(ActionRequest request, Model model, ActionResponse response,
            @RequestParam String login, @RequestParam String password) throws PortalException,
            SystemException, IOException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User u;
        try {
            u = UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(), login);
        } catch (NoSuchUserException nsu) {
            // username incorrect
            response.setRenderParameter("status", "registerOrLogin");
            response.setRenderParameter("SSO", "general");
            request.setAttribute("credentialsError", true);
            return;
        }

        PortletSession portletSession = request.getPortletSession();

        try {
            Map<String, Object> resultsMap = new HashMap<>();
            // Use local authentication API to check credentials
            int success = UserLocalServiceUtil
                    .authenticateByScreenName(themeDisplay.getCompanyId(), login, password, null,
                            request.getParameterMap(), resultsMap);
            if (success == Authenticator.SUCCESS) {
                // Do the actual login
                AuthenticationServiceUtil.logUserIn(request, response, login, password);

                // Do the linkage of OpenID or Facebook ID
                u = UserLocalServiceUtil.getUser(MapUtil.getLong(resultsMap, "userId"));
                String fbIdString = (String) portletSession
                        .getAttribute(SSOKeys.FACEBOOK_USER_ID, PortletSession.APPLICATION_SCOPE);
                String openId =
                        (String) portletSession.getAttribute(SSOKeys.SSO_OPENID_ID, PortletSession.APPLICATION_SCOPE);
                String profileImageId = (String) portletSession
                        .getAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID, PortletSession.APPLICATION_SCOPE);

                if (Validator.isNotNull(profileImageId) && u.getPortraitId() == 0) {
                    long id = GetterUtil.getLong(profileImageId);
                    u.setPortraitId(id);
                    UserLocalServiceUtil.updateUser(u);
                }
                if (Validator.isNotNull(fbIdString)) {
                    // update FB credentials
                    long fbId = Long.parseLong(fbIdString);
                    u.setFacebookId(fbId);
                    UserLocalServiceUtil.updateUser(u);
                    response.sendRedirect(themeDisplay.getURLHome());
                    return;
                }
                if (Validator.isNotNull(openId)) {
                    u.setOpenId(openId);
                    UserLocalServiceUtil.updateUser(u);
                    portletSession.setAttribute("OPEN_ID_LOGIN", u.getUserId(), PortletSession.APPLICATION_SCOPE);
                    response.sendRedirect(themeDisplay.getURLHome());
                    return;
                }
                response.setRenderParameter("error", "true");
                response.setRenderParameter("SSO", "general");
                request.setAttribute("error", "An unknown error occured.");

                return;
            }

        } catch (Exception e) {
            _log.error("Linking user {} failed:", login, e);
        }

        // passwords don't match
        response.setRenderParameter("status", "registerOrLogin");
        response.setRenderParameter("SSO", "general");
        request.setAttribute("credentialsError", true);
    }

    @RequestMapping(params = "error=true")
    public String registerError(PortletRequest request) {
        return "SSO/error";
    }

    @RequestMapping(params = "status=registerOrLogin")
    public String registerOrLogin(PortletRequest request, Model model) {
        model.addAttribute("colabName", ConfigurationAttributeKey.COLAB_NAME.get());
        model.addAttribute("colabShortName", ConfigurationAttributeKey.COLAB_SHORT_NAME.get());
        return "SSO/registerOrLogin";
    }
}
