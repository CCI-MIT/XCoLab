package org.xcolab.service.proposal.domain.proposalversion;

import org.xcolab.model.tables.pojos.ProposalVersion;

import java.util.List;

public interface ProposalVersionDao {

    List<ProposalVersion> findByGiven(Long proposalId, Integer version);

    ProposalVersion create(ProposalVersion proposalVersion);
}
