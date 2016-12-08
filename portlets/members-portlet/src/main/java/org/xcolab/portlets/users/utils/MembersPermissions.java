package org.xcolab.portlets.users.utils;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.entity.utils.members.MemberAuthUtil;

import javax.portlet.PortletRequest;

public class MembersPermissions {

    private final long memberId;

    public MembersPermissions(PortletRequest request) {
        memberId = MemberAuthUtil.getMemberId(request);
    }

    public boolean getCanDownloadMemberList() {
        return PermissionsClient.canAdminAll(memberId);
    }
}
