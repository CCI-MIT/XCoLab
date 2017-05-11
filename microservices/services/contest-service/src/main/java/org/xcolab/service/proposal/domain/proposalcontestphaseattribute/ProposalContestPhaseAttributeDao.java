package org.xcolab.service.proposal.domain.proposalcontestphaseattribute;

import org.xcolab.model.tables.pojos.ProposalContestPhaseAttribute;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalContestPhaseAttributeDao {

    int delete(Long id_);

    List<ProposalContestPhaseAttribute> findByGiven(Long proposalId, Long contestPhaseId, String name);

    ProposalContestPhaseAttribute get(Long id_) throws NotFoundException;

    ProposalContestPhaseAttribute create(ProposalContestPhaseAttribute proposalContestPhaseAttribute);

    ProposalContestPhaseAttribute getByProposalIdContestPhaseIdName(Long proposalId, Long contestPhaseId, String name) throws NotFoundException;

    boolean update(ProposalContestPhaseAttribute proposalContestPhaseAttribute);
}
