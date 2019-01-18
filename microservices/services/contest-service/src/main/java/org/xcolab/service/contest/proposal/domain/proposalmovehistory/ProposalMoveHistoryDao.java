package org.xcolab.service.contest.proposal.domain.proposalmovehistory;

import org.xcolab.client.contest.pojo.IProposalMoveHistory;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalMoveHistoryDao {

    List<IProposalMoveHistory> findByGiven(Long sourceProposalId, Long sourceContestId, Long targetProposalId, Long targetContestId);

    IProposalMoveHistory get(Long id) throws NotFoundException;

    IProposalMoveHistory create(IProposalMoveHistory proposalMoveHistory);
}
