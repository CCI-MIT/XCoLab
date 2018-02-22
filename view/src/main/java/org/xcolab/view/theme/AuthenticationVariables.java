package org.xcolab.view.theme;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.auth.login.AuthenticationError;

import javax.servlet.http.HttpServletRequest;

public class AuthenticationVariables {
    public boolean isLoggedIn;
    public boolean isImpersonating;
    public Member realMember;
    public Member member;
    public boolean isAdmin;

    public boolean isGoogleSsoActive;
    public boolean isFacebookSsoActive;
    public String facebookId;
    public boolean showLoginPopup;
    public AuthenticationError authError;
    public boolean showPasswordResetPopup;
    public boolean showSsoPopup;

    public AuthenticationVariables(AuthenticationService authenticationService,
                          HttpServletRequest request) {
        this.isLoggedIn = authenticationService.isLoggedIn();

        this.isImpersonating = authenticationService.isImpersonating(request);
        if (isImpersonating) {
            this.realMember = authenticationService.getRealMemberOrNull();
        }

        if (isLoggedIn) {
            this.member = authenticationService.getMemberOrThrow(request);
            this.isAdmin = PermissionsClient.canAdminAll(member.getUserId());
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
}
