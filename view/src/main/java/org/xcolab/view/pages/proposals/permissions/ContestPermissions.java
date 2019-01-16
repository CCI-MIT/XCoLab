package org.xcolab.view.pages.proposals.permissions;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.user.PermissionsClient;
import org.xcolab.client.user.pojo.Member;

public class ContestPermissions {

    private final Member loggedInMember;

    public ContestPermissions(Member loggedInMember) {
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
        return PermissionsClient.hasRoleGroup(userId, contestType.getRoleGroup());
    }

    public boolean getCanAccessContest(Contest contest) {
        ContestType contestType = contest.getContestType();
        if (!contestType.isRestrictedAccess()) {
            return true;
        }
        if (loggedInMember == null) {
            return false;
        }
        final long userId = loggedInMember.getId();
        return PermissionsClient.hasRoleGroup(userId, contestType.getRoleGroup());
    }
}
