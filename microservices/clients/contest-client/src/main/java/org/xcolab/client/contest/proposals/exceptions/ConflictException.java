package org.xcolab.client.contest.proposals.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Entry already exists")
public class ConflictException extends EntityNotFoundException {

    public ConflictException() {
        super("", ConflictException.class);
    }
}
