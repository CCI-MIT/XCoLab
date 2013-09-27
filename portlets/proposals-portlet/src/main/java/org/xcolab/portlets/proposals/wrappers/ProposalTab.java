package org.xcolab.portlets.proposals.wrappers;

public enum ProposalTab {
    DESCRIPTION("Description"),
    ACTIONSIMPACTS("Model results"),
    TEAM("Contributors"),
    COMMENTS("Comments"),
    JUDGES_COMMENTS("Judges comments"),
    ADVISORS_COMMENTS("Advisors comments"),
    ADMIN("Admin"),
    OTHER("");
    private final String displayName;
    
    private ProposalTab(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}