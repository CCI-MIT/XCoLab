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
        String printName = WordUtils.capitalizeFully((name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase()).replaceAll("_", " "));
        if ((printName.length() > 12) && (printName.contains(" "))) {
            printName = abbreviateName(printName);
        }
        return printName;
    }

    public String abbreviateName(String printName){
        String printNameAbbr = "";
        String[] printNameArray = printName.split("\\s+");
        int arrayCount = printNameArray.length - 1;
        for (int i = 0; i < arrayCount; i++) {
            printNameAbbr = printNameAbbr + printNameArray[i].substring(0, 1);
        }
        printNameAbbr = printNameAbbr + " " + printNameArray[arrayCount];
        return printNameAbbr;
    }

    public String getImageUrl() {
        if (name() == MemberCategory.IMPACT_ASSESSMENT_FELLOW.name()){
            return MemberCategory.FELLOW.name().toLowerCase();
        }
        else if (name() == MemberCategory.CONTESTMANAGER.name()){
            return MemberCategory.EXPERT.name().toLowerCase();
        }
        else
            return name().toLowerCase();
    }
    
    public String getLowerCase() {
        return name().toLowerCase();
    }

    public String[] getRoleNames() {
        return roleNames;
    }
}
