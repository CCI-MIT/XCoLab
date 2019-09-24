package org.xcolab.client.contest.exceptions;

import org.xcolab.util.http.exceptions.RuntimeEntityNotFoundException;

public class ContestNotFoundException extends RuntimeEntityNotFoundException {

    public ContestNotFoundException(String msg) {
        super(msg, ContestNotFoundException.class);
    }

    public ContestNotFoundException(long contestId) {
        this("Contest with id " + contestId + " not found");
    }

    public ContestNotFoundException(String contestUrlName, long contestYear) {
        this("Contest " + contestUrlName + " not found in year " + contestYear);
    }
}
