package org.xcolab.service.proposal.domain.proposalsupporter;

import org.xcolab.model.tables.pojos.ProposalSupporter;

import java.util.List;

public interface ProposalSupporterDao {

    ProposalSupporter create(ProposalSupporter proposalSupporter);

    List<ProposalSupporter> findByGiven(Long proposalId, Long userId);

    Integer countByProposalId(Long proposalId);

    int delete(Long proposalId, Long userId);
}
