package org.xcolab.util.enums.promotion;

/**
 * Created by johannes on 9/23/15.
 */
public enum GenericJudgingStatus {
    STATUS_UNKNOWN(0),
    STATUS_REJECTED(1),
    STATUS_ACCEPTED(2);

    private int statusValue;

    GenericJudgingStatus(int statusValue) {
        this.statusValue = statusValue;
    }

    public int getStatusValue() {
        return statusValue;
    }
}
