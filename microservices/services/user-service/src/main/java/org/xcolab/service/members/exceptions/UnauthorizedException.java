package org.xcolab.service.members.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="You are not authorized to acccess this resource")
public class UnauthorizedException extends Exception {
    public UnauthorizedException(String msg) {
        super(msg);
    }
}
