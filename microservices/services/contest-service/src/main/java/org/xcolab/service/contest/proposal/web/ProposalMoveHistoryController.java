package org.xcolab.service.contest.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.pojo.IProposalMoveHistory;
import org.xcolab.client.contest.proposals.IProposalMoveClient;
import org.xcolab.service.contest.proposal.domain.proposalmovehistory.ProposalMoveHistoryDao;

import java.util.List;

@RestController
public class ProposalMoveHistoryController implements IProposalMoveClient {

    private final ProposalMoveHistoryDao proposalMoveHistoryDao;

    @Autowired
    public ProposalMoveHistoryController(
            ProposalMoveHistoryDao proposalMoveHistoryDao) {
        this.proposalMoveHistoryDao = proposalMoveHistoryDao;
    }

    @Override
    @GetMapping("/proposalMoveHistories")
    public List<IProposalMoveHistory> getProposalMoveHistories(
            @RequestParam(required = false) Long sourceProposalId,
            @RequestParam(required = false) Long sourceContestId,
            @RequestParam(required = false) Long targetProposalId,
            @RequestParam(required = false) Long targetContestId) {
        return proposalMoveHistoryDao
                .findByGiven(sourceProposalId, sourceContestId, targetProposalId, targetContestId);
    }

    @Override
    @PostMapping("/proposalMoveHistories")
    public IProposalMoveHistory createProposalMoveHistory(
            @RequestBody IProposalMoveHistory proposalMoveHistory) {
        return this.proposalMoveHistoryDao.create(proposalMoveHistory);
    }
}
