package org.xcolab.view.pages.loginregister.singlesignon;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.members.exceptions.LockoutLoginException;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.exceptions.PasswordLoginException;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.view.pages.loginregister.CreateUserBean;
import org.xcolab.view.pages.loginregister.LoginRegisterUtil;
import org.xcolab.view.pages.loginregister.LoginRegisterController;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/sso/colab")
public class ColabController {

    @PostMapping
    public void initiateLoginOrReg(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String redirect = request.getParameter("redirect");
        if (StringUtils.isBlank(redirect) || redirect.contains("/register")) {
            redirect =  "/";
        }
        UriComponentsBuilder redirectUrl = UriComponentsBuilder.fromHttpUrl(redirect);
        redirectUrl.replaceQueryParam("signinRegError");
        redirectUrl.replaceQueryParam("isSigningInPopup");
        redirectUrl.replaceQueryParam("isSigningIn");
        redirectUrl.replaceQueryParam("isRegistering");
        redirectUrl.replaceQueryParam("isPasswordReminder");
        redirectUrl.replaceQueryParam("isSSOSigningIn");

        try {
            LoginRegisterUtil.login(request, login, password);
            response.sendRedirect(redirectUrl.toUriString());
            return;
        } catch (MemberNotFoundException | LockoutLoginException | PasswordLoginException ignored) {
            //login failed
        }
        try {
            org.xcolab.client.sharedcolab.pojo.Member foreignColab = SharedColabClient.findMemberByScreenName(login);
            boolean loggedIn = SharedColabClient.validatePassword(password, foreignColab.getId_());
            if (loggedIn) {
                CreateUserBean userBean = new CreateUserBean();
                userBean.setScreenName(foreignColab.getScreenName());
                userBean.setFirstName(foreignColab.getFirstName());
                userBean.setLastName(foreignColab.getLastName());
                userBean.setEmail(foreignColab.getEmailAddress());
                userBean.setCountry(foreignColab.getCountry());
                userBean.setPassword(password);
                userBean.setRetypePassword(password);
                //TODO: get user imageId and save it locally
                userBean.setShortBio(foreignColab.getShortBio());
                try {
                    LoginRegisterController.completeRegistration(request, response, userBean,
                            redirectUrl.toUriString(), true);
                    return;

                } catch (Exception ignored) {
                    ignored.printStackTrace();

                }
            }
        } catch (org.xcolab.client.sharedcolab.exceptions.MemberNotFoundException foreignNotFound) {

        }
        redirectUrl.replaceQueryParam("isSSOSigningIn", true);

        response.sendRedirect(redirectUrl.toUriString());
    }
}

