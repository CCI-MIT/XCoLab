package org.xcolab.view.pages.loginregister;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFound;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.members.exceptions.LockoutLoginException;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.exceptions.PasswordLoginException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.portlet.session.SessionErrors;
import org.xcolab.entity.utils.portlet.session.SessionMessages;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "view", params = "isLoggingIn=true")
public class LoginController {

//    @PostMapping("/login")
    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String refererHeader = request.getHeader("referer");

        String redirect = request.getParameter("redirect");
        redirect = !StringUtils.isBlank(redirect) ? redirect : refererHeader;
        redirect = !StringUtils.isBlank(redirect) ? redirect : ConfigurationAttributeKey.COLAB_URL.get();

        redirect = Helper.removeParamFromRequestStr(redirect, "signinRegError");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSigningInPopup");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSigningIn");
        redirect = Helper.removeParamFromRequestStr(redirect, "isRegistering");
        redirect = Helper.removeParamFromRequestStr(redirect, "isPasswordReminder");

        Member member = null;
        try {
            String login = request.getParameter("login");
            member = LoginRegisterUtil.login(request, login, request.getParameter("password"), redirect);
        } catch (MemberNotFoundException | PasswordLoginException | LockoutLoginException e) {
            SessionErrors.add(request, e.getClass().getName());
        }

        if (!SessionErrors.isEmpty(request)) {
            // url parameters
            Map<String, String> parameters = new HashMap<>();
            //boolean isSigningInPopup = ParamUtil.getBoolean(HttpServletRequest, "isSigningInPopup");

            parameters.put("isSigningIn", "true");

            redirect = Helper.modifyRedirectUrl(redirect, request, parameters);
        }

        SessionErrors.clear(request);
        SessionMessages.clear(request);

        BalloonCookie bc = BalloonCookie.fromCookieArray(request.getCookies());
        if (StringUtils.isNotBlank(bc.getUuid()) && member!=null) {
            // cookie is present, get BalloonUserTracking if it exists and update association to the current user
            try {
                BalloonUserTracking but = BalloonsClient.getBalloonUserTracking(bc.getUuid());
                if (but == null) {
                    List<BalloonUserTracking> buts =
                            BalloonsClient.getBalloonUserTrackingByEmail(member.getEmailAddress());
                    if (!buts.isEmpty()) {
                        but = buts.get(0);
                    }
                }

                if (but != null && but.getUserId() != member.getUserId()) {
                    but.setUserId(member.getUserId());
                    BalloonsClient.updateBalloonUserTracking(but);

                }
            } catch (BalloonUserTrackingNotFound ignored) {
            }
        }

        response.sendRedirect(redirect);
    }
}
