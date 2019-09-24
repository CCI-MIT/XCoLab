package org.xcolab.service.contest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Entry already exists")
public class ConflictException extends Exception {

}
