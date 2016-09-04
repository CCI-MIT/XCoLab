package org.xcolab.service.members.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="You do not have the permissions to access this resource")
public class UnauthorizedException extends Exception {

    public UnauthorizedException() {

    }
    public UnauthorizedException(String message) {
        super(message);
    }
}
