package org.xcolab.view.pages.loginregister;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.ILoginTokenClient;
import org.xcolab.client.user.pojo.wrapper.LoginTokenWrapper;
import org.xcolab.client.user.pojo.wrapper.TokenValidityWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.commons.servlet.flash.ErrorPage;
import org.xcolab.entity.utils.notifications.member.MemberBatchRegistrationNotification;
import org.xcolab.view.auth.AuthenticationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private static final String LOGIN_VIEW_NAME = "loginregister/login";
    private static final String LOGOUT_VIEW_NAME = "loginregister/logout";
    private static final String LOGIN_TOKEN_VIEW_NAME = "loginregister/loginWithToken";

    private final AuthenticationService authenticationService;
    private final RequestCache requestCache = new HttpSessionRequestCache();

    private final ILoginTokenClient loginTokenClient;

    @Autowired
    public LoginController(AuthenticationService authenticationService, ILoginTokenClient loginTokenClient) {
        this.authenticationService = authenticationService;
        this.loginTokenClient = loginTokenClient;
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean isOauthLogin = false;
        boolean hideClimateXLogin = false;
        SavedRequest savedRequest = this.requestCache.getRequest(request, response);
        if (savedRequest != null) {
            DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) savedRequest;
            final String requestURI = defaultSavedRequest.getRequestURI();
            isOauthLogin = StringUtils.isNotEmpty(requestURI) && requestURI.startsWith("/oauth/authorize");
            hideClimateXLogin = getBooleanParameter(defaultSavedRequest, "hideClimateXLogin");
        }
        model.addAttribute("hideRegisterPrompt", isOauthLogin);
        model.addAttribute("hideClimateXLogin", hideClimateXLogin);
        return LOGIN_VIEW_NAME;
    }

    @GetMapping("/logout")
    public String showLogoutPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        return LOGOUT_VIEW_NAME;
    }

    private boolean getBooleanParameter(DefaultSavedRequest defaultSavedRequest, String paramName) {
        final String[] stringValueArray =
                defaultSavedRequest.getParameterValues(paramName);
        if (stringValueArray != null) {
            return Boolean.valueOf(stringValueArray[0]);
        }
        return false;
    }

    @GetMapping("/login/token/{tokenId}")
    public String loginWithToken(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper loggedInMember, @PathVariable String tokenId,
            @RequestParam(defaultValue = "false") String tokenKey) {
        if (loggedInMember != null) {
            AlertMessage.warning("You are already logged in.");
            return "redirect:/";
        }
        model.addAttribute("tokenId", tokenId);
        model.addAttribute("tokenKey", tokenKey);
        final boolean isIncomplete = tokenKey == null;
        model.addAttribute("isIncomplete", isIncomplete);
        if (!isIncomplete) {
            final TokenValidityWrapper
                    tokenValidity = loginTokenClient.validateLoginToken(tokenId, tokenKey);
            model.addAttribute("isValid", tokenValidity == TokenValidityWrapper.VALID);
            model.addAttribute("isExpired", tokenValidity == TokenValidityWrapper.EXPIRED);
        }
        return LOGIN_TOKEN_VIEW_NAME;
    }

    @PostMapping(value = "/login/token/{tokenId}")
    public String doLoginWithToken(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable String tokenId,
            @RequestParam(defaultValue = "false") String tokenKey,
            @RequestParam(defaultValue = "false") boolean invalidateLink) {
        if (tokenKey == null) {
            AlertMessage.warning("Login link incomplete, make sure you used the full link.");
            return "redirect:/";
        }
        final TokenValidityWrapper
                tokenValidity = loginTokenClient.validateLoginToken(tokenId, tokenKey);
        switch (tokenValidity) {
            case VALID: {
                final UserWrapper member = loginTokenClient.getMemberForLoginToken(tokenId);
                authenticationService.authenticate(request, response, member);
                if (invalidateLink) {
                    loginTokenClient.invalidateLoginToken(tokenId);
                }
                AlertMessage.success("Login successful!").flash(request);
                return "redirect:/";
            }
            case EXPIRED: {
                final UserWrapper member = loginTokenClient.getMemberForLoginToken(tokenId);
                final LoginTokenWrapper loginToken = loginTokenClient.createLoginToken(member.getId());
                new MemberBatchRegistrationNotification(member, loginToken).sendEmailNotification();
                AlertMessage.success("Your token is expired. A new login link has been sent.")
                        .flash(request);
                return "redirect:/";
            }
            case INVALID:
            default:
                AlertMessage.danger("Invalid login token.").flash(request);
                return "redirect:/";
        }
    }

    @GetMapping("/loginDisabled")
    public String showLoginDisabled(HttpServletRequest request) {
        return ErrorPage.error("The login function has been disabled on this site. "
                + "Please use the <a href='/feedback'>feedback form</a> to contact "
                + "an administrator for more information.")
                .withTitle("Login disabled")
                .flashAndReturnView(request);
    }
}
