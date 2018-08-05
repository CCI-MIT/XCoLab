package org.xcolab.view.pages.members.users.utils;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.auth.MemberAuthUtil;

import javax.servlet.http.HttpServletRequest;

public class MembersPermissions {

    private final long userId;

    public MembersPermissions(HttpServletRequest request) {
        userId = MemberAuthUtil.getuserId(request);
    }

    public boolean getCanDownloadMemberList() {
        return getCanAdminAll();
    }

    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(userId);
    }
}
