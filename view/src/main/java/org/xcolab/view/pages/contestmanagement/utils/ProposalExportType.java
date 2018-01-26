package org.xcolab.view.pages.contestmanagement.utils;

public enum ProposalExportType {
    ALL("All proposals"),
    WINNING_CONTRIBUTORS("Winning proposal contributors")
    ;

    private final String description;

    ProposalExportType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
