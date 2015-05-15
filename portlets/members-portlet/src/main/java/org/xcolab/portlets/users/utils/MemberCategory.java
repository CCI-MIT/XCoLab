package org.xcolab.portlets.users.utils;

public enum MemberCategory {
    DEFAULT("Default", "Guest"),
	ALL("All"),
    MEMBER("User"),
    CATALYST("Catalyst"),
    FELLOW("Fellow", "Impact Assessment Fellow"),
    ADVISOR("Advisor"),
    EXPERT("Experts"),
    JUDGES("Judges"),
    STAFF("Staff", "Moderator"),
    MODERATOR("Staff");
    
    private final String[] roleNames;
    
    MemberCategory(String... roleNames) {
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
}
