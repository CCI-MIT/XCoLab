package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.taglibs.xcolab.interfaces.TabPermissions;
import org.xcolab.view.util.entity.enums.MemberRole;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class ContestManagementPermissions implements TabPermissions, Serializable {

    private final boolean isLoggedIn;
    private final long memberId;

    public ContestManagementPermissions(HttpServletRequest request) {
        memberId = MemberAuthUtil.getMemberId(request);
        isLoggedIn = memberId > 0;
    }

    @Override
    public boolean getCanAdmin() {
        return PermissionsClient.canAdminAll(memberId);
    }

    @Override
    public boolean getCanRole(MemberRole role) {
        return isLoggedIn && PermissionsClient.memberHasRole(memberId, role.getRoleId());
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
