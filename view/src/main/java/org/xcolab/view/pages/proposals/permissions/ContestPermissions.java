package org.xcolab.view.pages.proposals.permissions;

import org.xcolab.client.admin.pojo.ContestType;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

public class ContestPermissions {

    private final UserWrapper loggedInMember;

    public ContestPermissions(UserWrapper loggedInMember) {
        this.loggedInMember = loggedInMember;
    }

    public boolean getCanAccessContests(ContestType contestType) {
        if (!contestType.isRestrictedAccess()) {
            return true;
        }
        if (loggedInMember == null) {
            return false;
        }
        final long userId = loggedInMember.getId();
        return StaticUserContext.getPermissionClient().hasRoleGroup(userId, contestType.getRoleGroup());
    }

    public boolean getCanAccessContest(ContestWrapper contest) {
        ContestType contestType = contest.getContestType();
        if (!contestType.isRestrictedAccess()) {
            return true;
        }
        if (loggedInMember == null) {
            return false;
        }
        final long userId = loggedInMember.getId();
        return StaticUserContext.getPermissionClient().hasRoleGroup(userId, contestType.getRoleGroup());
    }
}
