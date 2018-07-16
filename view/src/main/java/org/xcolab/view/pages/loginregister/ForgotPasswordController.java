package org.xcolab.view.pages.loginregister;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.entity.utils.notifications.member.MemberForgotPasswordNotification;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class ForgotPasswordController {

    private static final String FORGOT_PASSWORD_URL
            = "/login/resetPassword/update?resetToken=%s&screenName=%s";
    private final LoginRegisterService loginRegisterService;

    @Autowired
    public ForgotPasswordController(LoginRegisterService loginRegisterService) {
        this.loginRegisterService = loginRegisterService;
    }

    @PostMapping("/login/resetPassword")
    public void sendPassword(HttpServletRequest request, HttpServletResponse response,
            @RequestParam String screenNameOrEmail) throws IOException {

        String redirect = request.getParameter("redirect");
        String referer = request.getHeader(HttpHeaders.REFERER);
        redirect = !StringUtils.isBlank(redirect) ? redirect : referer;

        redirect = !StringUtils.isBlank(redirect) ? redirect : PlatformAttributeKey.COLAB_URL.get();

        redirect = removeParamFromRequestStr(redirect, "signinRegError");
        redirect = removeParamFromRequestStr(redirect, "isPasswordReminder");
        redirect = removeParamFromRequestStr(redirect, "isSigningIn");
        redirect = removeParamFromRequestStr(redirect, "isRegistering");

        try {
            Member member;
            if (screenNameOrEmail != null && screenNameOrEmail.contains("@")) {
                member = MembersClient.findMemberByEmailAddress(screenNameOrEmail);
            } else {
                member = MembersClient.findMemberByScreenName(screenNameOrEmail);
            }

            String token = MembersClient.createForgotPasswordToken(member.getUserId());
            String colabUrl = PlatformAttributeKey.COLAB_URL.get();
            String passwordLink = colabUrl + String.format(FORGOT_PASSWORD_URL, token,
                    member.getScreenName());

            sendEmailNotificationToForPasswordReset(request.getRemoteAddr(), passwordLink, member);
            AlertMessage.success("A password retrieval message has been sent. Please check your email")
                    .flash(request);
        } catch (MemberNotFoundException e) {

            //TODO: better way of passing this on
            redirect += "isPasswordReminder=true";

            AlertMessage.danger("Could not send password retrieval message, please check your screen name or email")
                    .flash(request);
        }

        response.sendRedirect(redirect);
    }

    public static String removeParamFromRequestStr(String requestStr, String param) {
        return requestStr == null ? null : requestStr.replaceAll("&?" + param + "=[^&#]*", "");
    }

    private static void sendEmailNotificationToForPasswordReset(String memberIp, String link,
             Member recipient) {
        new MemberForgotPasswordNotification(memberIp, link, recipient)
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
            ForgotPasswordBean forgotPasswordBean, Model model, @RequestParam String resetToken,
            @RequestParam(required = false) String screenName) {

        if (!MembersClient.isForgotPasswordTokenValid(resetToken)) {
            return redirectToErrorPageOnPasswordReset(model);
        } else {
            model.addAttribute("screenName", screenName);
            model.addAttribute("forgotPasswordBean", forgotPasswordBean);
            model.addAttribute("resetToken", resetToken);
            return "loginregister/password_reset";
        }
    }

    @PostMapping("/login/resetPassword/update")
    public String updatePassword(HttpServletRequest request, HttpServletResponse response,
            Model model, @Valid ForgotPasswordBean forgotPasswordBean, BindingResult result,
            @RequestParam(required = false) String resetToken,
            @RequestParam(required = false) String screenName) {

        if (result.hasErrors()) {
            return openResetPassword(request, response, forgotPasswordBean, model, resetToken,
                    screenName);
        }

        String newPassword = forgotPasswordBean.getPassword();

        if (MembersClient.isForgotPasswordTokenValid(resetToken)) {
            try {
                loginRegisterService.updatePassword(resetToken, newPassword);
                AlertMessage.success("Your password was successfully updated!").flash(request);
                return "redirect:/";
            } catch (MemberNotFoundException e) {
                return "redirect:/login/resetPassword/error";
            }

        } else {
            return "redirect:/login/resetPassword/error";
        }
    }
}
