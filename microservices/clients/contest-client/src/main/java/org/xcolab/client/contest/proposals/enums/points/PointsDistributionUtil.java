package org.xcolab.client.contest.proposals.enums.points;

import org.xcolab.client.contest.pojo.IPointsDistributionConfiguration;
import org.xcolab.client.contest.pojo.IProposalReference;
import org.xcolab.client.contest.pojo.wrapper.PointTypeWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.ProposalAttributeNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PointsDistributionUtil {
    public static List<PointsTarget> distributeEquallyAmongContributors(long proposalId)
             {
        List<PointsTarget> targets = new ArrayList<>();
        List<UserWrapper> members = StaticProposalContext.getProposalClient()
                .getProposalMembers(proposalId);
        for (UserWrapper u : members) {
            targets.add(PointsTarget.forUser(u.getId(), 1.0d / members.size()));
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

    public static List<PointsTarget> distributeSectionDefinedAmongProposals(ProposalWrapper proposal, PointTypeWrapper pointType, Set<Long> subProposalIds)  {
        List<PointsTarget> targets = new ArrayList<>();
        for (long subProposalId : subProposalIds) {
            try {
                IProposalReference reference = StaticProposalContext.getProposalClient()
                        .getProposalReferenceByProposalIdSubProposalId(proposal.getId(), subProposalId);
                final ProposalAttribute referenceSectionProposalAttribute =
                        StaticProposalContext.getProposalAttributeClient()
                        .getProposalAttribute(reference.getSectionAttributeId());
                final long proposalTemplateSectionDefinitionId = referenceSectionProposalAttribute.getAdditionalId();

                IPointsDistributionConfiguration pdc = StaticProposalContext.getPointsClient()
                        .getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionIdOrNull(proposalTemplateSectionDefinitionId);
                //TODO COLAB-2597: do we need to do anything else if it's null?
                if (pdc != null) {
                    targets.add(PointsTarget.forProposal(subProposalId, pdc.getPercentage()));
                }
            } catch (ProposalAttributeNotFoundException  ignored) {
            }
        }
        return targets;
    }

    public static List<PointsTarget> distributeUserDefinedAmongProposals(ProposalWrapper proposal, PointTypeWrapper pointType, Set<Long> subProposalIds)  {
        List<PointsTarget> targets = new ArrayList<>();
        for (IPointsDistributionConfiguration pdc : StaticProposalContext.getPointsClient()
                .getPointsDistributionByProposalIdPointTypeId(proposal.getId(), pointType.getId())) {
            if (pdc.getTargetSubProposalId() > 0 && subProposalIds.contains(pdc.getTargetSubProposalId()) && pdc.getTargetSubProposalId() != proposal.getId()) {
                PointsTarget target = new PointsTarget();
                target.setProposalId(pdc.getTargetSubProposalId());
                target.setPercentage(pdc.getPercentage());
                targets.add(target);
            }
        }
        return targets;
    }

    public static List<PointsTarget> distributeAmongProposals(DistributionStrategy distributionStrategy, ProposalWrapper parentProposals, PointTypeWrapper pointType, Set<Long> proposalIds) {
        switch (distributionStrategy) {
            case USER_DEFINED:
                return distributeUserDefinedAmongProposals(parentProposals, pointType, proposalIds);
            case EQUAL_DIVISION:
                return distributeEquallyAmongProposals(proposalIds);
            case SECTION_DEFINED:
                return distributeSectionDefinedAmongProposals(parentProposals, pointType, proposalIds);
            //missing default case
            default:
            // add default case
                break;

        }
        return Collections.emptyList();
    }
}
