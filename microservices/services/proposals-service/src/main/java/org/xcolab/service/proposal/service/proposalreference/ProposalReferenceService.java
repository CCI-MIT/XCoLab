package org.xcolab.service.proposal.service.proposalreference;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import org.xcolab.client.contest.PlanTemplateClient;
import org.xcolab.client.contest.pojo.PlanSectionDefinition;
import org.xcolab.model.tables.pojos.Proposal;

import org.xcolab.model.tables.pojos.ProposalAttribute;
import org.xcolab.model.tables.pojos.ProposalReference;
import org.xcolab.service.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.proposal.domain.proposalreference.ProposalReferenceDao;
import org.xcolab.service.proposal.enums.PlanSectionTypeKeys;
import org.xcolab.service.proposal.exceptions.NotFoundException;
import org.xcolab.service.proposal.helper.ProposalAttributeHelper;
import org.xcolab.service.proposal.service.proposalattribute.ProposalAttributeKeys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProposalReferenceService {

    private final ProposalReferenceDao proposalReferenceDao;

    private final ProposalDao proposalDao;



    public ProposalReferenceService(ProposalReferenceDao proposalReferenceDao,ProposalDao proposalDao){
        this.proposalReferenceDao = proposalReferenceDao;
        this.proposalDao = proposalDao;
    }

    public void populateTableWithProposal(Proposal proposal)  {
        final List<ProposalReference> existingReferences = proposalReferenceDao.findByGiven(proposal.getProposalId(),null);
        for (ProposalReference existingReference : existingReferences) {
            proposalReferenceDao.delete(existingReference.getProposalId(), existingReference.getSubProposalId());
        }
        populateTableWithProposal(proposal, new HashSet<Long>());
    }
    private void populateTableWithProposal(Proposal proposal, Set<Long> processedProposals)  {
        if (processedProposals.contains(proposal.getProposalId())) {
            return;
        }
        final List<ProposalReference> existingReferences = proposalReferenceDao.findByGiven(proposal.getProposalId(),null);
        for (ProposalReference existingReference : existingReferences) {
            proposalReferenceDao.delete(existingReference.getProposalId(), existingReference.getSubProposalId());
        }
        processedProposals.add(proposal.getProposalId());
        for (ProposalAttribute attribute : new ProposalAttributeHelper(proposal).getAttributesByName(ProposalAttributeKeys.SECTION)) {

                PlanSectionDefinition psd = PlanTemplateClient.getPlanSectionDefinition(attribute.getAdditionalId());

                if (StringUtils.isBlank(psd.getType_())) {
                    continue;
                }

                PlanSectionTypeKeys type = PlanSectionTypeKeys.valueOf(psd.getType_());
                Set<Long> subProposalIds = new HashSet<>();
                switch (type) {
                    case PROPOSAL_REFERENCE: {
                        final long subProposalId = attribute.getNumericValue();
                        if (subProposalId != 0) {
                            subProposalIds.add(subProposalId);
                        }
                        break;
                    }
                    case PROPOSAL_LIST_REFERENCE: {
                        if ((attribute.getStringValue() == null)) {
                            break;
                        }
                        String[] referencedProposals = attribute.getStringValue().split(",");
                        for (String referencedProposal : referencedProposals) {
                            final long subProposalId = Long.parseLong(referencedProposal);
                            subProposalIds.add(subProposalId);
                        }
                        break;
                    }
                    case PROPOSAL_LIST_TEXT_REFERENCE: {
                        subProposalIds.addAll(getProposalIdsFromLinksInText(attribute.getStringValue()));
                        break;
                    }
                }

                for (long subProposalId : subProposalIds) {
                    addProposalReference(proposal.getProposalId(), subProposalId, attribute.getId_());
                    try {
                        populateTableWithProposal(proposalDao.get(subProposalId), processedProposals);
                    } catch (NotFoundException ignored) {

                    }
                }

        }
    }
    public static List<Long> getProposalIdsFromLinksInText(String text) {
        List<Long> proposalIds = new ArrayList<>();
        Pattern proposalLinkPattern = Pattern.compile(
                "(href=|https?://).*?/.+?/\\d{4}/[a-z0-9-]+?/(?:c|phase/\\d*)/.+?/(\\d*)");
        Matcher m = proposalLinkPattern.matcher(text);
        while (m.find()) {
            try {
                final long proposalId = Long.parseLong(m.group(2));
                proposalIds.add(proposalId);
            } catch (NumberFormatException ignored) {
            }
        }

        proposalIds.addAll(getProposalIdsFromLegacyLinksInText(text));
        return proposalIds;
    }
    private static List<Long> getProposalIdsFromLegacyLinksInText(String text) {
        List<Long> proposalIds = new ArrayList<>();
        Pattern proposalLinkPattern = Pattern.compile(
                "(href=|https?://).*?/-/plans/contestId/(\\d*)/(?:phaseId/\\d*/)?planId/(\\d*)");
        Matcher m = proposalLinkPattern.matcher(text);
        while (m.find()) {
            try {
                final long proposalId = Long.parseLong(m.group(3));
                proposalIds.add(proposalId);
            } catch (NumberFormatException ignored) {
            }
        }
        return proposalIds;
    }
    private void addProposalReference(long proposalId, long subProposalId, long sectionAttributeId)  {
        ProposalReference proposalReference = new ProposalReference();
        proposalReference.setProposalId(proposalId);
        proposalReference.setSubProposalId(subProposalId);

        try{
            proposalReferenceDao.get(proposalId, subProposalId);

        }catch (NotFoundException notFound){
            proposalReference.setSectionAttributeId(sectionAttributeId);
            proposalReferenceDao.create(proposalReference);
        }
    }
}
