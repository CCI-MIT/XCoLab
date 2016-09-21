package org.xcolab.client.contest.exceptions;

public class ContestNotFoundException extends Exception {
    public ContestNotFoundException(long contestId) {
        super("Contest with id " + contestId + " not found");
    }

    public ContestNotFoundException(String contestUrlName, long contestYear) {
        super("Contest " + contestUrlName + " not found in year " + contestYear);
    }
}
