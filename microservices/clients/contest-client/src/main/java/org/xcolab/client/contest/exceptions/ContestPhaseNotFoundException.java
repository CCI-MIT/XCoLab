package org.xcolab.client.contest.exceptions;

import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

public class ContestPhaseNotFoundException extends RuntimeEntityNotFoundException {

    public ContestPhaseNotFoundException(long contestPhaseId) {
        super("ContestPhase with id " + contestPhaseId + " not found",
                ContestPhaseNotFoundException.class);
    }
}
