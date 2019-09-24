package org.xcolab.view.pages.profile.utils;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

public class UserProfilePermissions {

    private final UserWrapper loggedInMember;
    private final boolean isLoggedIn;

    public UserProfilePermissions(UserWrapper loggedInMember) {
        this.loggedInMember = loggedInMember;
        isLoggedIn = loggedInMember != null;
    }

    private long getLoggedInuserId() {
        return isLoggedIn ? loggedInMember.getId() : 0;
    }

    public boolean getCanAdmin() {
        return StaticUserContext.getPermissionClient().canAdminAll(loggedInMember);
    }

    public UserWrapper getLoggedInMember() {
        return loggedInMember;
    }

    public boolean getCanEditMemberProfile(long userId) {
        return isLoggedIn && getLoggedInuserId() == userId || getCanAdmin();
    }

}
