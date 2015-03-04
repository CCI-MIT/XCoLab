package org.xcolab.enums;

/**
 * Created by kmang on 21/05/14.
 */
public enum ContestPhaseType {

    PROPOSAL_CREATION(1, "Proposal creation"),
    FINALIST_SELECTION(11, "Finalist selection"),
    PROPOSAL_REVISION(12, "Proposal revision"),
    SELECTION_OF_WINNERS(13, "Selection of winners"),
    SELECTION_OF_WINNERS_NEW(20, "Selection of winners"),/* seems to be equivalent to 13 and 20*/
    COMPLETED(14, "Complete"),
    WINNERS_SELECTION(15, "Winners selection"); /* seems to be equivalent to 13 */

    private long typeId;

    private String name;

    ContestPhaseType(long typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getTypeId() {
        return typeId;
    }

    public static ContestPhaseType getContestPhaseTypeByTypeId(long typeId) {
        for (ContestPhaseType phaseType : ContestPhaseType.values()) {
            if (phaseType.getTypeId() == typeId) {
                return phaseType;
            }
        }

        return null;
    }
}
