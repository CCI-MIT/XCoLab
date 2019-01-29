package org.xcolab.service.contest.proposal.domain.proposalcontestphaseattribute;

import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalContestPhaseAttributeDao {

    int delete(Long id);

    List<IProposalContestPhaseAttribute> findByGiven(Long proposalId, Long contestPhaseId, String name);

    IProposalContestPhaseAttribute get(Long id) throws NotFoundException;

    IProposalContestPhaseAttribute create(IProposalContestPhaseAttribute proposalContestPhaseAttribute);

    IProposalContestPhaseAttribute getByProposalIdContestPhaseIdName(Long proposalId, Long contestPhaseId, String name) throws NotFoundException;

    boolean update(IProposalContestPhaseAttribute proposalContestPhaseAttribute);
}
