package org.xcolab.service.contest.utils.promotion.enums;

public enum ContestPhasePromoteType {
    DEFAULT(-1, "DEFAULT", "Default autopromote used"),
    NULL(0, "", "No autopromote specified"),
    PROMOTE(1, "PROMOTE", "Proposals in this phase will automatically promoted to the next contest phase"),
    PROMOTE_JUDGED(2, "PROMOTE_JUDGED", "Proposals in this phase will only be promoted to the next phase if judges decide so"),
    PROMOTE_DONE(3, "PROMOTE_DONE", "All proposals in that phase have been promoted to the next phase"),
    PROMOTE_RIBBONIZE(4, "PROMOTE_RIBBONIZE", "All proposals from previous phases will be copied to the next phase" +
            " and (semi) finalist ribbons will be distributed.");

    private final int index;
    private final String value;
    private final String description;

    ContestPhasePromoteType(int index, String value, String description) {
        this.index = index;
        this.value = value;
        this.description = description;
    }

    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public static ContestPhasePromoteType getPromoteType(String theType) {
        for (ContestPhasePromoteType aType : ContestPhasePromoteType.values()) {
            if (aType.value.equals(theType)) {
                return aType;
            }
        }
        return null;
    }

    public static ContestPhasePromoteType getPromoteType(int index) {
        for (ContestPhasePromoteType aType : ContestPhasePromoteType.values()) {
            if (aType.index == index) {
                return aType;
            }
        }
        return null;
    }
}
