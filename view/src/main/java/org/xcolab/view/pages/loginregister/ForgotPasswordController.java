package org.xcolab.view.pages.loginregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.IUserLoginRegisterClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.entity.utils.notifications.member.MemberForgotPasswordNotification;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class ForgotPasswordController {

    private static final String FORGOT_PASSWORD_URL
            = "/login/resetPassword/update?resetToken=%s&screenName=%s";

    private static final String INVALID_TOKEN_ERROR_MESSAGE =
            "Your password reset token has expired or is invalid. Please try to reset your password again";

    private final LoginRegisterService loginRegisterService;

    private final IUserLoginRegisterClient userLoginRegister;

    private final IUserClient userClient;

    @Autowired
    public ForgotPasswordController(LoginRegisterService loginRegisterService, IUserLoginRegisterClient userLoginRegister,
            IUserClient userClient) {
        this.loginRegisterService = loginRegisterService;
        this.userLoginRegister = userLoginRegister;
        this.userClient = userClient;
    }

    @PostMapping("/login/resetPassword")
    public void sendPassword(HttpServletRequest request, HttpServletResponse response,
            @RequestParam String screenNameOrEmail) throws IOException {

        String referer = request.getHeader(HttpHeaders.REFERER);

        final UriComponentsBuilder redirectBuilder =
                UriComponentsBuilder.fromUriString(LinkUtils.getSafeRedirectUri(referer))
                        .replaceQueryParam("signinRegError")
                        .replaceQueryParam("isPasswordReminder")
                        .replaceQueryParam("isSigningIn");

        try {
            UserWrapper member;
            if (screenNameOrEmail != null && screenNameOrEmail.contains("@")) {
                member = userClient.findMemberByEmailAddress(screenNameOrEmail);
            } else {
                member = userClient.findMemberByScreenName(screenNameOrEmail);
            }

            String token = userLoginRegister.createForgotPasswordToken(member.getId());
            String colabUrl = PlatformAttributeKey.COLAB_URL.get();
            String passwordLink = colabUrl + String.format(FORGOT_PASSWORD_URL, token,
                    member.getScreenName());

            sendEmailNotificationToForPasswordReset(request.getRemoteAddr(), passwordLink, member);
            AlertMessage.success("A password retrieval message has been sent. Please check your email")
                    .flash(request);
        } catch (MemberNotFoundException e) {
            redirectBuilder.queryParam("isPasswordReminder", true);
            AlertMessage.danger("Could not send password retrieval message, please check your screen name or email")
                    .flash(request);
        }

        response.sendRedirect(redirectBuilder.toUriString());
    }

    @GetMapping("/login/resetPassword")
    public String handleInvalidHttpMethod(HttpServletRequest request) {
        AlertMessage.warning("Warning: page reloaded before password reset was finished.")
                .flash(request);
        String referrer = request.getHeader(HttpHeaders.REFERER);
        String redirect = LinkUtils.getSafeRedirectUri(referrer);
        final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(redirect);
        uriBuilder.queryParam("isPasswordReminder", true);
        return "redirect:" + uriBuilder.build().toUriString();
    }

    private static void sendEmailNotificationToForPasswordReset(String memberIp, String link,
             UserWrapper recipient) {
        new MemberForgotPasswordNotification(memberIp, link, recipient)
                .sendEmailNotification();
    }

    @GetMapping("/login/resetPassword/update")
    public String openResetPassword(HttpServletRequest request, HttpServletResponse response,
            ForgotPasswordBean forgotPasswordBean, Model model,
            @RequestParam (required = false) String resetToken,
            @RequestParam(required = false) String screenName) {

        if (resetToken == null) {
            AlertMessage.danger("Your reset link is incomplete. Please go back to the email and "
                    + "make sure you used the full link.").flash(request);
            return "redirect:/";
        }

        if (!userLoginRegister.isForgotPasswordTokenValid(resetToken)) {
            AlertMessage.danger(INVALID_TOKEN_ERROR_MESSAGE)
                    .flash(request);
            return "redirect:/";
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

        if (userLoginRegister.isForgotPasswordTokenValid(resetToken)) {
            try {
                loginRegisterService.updatePassword(resetToken, newPassword);
                AlertMessage.success("Your password was successfully updated!").flash(request);
            } catch (MemberNotFoundException e) {
                throw new InternalException(e);
            }

        } else {
            AlertMessage.danger(INVALID_TOKEN_ERROR_MESSAGE)
                    .flash(request);
        }
        return "redirect:/";
    }
}
