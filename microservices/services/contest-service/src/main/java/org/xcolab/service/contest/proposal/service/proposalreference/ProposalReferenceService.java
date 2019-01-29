package org.xcolab.service.contest.proposal.service.proposalreference;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.IProposalReference;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalReference;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.contest.proposal.domain.proposalreference.ProposalReferenceDao;
import org.xcolab.service.contest.proposal.service.proposalattribute.ProposalAttributeHelper;
import org.xcolab.service.contest.proposal.service.proposalattribute.ProposalAttributeKeys;
import org.xcolab.service.contest.proposal.service.proposalattribute.ProposalAttributeService;
import org.xcolab.util.enums.proposal.ProposalTemplateSectionType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProposalReferenceService {

    private final ProposalReferenceDao proposalReferenceDao;
    private final ProposalDao proposalDao;

    private final ProposalAttributeService proposalAttributeService;

    @Autowired
    public ProposalReferenceService(ProposalReferenceDao proposalReferenceDao,
            ProposalDao proposalDao, ProposalAttributeService proposalAttributeService){
        this.proposalReferenceDao = proposalReferenceDao;
        this.proposalDao = proposalDao;
        this.proposalAttributeService = proposalAttributeService;
    }

    public void populateTableWithProposal(ProposalWrapper proposal)  {
        final List<IProposalReference> existingReferences = proposalReferenceDao.findByGiven(proposal.getId(),null);
        for (IProposalReference existingReference : existingReferences) {
            proposalReferenceDao.delete(existingReference.getProposalId(), existingReference.getSubProposalId());
        }
        populateTableWithProposal(proposal, new HashSet<>());
    }

    private void populateTableWithProposal(ProposalWrapper proposal, Set<Long> processedProposals)  {
        if (processedProposals.contains(proposal.getId())) {
            return;
        }
        final List<IProposalReference> existingReferences = proposalReferenceDao.findByGiven(proposal.getId(),null);
        for (IProposalReference existingReference : existingReferences) {
            proposalReferenceDao.delete(existingReference.getProposalId(), existingReference.getSubProposalId());
        }
        processedProposals.add(proposal.getId());
        final ProposalAttributeHelper proposalAttributeHelper =
                proposalAttributeService.getProposalAttributeHelper(proposal.getId(),
                        ProposalAttributeService.LATEST_VERSION);
        final Collection<ProposalAttribute> sectionAttributes =
                proposalAttributeHelper.getAttributes(ProposalAttributeKeys.SECTION);
        for (ProposalAttribute attribute : sectionAttributes) {
            ProposalTemplateSectionDefinitionWrapper psd = StaticContestContext
                    .getProposalTemplateClient()
                    .getProposalTemplateSectionDefinition(attribute.getAdditionalId());

            if (StringUtils.isBlank(psd.getType())) {
                continue;
            }

            ProposalTemplateSectionType type = ProposalTemplateSectionType.valueOf(psd.getType());
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
                    if ((attribute.getStringValue() != null)) {
                        final String[] stringIds = attribute.getStringValue().split(",");
                        subProposalIds = Stream.of(stringIds)
                                .filter(NumberUtils::isParsable)
                                .map(Long::parseLong)
                                .collect(Collectors.toSet());
                    }
                    break;
                }
                case PROPOSAL_LIST_TEXT_REFERENCE: {
                    subProposalIds
                            .addAll(getProposalIdsFromLinksInText(attribute.getStringValue()));
                    break;
                }
            }

            for (long subProposalId : subProposalIds) {
                addProposalReference(proposal.getId(), subProposalId, attribute.getId());
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
        IProposalReference proposalReference = new ProposalReference();
        proposalReference.setProposalId(proposalId);
        proposalReference.setSubProposalId(subProposalId);

        try {
            proposalReferenceDao.get(proposalId, subProposalId);

        } catch (NotFoundException notFound) {
            proposalReference.setSectionAttributeId(sectionAttributeId);
            proposalReferenceDao.create(proposalReference);
        }
    }
}
