package org.xcolab.service.proposal.domain.proposalmovehistory;

import org.xcolab.model.tables.pojos.ProposalMoveHistory;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalMoveHistoryDao {

    List<ProposalMoveHistory> findByGiven(Long sourceProposalId, Long sourceContestId, Long targetProposalId, Long targetContestId);

    ProposalMoveHistory get(Long id_) throws NotFoundException;

    ProposalMoveHistory create(ProposalMoveHistory proposalMoveHistory);
}
