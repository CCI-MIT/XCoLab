package org.xcolab.view.pages.loginregister.singlesignon;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.members.exceptions.LockoutLoginException;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.exceptions.PasswordLoginException;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.view.pages.loginregister.CreateUserBean;
import org.xcolab.view.pages.loginregister.Helper;
import org.xcolab.view.pages.loginregister.LoginRegisterUtil;
import org.xcolab.view.pages.loginregister.MainViewController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "view", params = "SSO=colab")
public class ColabController {
    @RequestMapping(params = "action=initiateLoginOrReg")
    public void initiateLoginOrReg(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String redirect = request.getParameter("redirect");
        if (StringUtils.isBlank(redirect) || redirect.contains("web/guest/loginregister")) {
            redirect =  "/";
        }
        redirect = Helper.removeParamFromRequestStr(redirect, "signinRegError");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSigningInPopup");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSigningIn");
        redirect = Helper.removeParamFromRequestStr(redirect, "isRegistering");
        redirect = Helper.removeParamFromRequestStr(redirect, "isPasswordReminder");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSSOSigningIn");

        try {
            LoginRegisterUtil.login(request, login, password);
            response.sendRedirect(redirect);
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
                    MainViewController.completeRegistration(request, response, userBean, redirect, true);
                    return;

                } catch (Exception ignored) {
                    ignored.printStackTrace();

                }
            }
        } catch (org.xcolab.client.sharedcolab.exceptions.MemberNotFoundException foreignNotFound) {

        }

        Map<String, String> parameters = new HashMap<>();
        //boolean isSigningInPopup = ParamUtil.getBoolean(HttpServletRequest, "isSigningInPopup");

        parameters.put("isSSOSigningIn", "true");

        redirect = Helper.modifyRedirectUrl(redirect, request, parameters);

        response.sendRedirect(redirect);
    }
}

