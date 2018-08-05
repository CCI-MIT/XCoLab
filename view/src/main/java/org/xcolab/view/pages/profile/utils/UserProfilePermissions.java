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

    private long getLoggedInuserId() {
        return isLoggedIn ? loggedInMember.getId() : 0;
    }

    public boolean getCanAdmin() {
        return PermissionsClient.canAdminAll(loggedInMember);
    }

    public Member getLoggedInMember() {
        return loggedInMember;
    }

    public boolean getCanEditMemberProfile(long userId) {
        return isLoggedIn && getLoggedInuserId() == userId || getCanAdmin();
    }

}
