package org.xcolab.portlets.loginregister;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.liferay.portal.CookieNotSupportedException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PasswordExpiredException;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.UserIdException;
import com.liferay.portal.UserLockoutException;
import com.liferay.portal.UserPasswordException;
import com.liferay.portal.UserScreenNameException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.liferay.LoginRegisterUtil;
import org.xcolab.utils.GlobalMessagesUtil;
import org.xcolab.utils.ModelAttributeUtil;
import org.xcolab.utils.emailnotification.member.MemberForgotPasswordNotification;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "view")
public class ForgotPasswordController {

    private static final String FORGOT_PASSWORD_URL = "/web/guest/loginregister/-/login/forgotPassword/";


    @ActionMapping(params = {"isForgotpass=true"})
    public void sendPassword(ActionRequest request, ActionResponse response) throws IOException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(
                WebKeys.THEME_DISPLAY);

        HttpServletRequest httpRequest = PortletUtils.getOriginalRequest(request);

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

            Member member;
            if (userNameEmail.contains("@")) {
                member = MembersClient.findMemberByEmailAddress(userNameEmail);
            } else {
                member = MembersClient.findMemberByScreenName(userNameEmail);
            }

            String token = MembersClient.createForgotPasswordToken(member.getUserId());
            String colabUrl = ConfigurationAttributeKey.COLAB_URL.get();
            String passwordLink = colabUrl + FORGOT_PASSWORD_URL + "" + token;

            sendEmailNotificationToForPasswordReset(
                    PortalUtil.getHttpServletRequest(request).getRemoteAddr(),
                    passwordLink, themeDisplay, member);

            //TODO: exception handling
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

    private static void sendEmailNotificationToForPasswordReset(String memberIp, String link,
            ThemeDisplay themeDisplay, Member recipient) {
        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setPortalURL(themeDisplay.getPortalURL());

        new MemberForgotPasswordNotification(memberIp, link, recipient, serviceContext)
                .sendEmailNotification();
    }

    @RequestMapping(params = "isError=true")
    public String register(PortletRequest request, PortletResponse response,
                           Model model) {
        return redirectToErrorPageOnPasswordReset(model, request);
    }

    private String redirectToErrorPageOnPasswordReset(Model model, PortletRequest request) {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        model.addAttribute("message",
                "Your password reset ticket has expired. Please try to reset your password again.");
        model.addAttribute("redirect_url", themeDisplay.getPortalURL());

        ModelAttributeUtil.populateModelWithPlatformConstants(model);
        return "password_reset_error";
    }


    @RequestMapping(params = "pageToDisplay=password_reset")
    public String openResetPassword(PortletRequest request, PortletResponse response,
                                    Model model, @RequestParam String resetTicket) {

        if (!MembersClient.isForgotPasswordTokenValid(resetTicket)) {
            return redirectToErrorPageOnPasswordReset(model, request);
        } else {
            CreateUserBean userBean = new CreateUserBean();
            model.addAttribute("createUserBean", userBean);
            model.addAttribute("passwordResetToken", resetTicket);
            model.addAttribute("colabName", ConfigurationAttributeKey.COLAB_NAME.get());
            return "password_reset";
        }
    }

    @ActionMapping(params = {"isUpdatingPassword=true"})
    public void updatePassword(ActionRequest request, Model model,
                               ActionResponse response, CreateUserBean newAccountBean,
                               BindingResult result,
                               @RequestParam(required = false) String passwordResetToken) throws IOException, SystemException {

        String newPassword = newAccountBean.getPassword();

        if (MembersClient.isForgotPasswordTokenValid(passwordResetToken)) {
            try {
                LoginRegisterUtil.updatePassword(passwordResetToken, newPassword );
                GlobalMessagesUtil
                        .addMessage("Your password was successfully updated! ", request);
                SessionErrors.clear(request);
                SessionMessages.clear(request);
                response.sendRedirect("/");

            } catch (MemberNotFoundException | PortalException e) {
                response.setRenderParameter("isError", "true");
            }

        } else {
            response.setRenderParameter("isError", "true");
        }

    }

}
