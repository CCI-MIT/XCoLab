package org.xcolab.portlets.proposals.wrappers;

public enum ProposalTab {
    DESCRIPTION("Description"),
    ACTIONSIMPACTS("Model results"),
    TEAM("Contributors"),
    COMMENTS("Comments"),
    JUDGE("Judge"),
    FELLOW("Fellow"),
    ADMIN("Admin"),
    OTHER("");
    private final String displayName;
    
    private ProposalTab(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
    public boolean isDefault() {
        return this.ordinal() == 0;
    }
}