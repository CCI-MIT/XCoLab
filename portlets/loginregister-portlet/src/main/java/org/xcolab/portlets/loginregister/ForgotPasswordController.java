package org.xcolab.portlets.loginregister;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.liferay.LoginRegisterUtil;
import org.xcolab.entity.utils.GlobalMessagesUtil;
import org.xcolab.entity.utils.ModelAttributeUtil;
import org.xcolab.entity.utils.email.notifications.member.MemberForgotPasswordNotification;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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
            GlobalMessagesUtil.addMessage(
                    "A password retrieval message has been sent, please check your email", request);
        } catch (MemberNotFoundException e) {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("isPasswordReminder", "true");

            redirect = Helper.modifyRedirectUrl(redirect, request, parameters);

            GlobalMessagesUtil.addMessage(
                    "Could not send password retrieval message, please check your screen name or email", request);
        }

        SessionErrors.clear(request);
        SessionMessages.clear(request);

        response.sendRedirect(redirect);
    }

    private static void sendEmailNotificationToForPasswordReset(String memberIp, String link,
            ThemeDisplay themeDisplay, Member recipient) {
        new MemberForgotPasswordNotification(memberIp, link, recipient, themeDisplay.getPortalURL())
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
                "Your password reset ticket has expired or is invalid. Please try to reset your password again.");
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
