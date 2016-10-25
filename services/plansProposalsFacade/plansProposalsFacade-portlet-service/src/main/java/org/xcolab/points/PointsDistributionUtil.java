package org.xcolab.points;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.PointsClientUtil;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalAttributeNotFoundException;
import org.xcolab.client.proposals.pojo.points.PointType;
import org.xcolab.client.proposals.pojo.points.PointsDistributionConfiguration;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.client.proposals.pojo.tiers.ProposalReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PointsDistributionUtil {
    public static List<PointsTarget> distributeEquallyAmongContributors(long proposalId)
             {
        List<PointsTarget> targets = new ArrayList<>();
        List<Member> members = ProposalClientUtil.getProposalMembers(proposalId);
        for (Member u : members) {
            targets.add(PointsTarget.forUser(u.getUserId(), 1.0d / members.size()));
        }
        return targets;
    }

    public static List<PointsTarget> distributeEquallyAmongProposals(Collection<Long> proposalIds)
             {
        List<PointsTarget> targets = new ArrayList<>();
        for (Long proposalId : proposalIds) {
            targets.add(PointsTarget.forProposal(proposalId, 1.0d / proposalIds.size()));
        }
        return targets;
    }

    public static List<PointsTarget> distributeSectionDefinedAmongProposals(Proposal proposal, PointType pointType, Set<Long> subProposalIds)  {
        List<PointsTarget> targets = new ArrayList<>();
        for (long subProposalId : subProposalIds) {
            try {
                ProposalReference reference = ProposalClientUtil.getProposalReferenceByProposalIdSubProposalId(proposal.getProposalId(), subProposalId);
                final ProposalAttribute referenceSectionProposalAttribute = ProposalAttributeClientUtil

                        .getProposalAttribute(reference.getSectionAttributeId());
                final long planSectionDefinitionId = referenceSectionProposalAttribute.getAdditionalId();

                PointsDistributionConfiguration pdc = PointsClientUtil.getPointsDistributionConfigurationByTargetPlanSectionDefinitionId(planSectionDefinitionId);
                targets.add(PointsTarget.forProposal(subProposalId, pdc.getPercentage()));
            } catch (ProposalAttributeNotFoundException  ignored) {
            }
        }
        return targets;
    }

    public static List<PointsTarget> distributeUserDefinedAmongProposals(Proposal proposal, PointType pointType, Set<Long> subProposalIds)  {
        List<PointsTarget> targets = new ArrayList<>();
        for (PointsDistributionConfiguration pdc : PointsClientUtil
                .getPointsDistributionByProposalIdPointTypeId(proposal.getProposalId(), pointType.getId_())) {
            if (pdc.getTargetSubProposalId() > 0 && subProposalIds.contains(pdc.getTargetSubProposalId()) && pdc.getTargetSubProposalId() != proposal.getProposalId()) {
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
