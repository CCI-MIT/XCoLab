package org.xcolab.view.pages.loginregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.AuthenticationContext;
import org.xcolab.view.util.entity.flash.AlertMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private static final String LOGIN_VIEW_NAME = "loginregister/login";
    private static final String LOGIN_TOKEN_VIEW_NAME = "loginregister/loginWithToken";

    private final AuthenticationContext authenticationContext;

    @Autowired
    public LoginController(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
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
        return LOGIN_TOKEN_VIEW_NAME;
    }

    @PostMapping("/login/token/{tokenId}")
    public String doLoginWithToken(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable String tokenId, @RequestParam String tokenKey) {
        if (MembersClient.validateLoginToken(tokenId, tokenKey)) {
            AlertMessage.success("Login successful!").flash(request);
            final Member member = MembersClient.getMemberForLoginToken(tokenId);
            authenticationContext.authenticate(request, member);
            //TODO: potentially make the links single-use
//            MembersClient.invalidateLoginToken(tokenId);
        } else {
            AlertMessage.danger("Invalid or expired login token.").flash(request);
        }
        return "redirect:/";
    }

}
