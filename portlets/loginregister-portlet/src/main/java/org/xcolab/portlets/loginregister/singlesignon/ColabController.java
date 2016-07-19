package org.xcolab.portlets.loginregister.singlesignon;

import com.ext.portlet.service.LoginLogLocalServiceUtil;
import com.ext.utils.authentication.service.AuthenticationServiceUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.portlets.loginregister.CreateUserBean;
import org.xcolab.portlets.loginregister.MainViewController;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "view", params = "SSO=colab")
public class ColabController {
    @RequestMapping(params = "action=initiateLoginOrReg")
    public void initiateLoginOrReg(ActionRequest request, ActionResponse response) throws Exception {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String redirectUrl = request.getParameter("redirect");
        HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        try {
            Member member = MembersClient.findMemberByScreenName(login);
            boolean loggedIn = MembersClient.validatePassword(password, member.getId_());
            if (loggedIn) {
                User liferayUser = UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(), member.getScreenName());
                LoginLogLocalServiceUtil.createLoginLog(liferayUser, httpReq.getRemoteAddr(), redirectUrl);

                AuthenticationServiceUtil.logUserIn(request, response, login, password);
                response.sendRedirect(redirectUrl);
                return;
            }
        } catch (MemberNotFoundException ignored) {}
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
                    MainViewController.completeRegistration(request, response, userBean, redirectUrl, true);

                }catch(Exception ignored){

                }
            }
        } catch (org.xcolab.client.sharedcolab.exceptions.MemberNotFoundException foreignNotFound) {

        }

        response.setRenderParameter("status", "registerOrLogin");
        response.setRenderParameter("SSO", "general");
        request.setAttribute("credentialsError", false);
    }
}

