package org.xcolab.view.pages.loginregister;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.flash.AlertMessage;
import org.xcolab.entity.utils.email.notifications.member.MemberForgotPasswordNotification;
import org.xcolab.entity.utils.portlet.session.SessionErrors;
import org.xcolab.entity.utils.portlet.session.SessionMessages;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ForgotPasswordController {

    private static final String FORGOT_PASSWORD_URL = "/login/resetPassword/update?resetToken=";

    @PostMapping("/login/resetPassword")
    public void sendPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String redirect = request.getParameter("redirect");
        String referer = request.getHeader(HttpHeaders.REFERER);
        redirect = !StringUtils.isBlank(redirect) ? redirect : referer;

        redirect = !StringUtils.isBlank(redirect) ? redirect : ConfigurationAttributeKey.COLAB_URL.get();

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
            String passwordLink = colabUrl + FORGOT_PASSWORD_URL + token;

            sendEmailNotificationToForPasswordReset(request.getRemoteAddr(),
                    passwordLink,  member);
            AlertMessage.success("A password retrieval message has been sent, please check your email")
                    .flash(request);
        } catch (MemberNotFoundException e) {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("isPasswordReminder", "true");

            redirect = Helper.modifyRedirectUrl(redirect, request, parameters);

            AlertMessage.danger("Could not send password retrieval message, please check your screen name or email")
                    .flash(request);
        }

        SessionErrors.clear(request);
        SessionMessages.clear(request);

        response.sendRedirect(redirect);
    }

    private static void sendEmailNotificationToForPasswordReset(String memberIp, String link,
             Member recipient) {
        new MemberForgotPasswordNotification(memberIp, link, recipient, ConfigurationAttributeKey.COLAB_URL.get())
                .sendEmailNotification();
    }

    @GetMapping("/login/resetPassword/error")
    public String resetPasswordError(HttpServletRequest request, HttpServletResponse response, Model model) {
        return redirectToErrorPageOnPasswordReset(model);
    }

    private String redirectToErrorPageOnPasswordReset(Model model) {
        model.addAttribute("message",
                "Your password reset ticket has expired or is invalid. Please try to reset your password again.");
        model.addAttribute("redirect_url", "/");
        return "loginregister/password_reset_error";
    }


    @GetMapping("/login/resetPassword/update")
    public String openResetPassword(HttpServletRequest request, HttpServletResponse response,
                                    Model model, @RequestParam String resetToken) {

        if (!MembersClient.isForgotPasswordTokenValid(resetToken)) {
            return redirectToErrorPageOnPasswordReset(model);
        } else {
            CreateUserBean userBean = new CreateUserBean();
            model.addAttribute("createUserBean", userBean);
            model.addAttribute("resetToken", resetToken);
            return "loginregister/password_reset";
        }
    }

    @PostMapping("/login/resetPassword/update")
    public void updatePassword(HttpServletRequest request, HttpServletResponse response,
            Model model, CreateUserBean newAccountBean, BindingResult result,
            @RequestParam(required = false) String resetToken)
            throws IOException {

        String newPassword = newAccountBean.getPassword();

        if (MembersClient.isForgotPasswordTokenValid(resetToken)) {
            try {
                LoginRegisterUtil.updatePassword(resetToken, newPassword);
                AlertMessage.success("Your password was successfully updated!")
                        .flash(request);
                SessionErrors.clear(request);
                SessionMessages.clear(request);
                response.sendRedirect("/");
            } catch (MemberNotFoundException e) {
                response.sendRedirect("/login/resetPassword/error");
            }

        } else {
            response.sendRedirect("/login/resetPassword/error");
        }
    }
}
