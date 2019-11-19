package org.xcolab.view.pages.contestmanagement.utils;

public enum ProposalExportType {
    ALL_ESCAPED("All proposals sections without html tags"),
    ALL_COMMENTS("All proposals comments without html tags"),
    ALL_UNESCAPED("All proposals not escaped"),
    ALL_SUPPORTERS("All proposal supporters"),
    CONTRIBUTORS("Proposal contributors"),
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
