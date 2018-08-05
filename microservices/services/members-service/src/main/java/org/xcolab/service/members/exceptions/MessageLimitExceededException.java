package org.xcolab.service.members.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.TOO_MANY_REQUESTS, reason="Message limit exceeded")
public class MessageLimitExceededException extends Exception  {
    public MessageLimitExceededException(long userId) {
        super("Member " + userId + " exceeded their daily message limit");
    }
}
