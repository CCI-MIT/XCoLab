package org.xcolab.client.contest.exceptions;

public class ContestScheduleNotFoundException extends RuntimeException {
    public ContestScheduleNotFoundException(long contestScheduleId) {
        super("Contest schedule " + contestScheduleId + " does not exist");
    }
}
