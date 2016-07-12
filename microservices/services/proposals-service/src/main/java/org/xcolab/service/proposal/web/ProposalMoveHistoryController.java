package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.service.proposal.domain.proposalmovehistory.ProposalMoveHistoryDao;

@RestController
public class ProposalMoveHistoryController {

    @Autowired
    ProposalMoveHistoryDao proposalMoveHistoryDao;
}
