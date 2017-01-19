package org.xcolab.view.pages.profile.utils;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.auth.MemberAuthUtil;

import javax.servlet.http.HttpServletRequest;

public class UserProfilePermissions {

    private final long memberId;

    public UserProfilePermissions(HttpServletRequest request) {
        memberId = MemberAuthUtil.getMemberId(request);
    }

    public boolean getCanAdminProfile(long memberId) {
        return this.memberId == memberId || getCanAdmin();
    }

    public boolean getCanAdmin() {
        return PermissionsClient.canAdminAll(memberId);
    }

    public long getCurrentMemberId() {
        return memberId;
    }

    public void checkCanAdminSpamReports() throws UserProfileAuthorizationException {
        if (!getCanAdminSpamReports()) {
            throw new UserProfileAuthorizationException("Admin Spam Report permissions required");
        }
    }

    public boolean getCanEditMemberProfile(long memberId) {
        return this.memberId == memberId || getCanAdmin();
    }

    public boolean getCanAdminSpamReports() {
        return getCanAdmin();
    }
}
