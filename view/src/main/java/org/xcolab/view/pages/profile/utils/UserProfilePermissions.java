package org.xcolab.view.pages.profile.utils;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;

public class UserProfilePermissions {

    private final Member loggedInMember;
    private final boolean isLoggedIn;

    public UserProfilePermissions(Member loggedInMember) {
        this.loggedInMember = loggedInMember;
        isLoggedIn = loggedInMember != null;
    }

    private long getLoggedInMemberId() {
        return isLoggedIn ? loggedInMember.getId_() : 0;
    }

    public boolean getCanAdminProfile(long memberId) {
        return isLoggedIn && getLoggedInMemberId() == memberId || getCanAdmin();
    }

    public boolean getCanAdmin() {
        return PermissionsClient.canAdminAll(loggedInMember);
    }

    public boolean getCanEditMemberProfile(long memberId) {
        return isLoggedIn && getLoggedInMemberId() == memberId || getCanAdmin();
    }

}
