package org.xcolab.service.proposal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.model.tables.pojos.ProposalSupporter;
import org.xcolab.service.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.proposal.domain.proposalsupporter.ProposalSupporterDao;
import org.xcolab.util.GroupingUtil;

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

    public List<SupportedProposal> getSupportedProposalsForUser(long userId, boolean onlyVisible,
            boolean excludePrivateContests) {
        final List<ProposalSupporter> proposalSupporters = proposalSupporterDao.findByGiven(null, userId);
        final Map<Long, ProposalSupporter> supportsByProposalId =
                GroupingUtil.groupByUnique(proposalSupporters, ProposalSupporter::getProposalId);

        final Boolean visible = onlyVisible ? true : null;
        final Boolean contestPrivate = excludePrivateContests ? false : null;
        return proposalDao.filterByGiven(supportsByProposalId.keySet(), visible, contestPrivate)
                .stream().map(proposal -> new SupportedProposal(proposal,
                        supportsByProposalId.get(proposal.getProposalId())))
                .collect(Collectors.toList());
    }

    public static class SupportedProposal extends Proposal {

        private final ProposalSupporter proposalSupporter;

        public SupportedProposal(Proposal proposal, ProposalSupporter proposalSupporter) {
            super(proposal);
            this.proposalSupporter = proposalSupporter;
        }

        public Timestamp getSupportDate() {
            return proposalSupporter.getCreateDate();
        }

        public Long getSupporterUserId() {
            return proposalSupporter.getUserId();
        }

    }
}
