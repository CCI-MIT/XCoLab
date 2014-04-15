package org.xcolab.enums;

public enum MemberRole {
	ALL("All"),
    MEMBER("User"),
    FELLOW("Fellow"),
    ADVISOR("Advisor"),
    EXPERT("Experts"),
    JUDGES("Judges"),
    STAFF("Staff", "Moderator", "Administrator"),
    MODERATOR("Staff"),
    CATALYST("Catalyst");
    
    
    private final String[] roleNames;

   
    
    MemberRole(String... roleNames) {
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

    static public MemberRole getMember(String aName) {
        MemberRole[] roles = MemberRole.values();
        for (MemberRole r : roles)
            if (isMemberInList(aName, r.getRoleNames()))
                return r;
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
