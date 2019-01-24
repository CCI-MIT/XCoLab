package org.xcolab.view.pages.members.users.utils;

import org.xcolab.client.user.PermissionsClient;
import org.xcolab.view.auth.MemberAuthUtil;

public class MembersPermissions {

    private final long userId;

    public MembersPermissions() {
        userId = MemberAuthUtil.getUserId();
    }

    public boolean getCanDownloadMemberList() {
        return getCanAdminAll();
    }

    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(userId);
    }
}
