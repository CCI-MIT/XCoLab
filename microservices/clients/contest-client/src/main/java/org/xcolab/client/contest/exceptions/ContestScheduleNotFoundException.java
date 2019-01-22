package org.xcolab.client.contest.exceptions;

import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

public class ContestScheduleNotFoundException extends RuntimeEntityNotFoundException {

    public ContestScheduleNotFoundException(Long contestScheduleId) {
        super("Contest schedule " + contestScheduleId + " does not exist",
                ContestScheduleNotFoundException.class);
    }
}
