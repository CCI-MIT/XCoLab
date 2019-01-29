package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.permissions.SystemRole;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.taglibs.xcolab.interfaces.TabPermissions;

import java.io.Serializable;

public class ContestManagementPermissions implements TabPermissions, Serializable {

    private final boolean isLoggedIn;
    private final long userId;

    public ContestManagementPermissions() {
        userId = MemberAuthUtil.getUserId();
        isLoggedIn = userId > 0;
    }

    @Override
    public boolean getCanAdmin() {
        return StaticUserContext.getPermissionClient().canAdminAll(userId);
    }

    @Override
    public boolean getCanRole(SystemRole role) {
        return isLoggedIn && StaticUserContext.getPermissionClient().memberHasRole(userId, role.getRoleId());
    }

    @Override
    public boolean getCanDelete() {
        return isLoggedIn;
    }

    @Override
    public boolean getCanCreate() {
        return isLoggedIn;
    }

    public boolean getCanEdit() {
        return getCanAdmin();
    }
}
