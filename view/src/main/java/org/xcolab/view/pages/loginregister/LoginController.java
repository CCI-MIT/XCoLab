package org.xcolab.view.pages.loginregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.LoginToken;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.TokenValidity;
import org.xcolab.entity.utils.notifications.member.MemberBatchRegistrationNotification;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.view.util.entity.flash.ErrorPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private static final String LOGIN_VIEW_NAME = "loginregister/login";
    private static final String LOGIN_TOKEN_VIEW_NAME = "loginregister/loginWithToken";

    private final AuthenticationService authenticationService;

    @Autowired
    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String login() {
        return LOGIN_VIEW_NAME;
    }

    @GetMapping("/login/token/{tokenId}")
    public String loginWithToken(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable String tokenId, @RequestParam String tokenKey) {
        model.addAttribute("tokenId", tokenId);
        model.addAttribute("tokenKey", tokenKey);
        final TokenValidity tokenValidity = MembersClient.validateLoginToken(tokenId, tokenKey);
        model.addAttribute("isValid", tokenValidity == TokenValidity.VALID);
        model.addAttribute("isExpired", tokenValidity == TokenValidity.EXPIRED);
        return LOGIN_TOKEN_VIEW_NAME;
    }

    @PostMapping(value = "/login/token/{tokenId}")
    public String doLoginWithToken(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable String tokenId, @RequestParam String tokenKey,
            @RequestParam(defaultValue = "false") boolean invalidateLink) {
        final TokenValidity tokenValidity = MembersClient.validateLoginToken(tokenId, tokenKey);
        switch (tokenValidity) {
            case VALID: {
                final Member member = MembersClient.getMemberForLoginToken(tokenId);
                authenticationService.authenticate(request, response, member);
                if (invalidateLink) {
                    MembersClient.invalidateLoginToken(tokenId);
                }
                AlertMessage.success("Login successful!").flash(request);
                return "redirect:/";
            }
            case EXPIRED: {
                final Member member = MembersClient.getMemberForLoginToken(tokenId);
                final LoginToken loginToken = MembersClient.createLoginToken(member.getId_());
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
