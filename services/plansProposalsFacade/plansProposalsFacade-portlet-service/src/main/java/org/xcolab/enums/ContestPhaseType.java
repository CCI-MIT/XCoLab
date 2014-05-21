package org.xcolab.enums;

/**
 * Created by kmang on 21/05/14.
 */
public enum ContestPhaseType {

    PROPOSAL_CREATION(1, "Proposal creation phase"),
    FINALIST_SELECTION(11, "Finalist selection phase"),
    PROPOSAL_REVISION(12, "Proposal revision phase"),
    SELECTION_OF_WINNERS(13, "Selection of winners"),
    COMPLETED(14, "Contest completed");

    private int type;

    private String description;

    ContestPhaseType(int type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getType() {
        return type;
    }
}
