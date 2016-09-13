package org.xcolab.portlets.contestmanagement.utils.schedule;

public class SchedulePhasesMismatchException extends IllegalArgumentException {
    public SchedulePhasesMismatchException(long contestId, long newScheduleId, long unmatchedPhaseTypeId) {
        super(String.format("Could not match phaseType %d for schedule %d in contest %d",
                unmatchedPhaseTypeId, newScheduleId, contestId));
    }
}
