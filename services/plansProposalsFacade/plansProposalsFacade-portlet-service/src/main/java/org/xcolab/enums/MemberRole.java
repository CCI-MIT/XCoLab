package org.xcolab.enums;

public enum MemberRole {
    /**
     * Important:
     * Whenever these roles are modified (which should never happen) these Ids should be updated as well
     */
	ALL(0L, "All"),
    MEMBER(10122L, "User"),
    FELLOW(193261L, "Fellow"),
    ADVISOR(193260L, "Advisor"),
    EXPERT(44201L, "Experts"),
    JUDGES(1251483L, "Judges", "Judge"),
    STAFF(31704L, "Staff", "Moderator", "Administrator"),
    MODERATOR(31213L, "Staff"),
    CATALYST(1430078L, "Catalyst"),
    CONTESTMANAGER(1950101L, "Contest Manager");

    
    private final String[] roleNames;
    private Long roleId;

   
    
    MemberRole(Long roleId, String... roleNames) {
        this.roleId = roleId;
        this.roleNames = roleNames;
    } 

    public String getPrintName() {
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }
    
    public String getLowerCase() {
        return name().toLowerCase();
    }

    public String[] getRoleNames() {
        return roleNames;
    }

    public Long getRoleId() {
        return roleId;
    }

    static public MemberRole getMember(String aName) {
        MemberRole[] roles = MemberRole.values();
        for (MemberRole r : roles)
            if (isMemberInList(aName, r.getRoleNames()))
                return r;
        return null;
    }

    public static MemberRole getMemberByRoleId(Long roleId) {
        for (MemberRole memberRole : MemberRole.values()) {
            if (memberRole.getRoleId() == roleId) {
                return memberRole;
            }
        }

        return null;
    }

    private static boolean isMemberInList(String name, String[] names) {
        for (String n : names) {
            if (name.equalsIgnoreCase(n)) {
                return true;
            }
        }

        return false;
    }

}
