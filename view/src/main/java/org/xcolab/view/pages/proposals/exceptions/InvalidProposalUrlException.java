package org.xcolab.view.pages.proposals.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid Proposal URL")
public class InvalidProposalUrlException extends InvalidAccessException {

    public InvalidProposalUrlException(Contest contest, ContestPhase contestPhase, long proposalId) {
        super(msg(contest, contestPhase, proposalId));
    }

    private static String msg(Contest contest, ContestPhase contestPhase, Long proposalId) {
        String msg = "ProposalId " + proposalId + " not found in contest "
                + contest.getContestUrlName();
        if (contestPhase != null) {
            msg += String.format(" in contest phase %d (%s)", contestPhase.getId(),
                    contestPhase.getContestPhaseTypeObject().getName());
        }
        return msg;
    }
}
