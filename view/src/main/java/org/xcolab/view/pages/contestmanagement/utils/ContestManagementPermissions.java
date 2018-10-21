package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.legacy.enums.SystemRole;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.taglibs.xcolab.interfaces.TabPermissions;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class ContestManagementPermissions implements TabPermissions, Serializable {

    private final boolean isLoggedIn;
    private final long userId;

    public ContestManagementPermissions(HttpServletRequest request) {
        userId = MemberAuthUtil.getuserId(request);
        isLoggedIn = userId > 0;
    }

    @Override
    public boolean getCanAdmin() {
        return PermissionsClient.canAdminAll(userId);
    }

    @Override
    public boolean getCanRole(SystemRole role) {
        return isLoggedIn && PermissionsClient.memberHasRole(userId, role.getRoleId());
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
