package org.xcolab.service.contest.proposal.domain.proposalversion;


import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.wrapper.ProposalVersionWrapper;

import java.util.List;

public interface ProposalVersionDao {

    List<ProposalVersionWrapper> findByGiven(Long proposalId, Integer version);

    int findMaxVersion(Long proposalId);

    ProposalVersionWrapper create(ProposalVersionWrapper proposalVersion);

    ProposalVersionWrapper getByProposalIdVersion(Long proposalId, Integer version);

    List<ProposalVersionWrapper> findByProposal2Phase(List<IProposal2Phase> proposal2Phases, Long proposalId);

    int countByGiven(Long proposalId);
}
