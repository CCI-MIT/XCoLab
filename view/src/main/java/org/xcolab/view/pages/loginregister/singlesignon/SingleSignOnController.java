package org.xcolab.view.pages.loginregister.singlesignon;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.LockoutLoginException;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.exceptions.PasswordLoginException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.pages.loginregister.LoginRegisterUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "view", params = "SSO=general")
public class SingleSignOnController {

    private final static Logger _log = LoggerFactory.getLogger(SingleSignOnController.class);

    @RequestMapping(params = "action=provideSSOCredentials")
    public void linkUser(HttpServletRequest request, Model model, HttpServletResponse response,
            @RequestParam String login, @RequestParam String password) throws IOException {
        Member member;
        try {
            MembersClient.findMemberByScreenName(login);
        } catch (MemberNotFoundException e) {
            // username incorrect
            //TODO: redirect
//            response.setRenderParameter("status", "registerOrLogin");
//            response.setRenderParameter("SSO", "general");
            request.setAttribute("credentialsError", true);
            return;
        }

        HttpSession session = request.getSession();

        try {
            Map<String, Object> resultsMap = new HashMap<>();
            // Use local authentication API to check credentials
            member = LoginRegisterUtil.login(request, login, password);
            // Do the linkage of OpenID or Facebook ID
            String fbIdString = (String) session.getAttribute(SSOKeys.FACEBOOK_USER_ID);
            String openId = (String) session.getAttribute(SSOKeys.SSO_OPENID_ID);
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
            if (StringUtils.isNotEmpty(openId)) {
                member.setOpenId(openId);
                MembersClient.updateMember(member);
                session.setAttribute("OPEN_ID_LOGIN", member.getUserId());
                response.sendRedirect("/");
                return;
            }
            //TODO: redirect
//                response.setRenderParameter("error", "true");
//                response.setRenderParameter("SSO", "general");
            request.setAttribute("error", "An unknown error occurred.");

            return;

        } catch (MemberNotFoundException | IOException | LockoutLoginException | PasswordLoginException e) {
            _log.error("Linking user {} failed:", login, e);
        }

        // passwords don't match
        //TODO: redirect
//        response.setRenderParameter("status", "registerOrLogin");
//        response.setRenderParameter("SSO", "general");
        request.setAttribute("credentialsError", true);
    }

    @RequestMapping(params = "error=true")
    public String registerError(HttpServletRequest request) {
        return "SSO/error";
    }

    @RequestMapping(params = "status=registerOrLogin")
    public String registerOrLogin(HttpServletRequest request, Model model) {
        model.addAttribute("colabName", ConfigurationAttributeKey.COLAB_NAME.get());
        model.addAttribute("colabShortName", ConfigurationAttributeKey.COLAB_SHORT_NAME.get());
        return "SSO/registerOrLogin";
    }
}
