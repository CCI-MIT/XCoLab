package org.xcolab.client.contest.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class PointDistributionNotFoundException extends EntityNotFoundException {

    public PointDistributionNotFoundException() {
        super("", PointDistributionNotFoundException.class);
    }

    public PointDistributionNotFoundException(String message) {
        super(message, PointDistributionNotFoundException.class);
    }
}
