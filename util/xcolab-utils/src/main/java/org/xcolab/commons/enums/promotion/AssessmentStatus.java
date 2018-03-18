package org.xcolab.commons.enums.promotion;

public enum AssessmentStatus {
    UNKNOWN(0),
    NEGATIVE(1),
    POSITIVE(2);

    private final int statusValue;

    AssessmentStatus(int statusValue) {
        this.statusValue = statusValue;
    }

    public int getStatusValue() {
        return statusValue;
    }
}
