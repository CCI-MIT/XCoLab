package org.xcolab.view.pages.proposals.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid Contest or Proposal URL")
public class ProposalIdOrContestIdInvalidException extends IllegalArgumentException {
    public ProposalIdOrContestIdInvalidException(Exception exception) {
        super (exception);
    }

    public ProposalIdOrContestIdInvalidException() {

    }
}
