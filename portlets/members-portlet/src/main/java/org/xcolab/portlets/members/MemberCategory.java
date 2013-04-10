package org.xcolab.portlets.members;

public enum MemberCategory {
	ALL("All"),
    MEMBER("User"),
    FELLOW("Fellow"),
    ADVISOR("Advisor"),
    EXPERT("Experts"),
    STAFF("Staff", "Moderator"),
    MODERATOR("Staff");
    
    
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
