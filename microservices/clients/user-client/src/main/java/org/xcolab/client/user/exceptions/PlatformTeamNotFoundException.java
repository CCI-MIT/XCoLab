package org.xcolab.client.user.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class PlatformTeamNotFoundException extends EntityNotFoundException {

    public PlatformTeamNotFoundException(String msg) {
        super(msg);
    }

    public PlatformTeamNotFoundException() {
        super("");
    }
}
