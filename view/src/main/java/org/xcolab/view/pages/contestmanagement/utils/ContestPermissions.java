package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.permissions.SystemRole;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.taglibs.xcolab.interfaces.TabPermissions;

import java.io.Serializable;

public class ContestPermissions implements TabPermissions, Serializable {

    private final Contest contestWrapper;

    private final long userId;
    private final boolean isLoggedIn;

    public ContestPermissions(Contest contest) {
        userId = MemberAuthUtil.getUserId();
        isLoggedIn = userId > 0;
        contestWrapper = (contest);
    }

    @Override
    public boolean getCanAdmin() {
        return PermissionsClient.canAdminAll(userId);
    }

    @Override
    public boolean getCanRole(SystemRole role) {
        return isLoggedIn && contestWrapper
                .getHasUserRoleInContest(userId, role.getRoleId());
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
        return isLoggedIn;
    }

    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(userId);
    }
}
