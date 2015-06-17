package org.xcolab.portlets.users.utils;

import org.apache.commons.lang.WordUtils;

public enum MemberCategory {
    DEFAULT("Default", "Guest"),
	ALL("All"),
    MEMBER("User"),
    CATALYST("Catalyst"),
    FELLOW("Fellow", "Impact Assessment Fellow"),
    IMPACT_ASSESSMENT_FELLOW("Impact Assessment Fellow"),
    ADVISOR("Advisor"),
    EXPERT("Experts"),
    CONTESTMANAGER("Contest Manager"),
    JUDGES("Judges"),
    STAFF("Staff", "Moderator"),
    MODERATOR("Staff");
    
    private final String[] roleNames;
    
    MemberCategory(String... roleNames) {
        this.roleNames = roleNames;
    } 

    public String getPrintName() {
        return WordUtils.capitalizeFully((name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase()).replaceAll("_", " "));
    }
    
    public String getLowerCase() {
        return name().toLowerCase();
    }

    public String[] getRoleNames() {
        return roleNames;
    }
}
