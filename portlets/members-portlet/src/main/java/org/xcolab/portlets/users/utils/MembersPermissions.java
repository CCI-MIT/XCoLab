package org.xcolab.portlets.users.utils;

import org.xcolab.client.members.PermissionsClient;

import javax.portlet.PortletRequest;

public class MembersPermissions {

    private Long memberId;

    public MembersPermissions(PortletRequest request) {
        try {
            memberId = Long.parseLong(request.getRemoteUser());
        } catch (NumberFormatException e) {
            memberId = null;
        }
    }

    public boolean getCanDownloadMemberList() {
        return isLoggedId() && PermissionsClient.canAdminAll(memberId);
    }

    private boolean isLoggedId() {
        return memberId != null;
    }
}
