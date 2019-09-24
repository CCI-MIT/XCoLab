package org.xcolab.client.user.permissions;

/**
 * Represents a role anchored in the system.
 *
 * These roles must be present in all colabs and are used directly in code.
 * Other roles may be added in the user__role and user__member_category tables.
 *
 */
public enum SystemRole {
    /**
     * Important:
     * Whenever these roles are modified (which should never happen) these Ids should be updated as well
     */
    GUEST(10119L),
    MEMBER(10122L),
    FELLOW(193261L),
    ADVISOR(193260L),
    EXPERT(44201L),
    JUDGE(1251483L),
    STAFF(31704L),
    ADMINISTRATOR(10118L),
    MODERATOR(31213L),
    CATALYST(1430078L),
    CONTEST_MANAGER(1958405L),
    IMPACT_ASSESSMENT_FELLOW(1975251L);

    private final long roleId;

    SystemRole(Long roleId) {
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }

    public static SystemRole fromRoleId(long roleId) {
        for (SystemRole memberRole : SystemRole.values()) {
            if (roleId == memberRole.getRoleId()) {
                return memberRole;
            }
        }
        return null;
    }
}
