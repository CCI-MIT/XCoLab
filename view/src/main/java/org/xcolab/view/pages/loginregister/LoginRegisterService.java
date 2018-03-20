package org.xcolab.view.pages.loginregister;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFoundException;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.LockoutLoginException;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.exceptions.PasswordLoginException;
import org.xcolab.client.members.pojo.LoginToken;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.entity.utils.notifications.member.MemberBatchRegistrationNotification;
import org.xcolab.entity.utils.notifications.member.MemberRegistrationNotification;
import org.xcolab.util.activities.enums.MemberActivityType;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.auth.handlers.AuthenticationSuccessHandler;
import org.xcolab.view.pages.loginregister.singlesignon.SSOKeys;
import org.xcolab.view.pages.redballoon.utils.BalloonCookie;
import org.xcolab.view.util.googleanalytics.GoogleAnalyticsEventType;
import org.xcolab.view.util.googleanalytics.GoogleAnalyticsUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class LoginRegisterService {

    private static final Logger _log = LoggerFactory.getLogger(LoginRegisterService.class);

    private final AuthenticationService authenticationService;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public LoginRegisterService(AuthenticationService authenticationService,
            AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationService = authenticationService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    /**
     * Completes the user registration with the parameters set in the CreateUserBean
     *
     * @param request The HttpServletRequest object
     * @param response The HttpServletResponse object
     * @param newAccountBean The new user bean object
     * @param redirect Redirect URL for this request (may be null)
     */
    public void completeRegistration(HttpServletRequest request, HttpServletResponse response,
            CreateUserBean newAccountBean, String redirect, boolean postRegistration)
            throws IOException {
        HttpSession session = request.getSession();
        String fbIdString = (String) session.getAttribute(SSOKeys.FACEBOOK_USER_ID);
        String googleId = (String) session.getAttribute(SSOKeys.SSO_GOOGLE_ID);

        final Member member = register(newAccountBean.getScreenName(), newAccountBean.getPassword(),
                newAccountBean.getEmail(), newAccountBean.getFirstName(),
                newAccountBean.getLastName(), newAccountBean.getShortBio(),
                newAccountBean.getCountry(), fbIdString, googleId, newAccountBean.getImageId(),
                false, newAccountBean.getLanguage());

        // SSO
        if (StringUtils.isNotBlank(fbIdString)) {
            session.removeAttribute(SSOKeys.FACEBOOK_USER_ID);
        }
        if (googleId != null) {
            session.removeAttribute(SSOKeys.SSO_GOOGLE_ID);
        }
        Optional<BalloonCookie> balloonCookieOpt = BalloonCookie.from(request.getCookies());
        if (balloonCookieOpt.isPresent()) {
            BalloonCookie balloonCookie = balloonCookieOpt.get();
            try {
                BalloonUserTracking but =
                        BalloonsClient.getBalloonUserTracking(balloonCookie.getUuid());
                if (but.getUserId() == null) {
                    but.setRegistrationDate(new Timestamp(new Date().getTime()));
                    but.setUserId(member.getId_());
                    BalloonsClient.updateBalloonUserTracking(but);
                }
            } catch (BalloonUserTrackingNotFoundException e) {
                _log.error("Can't find balloon user tracking for uuid: {}",
                        balloonCookie.getUuid());
            }
        }
        //update user association for all BUTs under this email address
        BalloonsClient.getBalloonUserTrackingByEmail(member.getEmailAddress()).forEach(
                b -> b.updateUserIdAndEmailIfEmpty(member.getId_(), member.getEmailAddress()));

        try {
            checkLogin(request, response, newAccountBean.getScreenName(),
                    newAccountBean.getPassword(), redirect);
        } catch (MemberNotFoundException | PasswordLoginException | LockoutLoginException e) {
            throw new InternalException(e);
        }

        session.setAttribute("collab_user_has_registered", true);

        ActivitiesClientUtil.createActivityEntry(MemberActivityType.REGISTERED, member.getUserId(),
                member.getUserId());

        sendGoogleAnalytics(fbIdString, googleId, session.getAttribute("isSsoLogin"));


        if (redirect == null) {
            redirect = "/";
        }

        if (postRegistration) {
            // Add request variable for after-registration popover
            redirect = UriComponentsBuilder.fromUriString(redirect)
                    .queryParam("postRegistration", true)
                    .build().toUriString();
        }

        response.sendRedirect(redirect);
    }

    private void sendGoogleAnalytics(String fbIdString, String googleId, Object isSsoLogin) {
        if (fbIdString == null && googleId == null) {
            GoogleAnalyticsUtils.pushEventAsync(GoogleAnalyticsEventType.REGISTRATION_FORM);
            return;
        }
        if (isSsoLogin != null) {
            GoogleAnalyticsUtils.pushEventAsync(GoogleAnalyticsEventType.REGISTRATION_COMPLETE_PROFILE);
            return;
        }
        if (fbIdString != null) {
            GoogleAnalyticsUtils.pushEventAsync(GoogleAnalyticsEventType.REGISTRATION_SSO_FACEBOOK);
            return;
        }
        if (googleId != null) {
            GoogleAnalyticsUtils.pushEventAsync(GoogleAnalyticsEventType.REGISTRATION_SSO_GOOGLE);
        }
    }

    public void updatePassword(String forgotPasswordToken, String newPassword)
            throws MemberNotFoundException {
        Long memberId = MembersClient.updateUserPassword(forgotPasswordToken, newPassword);
        if (memberId == null) {
            throw new MemberNotFoundException(
                    "Member with forgotPasswordToken: " + forgotPasswordToken + " was not found");
        }
    }

    public Member autoRegister(String emailAddress, String firstName, String lastName) {
        return register(MembersClient.generateScreenName(lastName, firstName), null, emailAddress,
                firstName, lastName, "", null, null, null, null, true, "en");
    }

    public Member register(String screenName, String password, String email, String firstName,
            String lastName, String shortBio, String country, String fbIdString, String googleId,
            String imageId, boolean generateLoginUrl, String language) {

        Long memberId = SharedColabClient
                .retrieveSharedId(email, screenName, ConfigurationAttributeKey.COLAB_NAME.get());

        Member member = new Member();
        member.setId_(memberId);
        member.setScreenName(screenName);
        member.setEmailAddress(email);
        member.setFirstName(firstName);
        member.setHashedPassword(password);
        member.setLastName(lastName);
        member.setGoogleId(googleId);
        member.setDefaultLocale(language);
        try {
            member.setFacebookId(Long.parseLong(fbIdString));
        } catch (NumberFormatException ignored) {
        }

        final String baseUri = PlatformAttributeKey.COLAB_URL.get();
        member.setShortBio(HtmlUtil.cleanSome(shortBio, baseUri));
        member.setCountry(country);
        member.setPortraitFileEntryId(0L);
        MembersClient.register(member);
        member = MembersClient.getMemberUnchecked(member.getId_());

        if (imageId != null && !imageId.isEmpty()) {
            member.setPortraitFileEntryId(Long.parseLong(imageId));
            MembersClient.updateMember(member);
        } else {
            member.setPortraitFileEntryId(0L);
            MembersClient.updateMember(member);
        }
        if (generateLoginUrl) {
            final LoginToken loginToken = MembersClient.createLoginToken(memberId);
            new MemberBatchRegistrationNotification(member, loginToken).sendEmailNotification();
        } else {
            new MemberRegistrationNotification(member).sendEmailNotification();
        }

        return member;
    }

    public Member checkLogin(HttpServletRequest request, HttpServletResponse response, String login,
            String password)
            throws MemberNotFoundException, PasswordLoginException, LockoutLoginException {
        return checkLogin(request, response, login, password, "");
    }

    //TODO COLAB-2619: rethink name and semantics
    public Member checkLogin(HttpServletRequest request, HttpServletResponse response, String login,
            String password, String referer)
            throws MemberNotFoundException, PasswordLoginException, LockoutLoginException {
        if (StringUtils.isBlank(login)) {
            return null;
        }
        final String screenName = getScreenNameFromLogin(login);
        Member member = MembersClient.findMemberByScreenNameNoRole(screenName);
        boolean loggedIn =
                MembersClient.login(member.getId_(), password, request.getRemoteAddr(), referer);
        if (loggedIn) {
            authenticationService.authenticate(request, response, member);
        }
        return member;
    }

    public void logIn(HttpServletRequest request, HttpServletResponse response, Member member) {
        try {
            Authentication authentication =
                    authenticationService.authenticate(request, response, member);
            final boolean redirectOnSuccess = false;
            authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication,
                    redirectOnSuccess);
        } catch (IOException e) {
            authenticationService.logout(request, response);
            throw new InternalException(e);
        }
    }

    private String getScreenNameFromLogin(String login) throws MemberNotFoundException {
        if (login.contains("@")) {
            Member member = MembersClient.findMemberByEmailAddress(login);
            return member.getScreenName();
        }
        return login;
    }

}

