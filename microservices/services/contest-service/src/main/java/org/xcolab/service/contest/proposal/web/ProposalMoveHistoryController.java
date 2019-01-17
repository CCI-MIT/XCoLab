package org.xcolab.service.contest.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.xcolab.model.tables.pojos.ProposalMoveHistory;
import org.xcolab.service.contest.proposal.domain.proposalmovehistory.ProposalMoveHistoryDao;

import java.util.List;

@RestController
public class ProposalMoveHistoryController {

    @Autowired
    ProposalMoveHistoryDao proposalMoveHistoryDao;

    @RequestMapping(value = "/proposalMoveHistories", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ProposalMoveHistory> getProposalMoveHistories(
            @RequestParam(required = false) Long sourceProposalId,
            @RequestParam(required = false) Long sourceContestId,
            @RequestParam(required = false) Long targetProposalId,
            @RequestParam(required = false) Long targetContestId
    ) {
        return proposalMoveHistoryDao.findByGiven(sourceProposalId, sourceContestId, targetProposalId, targetContestId);
    }

    @RequestMapping(value = "/proposalMoveHistories", method = RequestMethod.POST)
    public ProposalMoveHistory createProposalMoveHistory(@RequestBody ProposalMoveHistory proposalMoveHistory) {
        return this.proposalMoveHistoryDao.create(proposalMoveHistory);
    }

}
