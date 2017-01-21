package org.xcolab.view.pages.members.users.utils;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.auth.MemberAuthUtil;

import javax.servlet.http.HttpServletRequest;

public class MembersPermissions {

    private final long memberId;

    public MembersPermissions(HttpServletRequest request) {
        memberId = MemberAuthUtil.getMemberId(request);
    }

    public boolean getCanDownloadMemberList() {
        return getCanAdminAll();
    }

    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(memberId);
    }
}
