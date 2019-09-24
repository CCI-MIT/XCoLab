package org.xcolab.view.pages.loginregister;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.activity.IActivityClient;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.tracking.IBalloonClient;
import org.xcolab.client.tracking.exceptions.BalloonUserTrackingNotFoundException;
import org.xcolab.client.tracking.pojo.IBalloonUserTracking;
import org.xcolab.client.user.ILoginTokenClient;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.IUserLoginRegisterClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.LoginTokenWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.entity.utils.notifications.member.MemberBatchRegistrationNotification;
import org.xcolab.entity.utils.notifications.member.MemberRegistrationNotification;
import org.xcolab.util.activities.enums.MemberActivityType;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.pages.redballoon.utils.BalloonCookie;
import org.xcolab.view.util.googleanalytics.GoogleAnalyticsEventType;
import org.xcolab.view.util.googleanalytics.GoogleAnalyticsUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginRegisterService {

    private static final Logger _log = LoggerFactory.getLogger(LoginRegisterService.class);

    private final AuthenticationService authenticationService;
    private final IBalloonClient balloonClient;
    private final IActivityClient activityClient;
    private final IUserClient userClient;
    private final IUserLoginRegisterClient userLoginRegister;
    private final ILoginTokenClient loginTokenClient;

    @Autowired
    public LoginRegisterService(AuthenticationService authenticationService,
                                IBalloonClient balloonClient, IActivityClient activityClient,
                                IUserClient userClient,
                                IUserLoginRegisterClient userLoginRegister,
                                ILoginTokenClient loginTokenClient) {
        this.authenticationService = authenticationService;
        this.balloonClient = balloonClient;
        this.activityClient = activityClient;
        this.userClient = userClient;
        this.userLoginRegister = userLoginRegister;
        this.loginTokenClient = loginTokenClient;
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

        final UserWrapper member = register(newAccountBean.getScreenName(), newAccountBean.getPassword(),
                newAccountBean.getEmail(), newAccountBean.getFirstName(),
                newAccountBean.getLastName(), newAccountBean.getShortBio(),
                newAccountBean.getCountry(), newAccountBean.getImageId(),
                false, newAccountBean.getLanguage());

        authenticationService.authenticate(request, response, member);
        updateBalloonTracking(member, request);
        recordRegistrationEvent(member);

        if (redirect == null) {
            redirect = "/";
        }

        if (postRegistration) {
            // Add request variable for after-registration popover
            redirect = UriComponentsBuilder.fromUriString(redirect)
                    .queryParam("postRegistration", true)
                    .build().toUriString();
        }

        //Make URI relative to prevent injection of external redirect URIs
        redirect = LinkUtils.getRelativeUri(redirect);

        if (!redirect.startsWith("/login") && !redirect.startsWith("/sso/")) {
            response.sendRedirect(redirect);
        } else {
            response.sendRedirect("/");
        }
    }

    private void updateBalloonTracking(UserWrapper member, HttpServletRequest request) {
        Optional<BalloonCookie> balloonCookieOpt = BalloonCookie.from(request.getCookies());
        if (balloonCookieOpt.isPresent()) {
            BalloonCookie balloonCookie = balloonCookieOpt.get();
            try {
                IBalloonUserTracking but =
                        balloonClient.getBalloonUserTracking(balloonCookie.getUuid());
                if (but.getUserId() == null) {
                    but.setRegistrationDate(new Timestamp(new Date().getTime()));
                    but.setUserId(member.getId());
                    balloonClient.updateBalloonUserTracking(but, member.getUuid());
                }
            } catch (BalloonUserTrackingNotFoundException e) {
                _log.error("Can't find balloon user tracking for uuid: {}",
                        balloonCookie.getUuid());
            }
        }
        //update user association for all BUTs under this email address
        balloonClient.listBalloonUserTrackings(member.getEmailAddress(), null).forEach(
                but -> {
                    balloonClient.updateUserIdAndEmailIfEmpty(but, member.getId(),
                            member.getEmailAddress());
                });
    }

    public void recordRegistrationEvent(UserWrapper member) {
        activityClient.createActivityEntry(MemberActivityType.REGISTERED, member.getId(),
                member.getId());

        if (member.getFacebookId() != null) {
            GoogleAnalyticsUtils.pushEventAsync(GoogleAnalyticsEventType.REGISTRATION_SSO_FACEBOOK);
            return;
        }
        if (member.getGoogleId() != null) {
            GoogleAnalyticsUtils.pushEventAsync(GoogleAnalyticsEventType.REGISTRATION_SSO_GOOGLE);
        }
        GoogleAnalyticsUtils.pushEventAsync(GoogleAnalyticsEventType.REGISTRATION_FORM);
    }

    public void updatePassword(String forgotPasswordToken, String newPassword)
            throws MemberNotFoundException {
        Long userId = userLoginRegister.updateForgottenPasswordByToken(forgotPasswordToken, newPassword);
        if (userId == null) {
            throw new MemberNotFoundException(
                    "Member with forgotPasswordToken: " + forgotPasswordToken + " was not found");
        }
    }

    public UserWrapper autoRegister(String emailAddress, String firstName, String lastName) {
        return register(null,UUID.randomUUID().toString(), emailAddress, firstName, lastName,
                "", null, null, true, null);
    }

    public UserWrapper register(String screenName, String password, String email, String firstName,
            String lastName, String shortBio, String country, Long imageId,
            boolean generateLoginUrl, String language) {

        Assert.notNull(email, "Email address is required");
        Assert.notNull(email, "First name is required");
        Assert.notNull(email, "Last name is required");

        UserWrapper member = new UserWrapper();
        if (screenName == null) {
            member.setScreenName(userLoginRegister.generateScreenName(lastName, firstName));
        } else {
            member.setScreenName(screenName);
        }
        member.setEmailAddress(email);
        member.setFirstName(firstName);
        member.setHashedPassword(password);
        member.setLastName(lastName);
        member.setDefaultLocale(language != null ? language : I18nUtils.DEFAULT_LANGUAGE);
        member.setStatus(0);
        member.setIsEmailConfirmed(false);
        member.setIsEmailBounced(false);

        final String baseUri = PlatformAttributeKey.COLAB_URL.get();
        member.setShortBio(HtmlUtil.cleanSome(shortBio, baseUri));
        member.setCountry(country);
        if (imageId != null) {
            member.setPortraitFileEntryId(imageId);
        } else {
            member.setPortraitFileEntryId(0L);
        }
        member = userClient.register(member);

        if (generateLoginUrl) {
            final LoginTokenWrapper loginToken = loginTokenClient.createLoginToken(member.getId());
            new MemberBatchRegistrationNotification(member, loginToken).sendEmailNotification();
        } else {
            new MemberRegistrationNotification(member).sendEmailNotification();
        }

        return member;
    }
}
