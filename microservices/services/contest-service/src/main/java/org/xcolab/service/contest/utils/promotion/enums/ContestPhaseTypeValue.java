package org.xcolab.service.contest.utils.promotion.enums;


import org.xcolab.service.contest.exceptions.NotFoundException;

public enum ContestPhaseTypeValue {

    PROPOSAL_CREATION(1, "Proposal creation"),
    FINALIST_SELECTION(11, "Finalist selection"),
    PROPOSAL_REVISION(12, "Proposal revision"),
    SELECTION_OF_WINNERS(13, "Selection of winners"),
    FINALIST_SELECTION_NEW(19, "Finalist selection new"), /* is used in ProposalsPreferencesController.runRibbonDistribution (11 isn't!) */
    SELECTION_OF_WINNERS_NEW(20, "Selection of winners"),/* seems to be equivalent to 13 and 20*/
    COMPLETED(14, "Complete"),
    WINNERS_AWARDED(17, "Winners awarded"),
    WINNERS_SELECTION(15, "Winners selection"), /* seems to be equivalent to 13 */
    WINNERS_ANNOUNCED_SOON(301, "Winners announced soon"),
    EARLY_BIRD_DEADLINE(401, "Early bird deadline");

    private final long typeId;

    private final String name;

    ContestPhaseTypeValue(long typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getTypeId() {
        return typeId;
    }



    public static ContestPhaseTypeValue fromTypeId(long typeId) throws NotFoundException {
        for (ContestPhaseTypeValue phaseType : ContestPhaseTypeValue.values()) {
            if (phaseType.getTypeId() == typeId) {
                return phaseType;
            }
        }
        throw new NotFoundException("Unknown contestPhaseTypeId given: " + typeId);
    }

}
