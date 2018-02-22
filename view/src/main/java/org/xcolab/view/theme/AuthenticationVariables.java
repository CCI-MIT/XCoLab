package org.xcolab.view.theme;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.auth.login.AuthenticationError;

import javax.servlet.http.HttpServletRequest;

public class AuthenticationVariables {
    final private boolean isLoggedIn;
    final private boolean isImpersonating;
    private Member realMember;
    private Member member;
    private boolean isAdmin;

    final private boolean isGoogleSsoActive;
    final private boolean isFacebookSsoActive;
    final private String facebookId;
    final private boolean showLoginPopup;
    private AuthenticationError authError;
    final private boolean showPasswordResetPopup;
    final private boolean showSsoPopup;

    public AuthenticationVariables(AuthenticationService authenticationService,
                          HttpServletRequest request) {
        this.isLoggedIn = authenticationService.isLoggedIn();

        this.isImpersonating = authenticationService.isImpersonating(request);
        if (isImpersonating()) {
            this.realMember = authenticationService.getRealMemberOrNull();
        }

        if (isLoggedIn()) {
            this.member = authenticationService.getMemberOrThrow(request);
            this.isAdmin = PermissionsClient.canAdminAll(getMember().getUserId());
        }

        boolean isSigningIn = readBooleanParameter(request, "isSigningIn");
        boolean isPasswordReminder = readBooleanParameter(request, "isPasswordReminder");
        boolean isSSOSigningIn = readBooleanParameter(request, "isSSOSigningIn");
        if (isSigningIn) {
            this.authError
                    = AuthenticationError.fromName(request.getParameter("signinRegError"));
        }

        this.isGoogleSsoActive = ConfigurationAttributeKey.GOOGLE_SSO_IS_ACTIVE.get();
        this.isFacebookSsoActive = ConfigurationAttributeKey.FACEBOOK_SSO_IS_ACTIVE.get();
        this.facebookId = ConfigurationAttributeKey.FACEBOOK_APPLICATION_ID.get();

        this.showLoginPopup = isSigningIn;
        this.showPasswordResetPopup = isPasswordReminder;
        this.showSsoPopup = isSSOSigningIn;
    }

    private boolean readBooleanParameter(HttpServletRequest request, String name) {
        return Boolean.parseBoolean(request.getParameter(name));
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public boolean isImpersonating() {
        return isImpersonating;
    }

    public Member getRealMember() {
        return realMember;
    }

    public Member getMember() {
        return member;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isGoogleSsoActive() {
        return isGoogleSsoActive;
    }

    public boolean isFacebookSsoActive() {
        return isFacebookSsoActive;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public boolean isShowLoginPopup() {
        return showLoginPopup;
    }

    public AuthenticationError getAuthError() {
        return authError;
    }

    public boolean isShowPasswordResetPopup() {
        return showPasswordResetPopup;
    }

    public boolean isShowSsoPopup() {
        return showSsoPopup;
    }
}
