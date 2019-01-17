package org.xcolab.service.contest.proposal.enums;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.model.tables.pojos.PointType;
import org.xcolab.model.tables.pojos.PointsDistributionConfiguration;
import org.xcolab.model.tables.pojos.ProposalReference;
import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.model.tables.pojos.ProposalAttribute;
import org.xcolab.service.contest.proposal.service.pointsdistributionconfiguration.PointsDistributionConfigurationService;
import org.xcolab.service.contest.proposal.service.proposal.ProposalService;

import java.util.*;

public class PointsDistributionUtil {

    private static ProposalService proposalService;
    private static PointsDistributionConfigurationService pointsDistributionConfigurationService;

    public static List<PointsTarget> distributeEquallyAmongContributors(long proposalId) {
        List<PointsTarget> targets = new ArrayList<>();
        try{
            List<Member> members = proposalService.getProposalMembers(proposalId);
            for (Member u : members) {
                targets.add(PointsTarget.forUser(u.getId(), 1.0d / members.size()));
            }
        } catch(ProposalNotFoundException ignored)  {}
        return targets;
    }

    public static List<PointsTarget> distributeEquallyAmongProposals(Collection<Long> proposalIds) {
        List<PointsTarget> targets = new ArrayList<>();
        for (Long proposalId : proposalIds) {
            targets.add(PointsTarget.forProposal(proposalId, 1.0d / proposalIds.size()));
        }
        return targets;
    }

    public static List<PointsTarget> distributeSectionDefinedAmongProposals(Proposal proposal, PointType pointType, Set<Long> subProposalIds)  {
        List<PointsTarget> targets = new ArrayList<>();
        for (long subProposalId : subProposalIds) {
            ProposalReference reference = proposalService.getReferenceByProposalIdAndSubProposalId(proposal.getId(), subProposalId);
            //ProposalReference reference = ProposalReferenceLocalServiceUtil.getByProposalIdSubProposalId(proposal.getId(), subProposalId);
            final ProposalAttribute referenceSectionProposalAttribute = proposalService.getProposalAttribute(reference.getSectionAttributeId());
            //final ProposalAttribute referenceSectionProposalAttribute = ProposalAttributeLocalServiceUtil.getProposalAttribute(reference.getSectionAttributeId());
            final long proposalTemplateSectionDefinitionId = referenceSectionProposalAttribute.getAdditionalId();
            PointsDistributionConfiguration pdc = pointsDistributionConfigurationService.getPointsDistributionConfiguration(proposalTemplateSectionDefinitionId);
                //PointsDistributionConfiguration pdc = PointsDistributionConfigurationLocalServiceUtil.getByProposalTemplateSectionDefinitionId(proposalTemplateSectionDefinitionId);
            targets.add(PointsTarget.forProposal(subProposalId, pdc.getPercentage()));

        }
        return targets;
    }

    public static List<PointsTarget> distributeUserDefinedAmongProposals(Proposal proposal, PointType pointType, Set<Long> subProposalIds) {
        List<PointsTarget> targets = new ArrayList<>();
        //for (PointsDistributionConfiguration pdc : PointsDistributionConfigurationLocalServiceUtil.findByProposalIdPointTypeId(proposal.getId(), pointType.getId())) {
        for (PointsDistributionConfiguration pdc : pointsDistributionConfigurationService.getPointsDistributionConfiguration(proposal.getId(), pointType.getId())) {
            if (pdc.getTargetSubProposalId() > 0 && subProposalIds.contains(pdc.getTargetSubProposalId()) && pdc.getTargetSubProposalId() != proposal.getId()) {
                PointsTarget target = new PointsTarget();
                target.setProposalId(pdc.getTargetSubProposalId());
                target.setPercentage(pdc.getPercentage());
                targets.add(target);
            }
        }
        return targets;
    }

    public static List<PointsTarget> distributeAmongProposals(DistributionStrategy distributionStrategy, Proposal parentProposals, PointType pointType, Set<Long> proposalIds) {
        switch (distributionStrategy) {
            case USER_DEFINED:
                return distributeUserDefinedAmongProposals(parentProposals, pointType, proposalIds);
            case EQUAL_DIVISION:
                return distributeEquallyAmongProposals(proposalIds);
            case SECTION_DEFINED:
                return distributeSectionDefinedAmongProposals(parentProposals, pointType, proposalIds);
        }
        return Collections.emptyList();
    }
}
