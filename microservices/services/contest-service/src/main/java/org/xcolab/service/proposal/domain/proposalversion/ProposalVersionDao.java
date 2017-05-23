package org.xcolab.service.proposal.domain.proposalversion;


import org.xcolab.model.tables.pojos.Proposal2Phase;
import org.xcolab.model.tables.pojos.ProposalVersion;

import java.util.List;

public interface ProposalVersionDao {

    List<ProposalVersion> findByGiven(Long proposalId, Integer version);

    ProposalVersion create(ProposalVersion proposalVersion);

    ProposalVersion getByProposalIdVersion(Long proposalId, Integer version);

    List<ProposalVersion> findByProposal2Phase(List<Proposal2Phase> proposal2Phases, Long proposalId);

    int countByGiven(Long proposalId);
}
