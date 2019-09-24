package org.xcolab.service.modeling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Object Not Found")
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
