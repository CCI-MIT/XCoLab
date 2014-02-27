package org.xcolab.portlets.loginregister.singlesignon;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.pwd.BasePasswordEncryptor;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PropsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.*;
import java.io.IOException;

@Controller
@RequestMapping(value = "view", params = "SSO=general")
public class SingleSignOnController {

    private final static Log _log = LogFactoryUtil.getLog(SingleSignOnController.class);

    private static final String SHA_ALGORITHM_PREFIX = "{SHA-1}";

    @RequestMapping(params = "action=provideSSOCredentials")
    public void linkUser(ActionRequest request, Model model, ActionResponse response,
                             @RequestParam String login, @RequestParam String password) throws PortalException,
                                SystemException, IOException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User u = null;
        try{
            u = UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(),login);
        } catch (NoSuchUserException nsu){
            // username incorrect
            response.setRenderParameter("status", "registerOrLogin");
            response.setRenderParameter("SSO", "general");
            request.setAttribute("credentialsError",true);
            return;
        }

        String encrAlgorithm = PropsUtil.get(PropsKeys.PASSWORDS_ENCRYPTION_ALGORITHM);
        _log.info("Encryption algorithm: " + encrAlgorithm);

        // Statically instantiate the PBKDF2 pw encryptor. Please change this when Liferay decides to change the password encryption
        // scheme again...
        String encrPassword = u.getPassword();

        PasswordEncryptor encryptor;
        // SHA hash
        if (encrPassword.contains(SHA_ALGORITHM_PREFIX)) {
            final int startIdx = encrPassword.indexOf(SHA_ALGORITHM_PREFIX);
            encrPassword = encrPassword.substring(startIdx + SHA_ALGORITHM_PREFIX.length());

            encryptor = new SSHAPasswordEncryptor();
        } else {
            encryptor = new PBKDF2PasswordEncryptor();
        }

        _log.error("encr. password: " + u.getPassword());
        String encPassword = encryptor.encrypt(password, u.getPassword());

        // passwords don't match
        if (!encPassword.equalsIgnoreCase(u.getPassword())) {
            response.setRenderParameter("status", "registerOrLogin");
            response.setRenderParameter("SSO", "general");
            request.setAttribute("credentialsError",true);
            return;
        }

        PortletSession portletSession = request.getPortletSession();

        String fbIdString = (String) portletSession.getAttribute("FACEBOOK_USER_ID",PortletSession.APPLICATION_SCOPE);
        String openId = (String) portletSession.getAttribute("SSO_OPENID_ID",PortletSession.APPLICATION_SCOPE);

        if (Validator.isNotNull(fbIdString)){
            // update FB credentials
                long fbId = Long.parseLong(fbIdString);
                u.setFacebookId(fbId);
                UserLocalServiceUtil.updateUser(u);
                response.sendRedirect(themeDisplay.getURLHome());
        } else if (Validator.isNotNull(openId)){
            u.setOpenId(openId);
            UserLocalServiceUtil.updateUser(u);
            portletSession.setAttribute("OPEN_ID_LOGIN",new Long(u.getUserId()), PortletSession.APPLICATION_SCOPE);
            response.sendRedirect(themeDisplay.getURLHome());
        }
        response.setRenderParameter("error", "true");
        response.setRenderParameter("SSO", "general");
        request.setAttribute("error","An unknown error occured.");
    }

    @RequestMapping(params = "error=true")
    public String registerError(PortletRequest request) {
        return "SSO/error";
    }

    @RequestMapping(params = "status=registerOrLogin")
    public String registerOrLogin(PortletRequest request) {
        return "SSO/registerOrLogin";
    }

}
