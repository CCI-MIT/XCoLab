package org.xcolab.client.balloons.exceptions;

import org.xcolab.commons.http.exceptions.EntityNotFoundException;

public class BalloonTextNotFoundException extends EntityNotFoundException {

    public BalloonTextNotFoundException(long id) {
        this("BalloonText " + id + " does not exist");
    }

    public BalloonTextNotFoundException(String msg) {
        super(msg);
    }
}
