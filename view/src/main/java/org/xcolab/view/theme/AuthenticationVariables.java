package org.xcolab.view.theme;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.auth.login.AuthenticationError;
import org.xcolab.view.config.spring.beans.SsoClientConfig.SsoServices;

import javax.servlet.http.HttpServletRequest;

public class AuthenticationVariables {
    private final boolean isLoggedIn;
    private final boolean isImpersonating;
    private final UserWrapper realMember;
    private final UserWrapper member;
    private final boolean isAdmin;

    private final boolean isGoogleSsoActive;
    private final boolean isClimateXSsoActive;
    private final boolean isFacebookSsoActive;
    private final String facebookId;
    private final boolean showLoginPopup;
    private final AuthenticationError authError;
    private final boolean showPasswordResetPopup;
    private final boolean showSsoPopup;

    public AuthenticationVariables(AuthenticationService authenticationService,
            SsoServices ssoServices, HttpServletRequest request) {
        this.isLoggedIn = authenticationService.isLoggedIn();

        this.isImpersonating = authenticationService.isImpersonating();
        this.realMember = authenticationService.getRealMemberOrNull();

        this.member = authenticationService.getMemberOrNull(request);
        this.isAdmin = StaticUserContext.getPermissionClient().canAdminAll(getMember());

        boolean isSigningIn = readBooleanParameter(request, "isSigningIn");
        boolean isPasswordReminder = readBooleanParameter(request, "isPasswordReminder");
        boolean isSSOSigningIn = readBooleanParameter(request, "isSSOSigningIn");
        this.authError = AuthenticationError.fromName(request.getParameter("signinRegError"));

        this.isGoogleSsoActive = ssoServices.isGoogleEnabled();
        this.isClimateXSsoActive = ssoServices.isClimateXEnabled();
        this.isFacebookSsoActive = ssoServices.isFacebookEnabled();
        this.facebookId = ConfigurationAttributeKey.FACEBOOK_APPLICATION_ID.get();

        this.showLoginPopup = isSigningIn;
        this.showPasswordResetPopup = isPasswordReminder;
        this.showSsoPopup = isSSOSigningIn;
    }

    private boolean readBooleanParameter(HttpServletRequest request, String name) {
        return Boolean.parseBoolean(request.getParameter(name));
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public boolean getIsImpersonating() {
        return isImpersonating;
    }

    public UserWrapper getRealMember() {
        return realMember;
    }

    public UserWrapper getMember() {
        return member;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public boolean getIsGoogleSsoActive() {
        return isGoogleSsoActive;
    }

    public boolean getIsFacebookSsoActive() {
        return isFacebookSsoActive;
    }

    public boolean getIsClimateXSsoActive() {
        return isClimateXSsoActive;
    }

    public boolean getIsAnySsoActive() {
        return isGoogleSsoActive || isFacebookSsoActive || isClimateXSsoActive;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public boolean getShowLoginPopup() {
        return showLoginPopup;
    }

    public AuthenticationError getAuthError() {
        return authError;
    }

    public boolean getShowPasswordResetPopup() {
        return showPasswordResetPopup;
    }

    public boolean getShowSsoPopup() {
        return showSsoPopup;
    }
}
