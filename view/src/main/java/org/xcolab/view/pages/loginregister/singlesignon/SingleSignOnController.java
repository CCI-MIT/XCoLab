package org.xcolab.view.pages.loginregister.singlesignon;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.LockoutLoginException;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.exceptions.PasswordLoginException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.login.AuthenticationError;
import org.xcolab.view.pages.loginregister.LoginRegisterService;
import org.xcolab.view.util.entity.flash.ErrorMessage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SingleSignOnController {
    private static final String REGISTER_OR_LOGIN_VIEW = "loginregister/SSO/registerOrLogin";
    public static final String REGISTER_OR_LOGIN_URL = "/sso/registerOrLogin";

    private final LoginRegisterService loginRegisterService;

    @Autowired
    public SingleSignOnController(LoginRegisterService loginRegisterService) {
        this.loginRegisterService = loginRegisterService;
    }

    @GetMapping(REGISTER_OR_LOGIN_URL)
    public String registerOrLogin(HttpServletRequest request, Model model) {
        return REGISTER_OR_LOGIN_VIEW;
    }

    @PostMapping(REGISTER_OR_LOGIN_URL)
    public void linkUser(HttpServletRequest request, Model model, HttpServletResponse response,
            @RequestParam String login, @RequestParam String password) throws IOException {
        try {
            MembersClient.findMemberByScreenName(login);
        } catch (MemberNotFoundException e) {
            ErrorMessage.error(AuthenticationError.CREDENTIALS.getMessage())
                    .flashAndRedirect(request, response, SsoEndpoint.REGISTER_OR_LOGIN.getUrl());
            return;
        }

        HttpSession session = request.getSession();

        try {
            // Use local authentication API to check credentials
            Member member = loginRegisterService.login(request, response, login, password);
            // Do the linkage of OpenID or Facebook ID
            String fbIdString = (String) session.getAttribute(SSOKeys.FACEBOOK_USER_ID);
            String googleId = (String) session.getAttribute(SSOKeys.SSO_GOOGLE_ID);
            String profileImageId = (String) session.getAttribute(SSOKeys.SSO_PROFILE_IMAGE_ID);

            if (StringUtils.isNumeric(profileImageId) && member.getPortraitId() == 0) {
                long id = Long.parseLong(profileImageId);
                member.setPortraitFileEntryId(id);
                MembersClient.updateMember(member);
            }
            if (StringUtils.isNotEmpty(fbIdString)) {
                // update FB credentials
                long fbId = Long.parseLong(fbIdString);
                member.setFacebookId(fbId);
                MembersClient.updateMember(member);
                response.sendRedirect("/");
                return;
            }
            if (StringUtils.isNotEmpty(googleId)) {
                member.setGoogleId(googleId);
                MembersClient.updateMember(member);
                session.setAttribute(SSOKeys.OPEN_ID_LOGIN, member.getUserId());
                response.sendRedirect("/");
                return;
            }
            ErrorMessage.error(AuthenticationError.UNKNOWN.getMessage())
                    .flashAndRedirect(request, response, SsoEndpoint.REGISTER_OR_LOGIN.getUrl());

        } catch (MemberNotFoundException | PasswordLoginException | LockoutLoginException e) {
            ErrorMessage.error(AuthenticationError.fromException(e).getMessage())
                    .flashAndRedirect(request, response, SsoEndpoint.REGISTER_OR_LOGIN.getUrl());
        }
    }
}
