package org.xcolab.service.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Request Parameter")
public class InvalidRequestParameterException extends IllegalArgumentException {
    public InvalidRequestParameterException(String message) {
        super(message);
    }

    public InvalidRequestParameterException(Throwable cause) {
        super(cause);
    }

    public InvalidRequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
