package org.xcolab.view.pages.proposals.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid Contest URL")
public class InvalidContestUrlException extends InvalidAccessException {

    public InvalidContestUrlException(String contestUrlName, Long contestId) {
        super(String.format("Invalid contest: id = %d, urlName = %s", contestId, contestUrlName));
    }
}
