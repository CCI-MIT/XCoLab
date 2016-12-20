package org.xcolab.portlets.loginregister;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.liferay.portal.CookieNotSupportedException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PasswordExpiredException;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.UserIdException;
import com.liferay.portal.UserLockoutException;
import com.liferay.portal.UserPasswordException;
import com.liferay.portal.UserScreenNameException;



import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AuthException;



import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFound;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.entity.utils.portlet.PortletUtil;
import org.xcolab.entity.utils.portlet.session.SessionErrors;
import org.xcolab.entity.utils.portlet.session.SessionMessages;
import org.xcolab.liferay.LoginRegisterUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "view", params = "isLoggingIn=true")
public class LoginController {
    private final static Logger _log = LoggerFactory.getLogger(LoginController.class);

    @ActionMapping
    public void doLogin(ActionRequest request, ActionResponse response) throws IOException {



        HttpServletRequest httpRequest = PortletUtils.getOriginalRequest(request);

        String refererHeader = httpRequest.getHeader("referer");
        String refererRequest =  PortletUtil.getHttpServletRequest(request).getHeader("referer");

        String redirect = httpRequest.getParameter("redirect");
        redirect = !StringUtils.isBlank(redirect) ? redirect : refererHeader;
        redirect = !StringUtils.isBlank(redirect) ? redirect : refererRequest;
        redirect = !StringUtils.isBlank(redirect) ? redirect : ConfigurationAttributeKey.COLAB_URL.get();

        redirect = Helper.removeParamFromRequestStr(redirect, "signinRegError");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSigningInPopup");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSigningIn");
        redirect = Helper.removeParamFromRequestStr(redirect, "isRegistering");
        redirect = Helper.removeParamFromRequestStr(redirect, "isPasswordReminder");

        User user = null;
        try {
            String login = request.getParameter("login");

            user = LoginRegisterUtil.login(request, response, login, request.getParameter("password"), redirect);
            if(user==null) {
                //Notify about wrong password
                throw new AuthException();
            }

        } catch (Exception e) {
            if (e instanceof AuthException) {
                Throwable cause = e.getCause();

                if (cause instanceof PasswordExpiredException ||
                        cause instanceof UserLockoutException) {

                    SessionErrors.add(request, cause.getClass().getName());
                } else {
                    SessionErrors.add(request, e.getClass().getName());
                }
            } else if (e instanceof CookieNotSupportedException ||
                    e instanceof MemberNotFoundException ||
                    e instanceof NoSuchUserException ||
                    e instanceof PasswordExpiredException ||
                    e instanceof UserEmailAddressException ||
                    e instanceof UserIdException ||
                    e instanceof UserLockoutException ||
                    e instanceof UserPasswordException ||
                    e instanceof UserScreenNameException) {

                SessionErrors.add(request, e.getClass().getName());
            } else {
                _log.error("Can't log user in", e);
                //PortalUtil.sendError(e, request, response);
                //TODO:INVESTIGATE THE SEND ERROR MSG
            }
        }

        if (!SessionErrors.isEmpty(request)) {
            // url parameters
            Map<String, String> parameters = new HashMap<>();
            //boolean isSigningInPopup = ParamUtil.getBoolean(actionRequest, "isSigningInPopup");

            parameters.put("isSigningIn", "true");

            redirect = Helper.modifyRedirectUrl(redirect, request, parameters);

        }

        SessionErrors.clear(request);
        SessionMessages.clear(request);

        BalloonCookie bc = BalloonCookie.fromCookieArray(request.getCookies());
        if (StringUtils.isNotBlank(bc.getUuid()) && user!=null) {
            // cookie is present, get BalloonUserTracking if it exists and update association to the current user
            try {
                BalloonUserTracking but = BalloonsClient.getBalloonUserTracking(bc.getUuid());
                if (but == null) {
                    List<BalloonUserTracking> buts =
                            BalloonsClient.getBalloonUserTrackingByEmail(user.getEmailAddress());
                    if (!buts.isEmpty()) {
                        but = buts.get(0);
                    }
                }

                if (but != null && but.getUserId() != user.getUserId()) {
                    but.setUserId(user.getUserId());
                    BalloonsClient.updateBalloonUserTracking(but);

                }
            } catch (BalloonUserTrackingNotFound ignored) {
            }
        }

        response.sendRedirect(redirect);
    }
}
