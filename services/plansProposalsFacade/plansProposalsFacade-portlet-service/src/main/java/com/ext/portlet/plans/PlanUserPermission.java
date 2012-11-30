package com.ext.portlet.plans;

public enum PlanUserPermission {
    OWNER("Owner"),
    ADMIN("Admin"),
    MEMBER("Member");
    
    private final String description;

    private PlanUserPermission(String description) {
        this.description = description;
    }
    
    public String getDescription() { return description; }
}
