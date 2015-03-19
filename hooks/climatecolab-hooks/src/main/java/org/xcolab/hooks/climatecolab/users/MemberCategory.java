package org.xcolab.hooks.climatecolab.users;

public enum MemberCategory {
    DEFAULT("Default"),
	ALL("All"),
    MEMBER("User"),
    CATALYST("Catalyst"),
    FELLOW("Fellow"),
    ADVISOR("Advisor"),
    EXPERT("Experts"),
    JUDGES("Judges"),
    STAFF("Staff", "Moderator"),
    MODERATOR("Staff"),
    CONTESTMANAGER("Contest Manager");


    private final String[] roleNames;

   
    
    MemberCategory(String...roleNames) {
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
