package org.xcolab.view.util.entity.enums;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;

public enum ContestPhaseTypeValue {

    PROPOSAL_CREATION(1, "Proposal creation"),
    FINALIST_SELECTION(11, "Finalist selection"),
    PROPOSAL_REVISION(12, "Proposal revision"),
    SELECTION_OF_WINNERS(13, "Selection of winners"),
    FINALIST_SELECTION_NEW(19, "Finalist selection new"), /* is used in ProposalsPreferencesController.runRibbonDistribution (11 isn't!) */
    SELECTION_OF_WINNERS_NEW(20, "Selection of winners"),/* seems to be equivalent to 13 and 20*/
    COMPLETED(14, "Complete"),
    VOTING_PHASE_SOLVE(21,"Voting"),
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

    public ContestPhaseType getContestPhaseType() {
        return ContestClientUtil.getContestPhaseType(typeId);
    }

    public static ContestPhaseTypeValue fromTypeId(long typeId) {
        for (ContestPhaseTypeValue phaseType : ContestPhaseTypeValue.values()) {
            if (phaseType.getTypeId() == typeId) {
                return phaseType;
            }
        }
        throw new NoSuchContestPhaseTypeValueException("Unknown contestPhaseTypeId given: " + typeId);
    }

    public static class NoSuchContestPhaseTypeValueException extends IllegalStateException {
        public NoSuchContestPhaseTypeValueException(String message) {
            super(message);
        }
    }
}
