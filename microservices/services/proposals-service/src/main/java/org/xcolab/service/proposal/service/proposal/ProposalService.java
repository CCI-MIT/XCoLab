package org.xcolab.service.proposal.service.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.model.tables.pojos.PlanSectionDefinition;
import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.model.tables.pojos.ProposalAttribute;
import org.xcolab.model.tables.pojos.ProposalReference;
import org.xcolab.service.proposal.domain.plansectiondefinition.PlanSectionDefinitionDao;
import org.xcolab.service.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.proposal.domain.proposalattribute.ProposalAttributeDao;
import org.xcolab.service.proposal.domain.proposalreference.ProposalReferenceDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProposalService {

    private final ProposalDao proposalDao;

    private final ProposalReferenceDao proposalReferenceDao;

    private final ProposalAttributeDao proposalAttributeDao;

    private final PlanSectionDefinitionDao planSectionDefinitionDao;

    @Autowired
    public ProposalService(ProposalDao proposalDao, ProposalReferenceDao proposalReferenceDao, ProposalAttributeDao proposalAttributeDao, PlanSectionDefinitionDao planSectionDefinitionDao) {
        this.proposalDao = proposalDao;
        this.proposalReferenceDao = proposalReferenceDao;
        this.proposalAttributeDao = proposalAttributeDao;
        this.planSectionDefinitionDao = planSectionDefinitionDao;
    }


    public List<Proposal> getContestIntegrationRelevantSubproposals(long proposalId) {
        final boolean onlyWithContestIntegrationRelevance = true;
        final boolean includeProposalsInSameContest = false;
        return getSubproposals(proposalId, includeProposalsInSameContest, onlyWithContestIntegrationRelevance);
    }

    public List<Proposal> getSubproposals(long proposalId, boolean includeProposalsInSameContest) {
        final boolean onlyWithContestIntegrationRelevance = false;
        return getSubproposals(proposalId, includeProposalsInSameContest, onlyWithContestIntegrationRelevance);
    }

    public List<Proposal> getSubproposals(long proposalId, boolean includeProposalsInSameContest, boolean onlyWithContestIntegrationRelevance) {
        List<ProposalReference> proposalReferences = proposalReferenceDao.findByGiven(proposalId);

        List<Proposal> proposals = new ArrayList<>();
        for (ProposalReference proposalReference : proposalReferences) {
            try {
                if (onlyWithContestIntegrationRelevance) {
                    ProposalAttribute attribute = proposalAttributeDao.get(proposalReference.getSectionAttributeId());
                    PlanSectionDefinition psd = planSectionDefinitionDao.get(attribute.getAdditionalId());
                    if (!psd.getContestIntegrationRelevance()) {
                        continue;
                    }
                }
                final long subProposalId = proposalReference.getSubProposalId();
                Proposal p = proposalDao.get(subProposalId);
                if (p != null) {
                    if (!includeProposalsInSameContest) {
                        if (getLatestProposalContest(proposalId).equals(getLatestProposalContest(subProposalId))) {
                            continue;
                        }
                    }
                    if (p.getProposalId() != proposalId) {
                        proposals.add(p);
                    }
                }
            } catch (NotFoundException | ContestNotFoundException ignored) {

            }
        }
        return proposals;
    }

    private Long getLatestProposalContest(Long proposalId) throws ContestNotFoundException {
        Long contestPhaseId = ProposalsClient.getLatestContestPhaseIdInProposal(proposalId);
        ContestPhase contestPhase = ContestClient.getContestPhase(contestPhaseId);
        return contestPhase.getContestPhasePK();
    }
}
