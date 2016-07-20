package org.xcolab.portlets.loginregister.singlesignon;

import com.ext.portlet.service.LoginLogLocalServiceUtil;
import com.ext.utils.authentication.service.AuthenticationServiceUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.portlets.loginregister.CreateUserBean;
import org.xcolab.portlets.loginregister.Helper;
import org.xcolab.portlets.loginregister.MainViewController;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "view", params = "SSO=colab")
public class ColabController {
    @RequestMapping(params = "action=initiateLoginOrReg")
    public void initiateLoginOrReg(ActionRequest request, ActionResponse response) throws Exception {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String redirect = httpReq.getParameter("redirect");
        redirect = !StringUtils.isBlank(redirect) ? redirect : themeDisplay.getURLHome();
        if (redirect.contains("web/guest/loginregister")) {
            redirect =  themeDisplay.getURLHome();
        }
        redirect = Helper.removeParamFromRequestStr(redirect, "signinRegError");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSigningInPopup");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSigningIn");
        redirect = Helper.removeParamFromRequestStr(redirect, "isRegistering");
        redirect = Helper.removeParamFromRequestStr(redirect, "isPasswordReminder");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSSOSigningIn");

        try {
            Member member = MembersClient.findMemberByScreenName(login);
            boolean loggedIn = MembersClient.validatePassword(password, member.getId_());
            if (loggedIn) {
                User liferayUser = UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(), member.getScreenName());
                LoginLogLocalServiceUtil.createLoginLog(liferayUser, httpReq.getRemoteAddr(), redirect);

                AuthenticationServiceUtil.logUserIn(request, response, login, password);
                response.sendRedirect(redirect);
                return;
            }
        } catch (MemberNotFoundException ignored) {
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
        //boolean isSigningInPopup = ParamUtil.getBoolean(actionRequest, "isSigningInPopup");

        parameters.put("isSSOSigningIn", "true");

        redirect = Helper.modifyRedirectUrl(redirect, request, parameters);

        response.sendRedirect(redirect);
    }
}

