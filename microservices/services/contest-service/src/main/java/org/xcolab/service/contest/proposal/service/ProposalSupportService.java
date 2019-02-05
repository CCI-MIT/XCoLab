package org.xcolab.service.contest.proposal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.contest.pojo.IProposalSupporter;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.wrapper.SupportedProposal;
import org.xcolab.commons.GroupingHelper;
import org.xcolab.service.contest.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.contest.proposal.domain.proposalsupporter.ProposalSupporterDao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProposalSupportService {

    private final ProposalSupporterDao proposalSupporterDao;
    private final ProposalDao proposalDao;

    @Autowired
    public ProposalSupportService(ProposalSupporterDao proposalSupporterDao,
            ProposalDao proposalDao) {
        this.proposalSupporterDao = proposalSupporterDao;
        this.proposalDao = proposalDao;
    }

    public List<IProposalSupporter> getSupportedProposalsForUser(long userId, boolean onlyVisible,
            boolean excludePrivateContests) {
        final List<IProposalSupporter> proposalSupporters = proposalSupporterDao.findByGiven(null, userId);
        Map<Long, IProposalSupporter> supportsByProposalId = new GroupingHelper<>(proposalSupporters)
                .groupUnique(IProposalSupporter::getProposalId);

        final Boolean visible = onlyVisible ? true : null;
        final Boolean contestPrivate = excludePrivateContests ? false : null;
        return proposalDao.filterByGiven(supportsByProposalId.keySet(), visible, contestPrivate)
                .stream().map(proposal -> supportsByProposalId.get(proposal.getId()))
                .collect(Collectors.toList());
    }
}
