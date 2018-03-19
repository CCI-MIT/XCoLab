package org.xcolab.service.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Request Parameter")
public class IllegalRequestParameterException extends IllegalArgumentException {
    public IllegalRequestParameterException(String message) {
        super(message);
    }

    public IllegalRequestParameterException(Throwable cause) {
        super(cause);
    }

    public IllegalRequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
