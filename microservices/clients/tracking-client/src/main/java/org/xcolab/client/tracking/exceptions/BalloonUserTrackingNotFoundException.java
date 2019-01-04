package org.xcolab.client.tracking.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class BalloonUserTrackingNotFoundException extends EntityNotFoundException {
    public BalloonUserTrackingNotFoundException(String msg) {
        super(msg, BalloonUserTrackingNotFoundException.class);
    }
}
