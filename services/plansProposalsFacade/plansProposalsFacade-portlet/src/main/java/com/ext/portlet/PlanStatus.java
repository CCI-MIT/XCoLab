package com.ext.portlet;

public enum PlanStatus {
    UNDER_DEVELOPMENT("Under development", true),
    SUBMITTED("Submitted", true);
    
    private final boolean editable;
    private final String description;
    
    PlanStatus(String description, boolean editable) {
        this.description = description;
        this.editable = editable;
    }
    
    public boolean isEditable() { return editable; }
    public String getDescription() { return description; } 

}
