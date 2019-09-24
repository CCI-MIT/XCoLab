package org.xcolab.client.tracking.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class BalloonTextNotFoundException extends EntityNotFoundException {

    public BalloonTextNotFoundException(long id) {
        this("BalloonText " + id + " does not exist");
    }

    public BalloonTextNotFoundException(String msg) {
        super(msg, BalloonTextNotFoundException.class);
    }
}
