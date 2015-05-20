package org.xcolab.portlets.loginregister;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ext.portlet.NoSuchMessagingIgnoredRecipientsException;
import com.ext.portlet.service.LoginLogLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.ext.portlet.NoSuchBalloonUserTrackingException;
import com.ext.portlet.model.BalloonUserTracking;
import com.ext.portlet.model.MessagingIgnoredRecipients;
import com.ext.portlet.service.BalloonUserTrackingLocalServiceUtil;
import com.ext.portlet.service.MessagingIgnoredRecipientsLocalServiceUtil;
import com.ext.utils.authentication.service.AuthenticationServiceUtil;
import com.liferay.portal.CookieNotSupportedException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PasswordExpiredException;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.UserIdException;
import com.liferay.portal.UserLockoutException;
import com.liferay.portal.UserPasswordException;
import com.liferay.portal.UserScreenNameException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.xcolab.portlets.loginregister.singlesignon.SSOKeys;

@Controller
@RequestMapping(value = "view", params = "isLoggingIn=true")
public class LoginController {
    public static final long companyId = 10112L;

    @ActionMapping
    public void doLogin(ActionRequest request, ActionResponse response) throws IOException {
    	
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(
                WebKeys.THEME_DISPLAY);

        HttpServletRequest httpRequest = PortletUtils.getOryginalRequest(request);

        String redirect = httpRequest.getParameter("redirect");
        String referer = httpRequest.getHeader("referer");
        redirect = !StringUtils.isBlank(redirect) ? redirect : referer;

        redirect = !StringUtils.isBlank(redirect) ? redirect : themeDisplay.getURLHome();


        redirect = Helper.removeParamFromRequestStr(redirect, "signinRegError");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSigningInPopup");
        redirect = Helper.removeParamFromRequestStr(redirect, "isSigningIn");
        redirect = Helper.removeParamFromRequestStr(redirect, "isRegistering");
        redirect = Helper.removeParamFromRequestStr(redirect, "isPasswordReminder");

        //if (redirect == null || redirect.trim().length() == 0) {
            redirect = PortalUtil.getHttpServletRequest(request).getHeader("referer");
        //}

        User user = null;
        try {
            String login = request.getParameter("login");
            if (! StringUtils.isBlank(login) && login.contains("@")) {
                // search for user by email and after that take his/her screen name for logging in
                user = UserLocalServiceUtil.getUserByEmailAddress(companyId, login);
                
                if (user != null) {
                    login = user.getScreenName();
                }
            }

    		AuthenticationServiceUtil.logUserIn(request, response, login, request.getParameter("password"));
            //LoginUtil.logUserIn(request, response, login, request.getParameter("password"));

            if (user == null) {
                user = UserLocalServiceUtil.getUserByScreenName(companyId, login);
            }

			LoginLogLocalServiceUtil.createLoginLog(user, PortalUtil.getHttpServletRequest(request).getRemoteAddr(), referer);

        } catch (Exception e) {
            if (e instanceof AuthException) {
                Throwable cause = e.getCause();

                if (cause instanceof PasswordExpiredException ||
                        cause instanceof UserLockoutException) {

                    SessionErrors.add(
                            request, cause.getClass().getName());
                } else {
                    SessionErrors.add(request, e.getClass().getName());
                }
            } else if (e instanceof CookieNotSupportedException ||
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
                PortalUtil.sendError(e, request, response);
            }
        }

        if (user != null) {
            try {
                //display message if user is in the ignored recipients list
                MessagingIgnoredRecipients r = MessagingIgnoredRecipientsLocalServiceUtil.findByEmail(user.getEmailAddress());
                if(r.getUserId() == 0) {
                    redirect = "/web/guest/member/-/member/userId/" + user.getUserId();
                }
            } catch (NoSuchMessagingIgnoredRecipientsException e) { /* Case is fine, user is not in ignored rec. list */  }
            catch (SystemException e ) { _log.error("System exception while trying to log user in", e); }
        }

        
        if (!SessionErrors.isEmpty(request)) {
            // url parameters
            Map<String, String> parameters = new HashMap<String, String>();
            //boolean isSigningInPopup = ParamUtil.getBoolean(actionRequest, "isSigningInPopup");

            parameters.put("isSigningIn", "true");

            redirect = Helper.modifyRedirectUrl(redirect, request, parameters);

        }

        SessionErrors.clear(request);
        SessionMessages.clear(request);

        BalloonCookie bc = BalloonCookie.fromCookieArray(request.getCookies());
        if (StringUtils.isNotBlank(bc.getUuid()) && Validator.isNotNull(user)) {
        	// cookie is present, get BalloonUserTracking if it exists and update association to the current user
        	try {
        		BalloonUserTracking but = BalloonUserTrackingLocalServiceUtil.getBalloonUserTracking(bc.getUuid());
        		if (but == null) {
        			List<BalloonUserTracking> buts = BalloonUserTrackingLocalServiceUtil.findByEmail(user.getEmailAddress());
        			if (! buts.isEmpty()) {
        				but = buts.get(0);
        			}
        		}

        		if (but != null && user != null && but.getUserId() != user.getUserId()) {
        			but.setUserId(user.getUserId());
        			BalloonUserTrackingLocalServiceUtil.updateBalloonUserTracking(but);
        		}
        	}
        	catch (NoSuchBalloonUserTrackingException e) {
        		// ignore
        	} catch (SystemException e) {
        		// ignore
			} catch (PortalException e) {
        		// ignore
			}
        }

        response.sendRedirect(redirect);
    }
    
    private final static Log _log = LogFactoryUtil.getLog(LoginController.class);
}
