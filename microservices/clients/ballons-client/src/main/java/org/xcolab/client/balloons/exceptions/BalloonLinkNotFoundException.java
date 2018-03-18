package org.xcolab.client.balloons.exceptions;

import org.xcolab.commons.http.exceptions.EntityNotFoundException;

public class BalloonLinkNotFoundException extends EntityNotFoundException {
    public BalloonLinkNotFoundException(String msg) {
        super(msg);
    }
}
