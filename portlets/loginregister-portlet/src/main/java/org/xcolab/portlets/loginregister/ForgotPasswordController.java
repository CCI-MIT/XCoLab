package org.xcolab.portlets.loginregister;

import com.ext.utils.authentication.service.AuthenticationServiceUtil;
import com.liferay.portal.CookieNotSupportedException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PasswordExpiredException;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.UserIdException;
import com.liferay.portal.UserLockoutException;
import com.liferay.portal.UserPasswordException;
import com.liferay.portal.UserScreenNameException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.xcolab.utils.GlobalMessagesUtil;
import org.xcolab.utils.ModelAttributeUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "view")
public class ForgotPasswordController {

    private static final long DEFAULT_COMPANY_ID = 10112L;

    @ActionMapping(params = {"isForgotpass=true"})
    public void sendPassword(ActionRequest request, ActionResponse response) throws IOException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(
                WebKeys.THEME_DISPLAY);

        HttpServletRequest httpRequest = PortletUtils.getOryginalRequest(request);

        String redirect = httpRequest.getParameter("redirect");
        String referer = httpRequest.getHeader("referer");
        redirect = !StringUtils.isBlank(redirect) ? redirect : referer;

        redirect = !StringUtils.isBlank(redirect) ? redirect : themeDisplay.getURLHome();

        redirect = Helper.removeParamFromRequestStr(redirect, "signinRegError");
        redirect = Helper.removeParamFromRequestStr(redirect, "isPasswordReminder");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSigningIn");
        redirect = Helper.removeParamFromRequestStr(redirect, "isRegistering");

        try {

            PortletPreferences preferences = request.getPreferences();
            String userNameEmail = request.getParameter("screenName");

            User user;
            if (userNameEmail.contains("@")) {
                user = UserLocalServiceUtil.getUserByEmailAddress(DEFAULT_COMPANY_ID, userNameEmail);
            } else {
                user = UserLocalServiceUtil.getUserByScreenName(DEFAULT_COMPANY_ID, userNameEmail);
            }

            String languageId = LanguageUtil.getLanguageId(request);

            String emailFromName = preferences.getValue("emailFromName", null);
            String emailFromAddress = preferences.getValue(
                    "emailFromAddress", null);
            String emailToAddress = user.getEmailAddress();

            String emailParam = "emailPasswordSent";

            String subject = preferences.getValue(
                    emailParam + "Subject_" + languageId, null);
            String body = preferences.getValue(
                    emailParam + "Body_" + languageId, null);

            AuthenticationServiceUtil
                    .sendPassword(request, emailFromName, emailFromAddress, emailToAddress, subject, body);
        } catch (Exception e) {
            if (e instanceof AuthException) {
                Throwable cause = e.getCause();

                if (cause instanceof PasswordExpiredException ||
                        cause instanceof UserLockoutException) {

                    SessionErrors.add(
                            request, cause.getClass().getName());
                } else {
                    SessionErrors.add(request, e.getClass().getName());
                }
            } else if (e instanceof CookieNotSupportedException ||
                    e instanceof NoSuchUserException ||
                    e instanceof PasswordExpiredException ||
                    e instanceof UserEmailAddressException ||
                    e instanceof UserIdException ||
                    e instanceof UserLockoutException ||
                    e instanceof UserPasswordException ||
                    e instanceof UserScreenNameException) {

                SessionErrors.add(request, e.getClass().getName());
            } else {
                PortalUtil.sendError(e, request, response);
            }
        }

        if (!SessionErrors.isEmpty(request)) {
            // url parameters
            Map<String, String> parameters = new HashMap<>();
            //boolean isSigningInPopup = ParamUtil.getBoolean(actionRequest, "isSigningInPopup");

            parameters.put("isPasswordReminder", "true");

            redirect = Helper.modifyRedirectUrl(redirect, request, parameters);

        } else {
            GlobalMessagesUtil
                    .addMessage("A password retrieval message has been sent, please check your email", request);
        }

        SessionErrors.clear(request);
        SessionMessages.clear(request);

        response.sendRedirect(redirect);
    }

    @RequestMapping(params = "isError=true")
    public String register(PortletRequest request, PortletResponse response,
            Model model) throws SystemException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        model.addAttribute("message",
                "Your password reset ticket has expired. Please try to reset your password again.");
        model.addAttribute("redirect_url", themeDisplay.getPortalURL());

        ModelAttributeUtil.populateModelWithPlatformConstants(model);
        return "password_reset_error";
    }

}
