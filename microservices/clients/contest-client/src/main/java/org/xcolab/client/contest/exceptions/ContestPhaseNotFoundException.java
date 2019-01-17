package org.xcolab.client.contest.exceptions;

public class ContestPhaseNotFoundException extends RuntimeException {

    public ContestPhaseNotFoundException(String msg) {
        super(msg);
    }

    public ContestPhaseNotFoundException(long contestPhaseId) {
        super("ContestPhase with id " + contestPhaseId + " not found");
    }
}
