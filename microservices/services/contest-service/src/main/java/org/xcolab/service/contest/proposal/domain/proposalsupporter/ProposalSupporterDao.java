package org.xcolab.service.contest.proposal.domain.proposalsupporter;

import org.xcolab.client.contest.pojo.IProposalSupporter;

import java.util.List;

public interface ProposalSupporterDao {

    IProposalSupporter create(IProposalSupporter proposalSupporter);

    List<IProposalSupporter> findByGiven(Long proposalId, Long userId);

    Integer countByProposalId(Long proposalId);

    int delete(Long proposalId, Long userId);
}
