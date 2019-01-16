package org.xcolab.service.members.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="You do not have the permissions to access this resource")
public class ForbiddenException extends Exception {

    public ForbiddenException(String message) {
        super(message);
    }
}
