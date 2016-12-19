package org.xcolab.portlets.contestmanagement.utils;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.enums.MemberRole;
import org.xcolab.interfaces.TabPermissions;

import javax.portlet.PortletRequest;

//TODO: what's the difference to ContestManagementPermissions?
public class ContestPermissions implements TabPermissions {
    private final Contest contestWrapper;

    private final long memberId;
    private final boolean isLoggedIn;

    public ContestPermissions(PortletRequest request, Contest contest) {

        memberId = MemberAuthUtil.getMemberId(request);
        isLoggedIn = memberId > 0;
        contestWrapper = (contest);
    }

    @Override
    public boolean getCanRole(MemberRole role) {
        return isLoggedIn && contestWrapper
                .getHasUserRoleInContest(memberId, role.getPrintName());
    }

    @Override
    public boolean getIsOwner() {
        return isLoggedIn && contestWrapper.getAuthorId() == memberId;
    }

    @Override
    public boolean getCanAdmin() {
        return PermissionsClient.canAdminAll(memberId);
    }

    @Override
    public boolean getCanStaff() {
        if (!isLoggedIn) {
            return false;
        }
        return MembersClient.hasMemberRole(memberId, MemberRole.STAFF.getRoleId());
    }

    public boolean getCanEdit() {
        return isLoggedIn;
    }

    @Override
    public boolean getCanDelete() {
        return isLoggedIn;
    }

    @Override
    public boolean getCanCreate() {
        // TODO check who needs this
        return isLoggedIn;
    }

    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(memberId);
    }
}
