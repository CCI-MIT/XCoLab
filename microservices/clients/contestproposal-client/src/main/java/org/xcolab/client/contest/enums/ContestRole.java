package org.xcolab.client.contest.enums;

public enum ContestRole {
    FELLOW(193261L),
    ADVISOR(193260L),
    JUDGE(1251483L),
    IAF(1975251L);

    private final long roleId;

    ContestRole(long roleId) {
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }

    public static ContestRole fromRoleId(long roleId) {
        for (ContestRole value : values()) {
            if (value.getRoleId() == roleId) {
                return value;
            }
        }
        throw new IllegalArgumentException("No contest role with roleId = " + roleId);
    }
}
