package org.xcolab.client.balloons.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class BalloonUserTrackingNotFoundException extends EntityNotFoundException {
    public BalloonUserTrackingNotFoundException(String msg) {
        super(msg);
    }
}
