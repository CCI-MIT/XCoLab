package org.xcolab.view.pages.profile.beans;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.IContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.view.pages.profile.entity.Badge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BadgeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<Badge> badges;

    public BadgeBean(long userId) {
        badges = getBadges(userId);
    }

    private List<Badge> getBadges(long userId) {
        final List<Badge> badges = new ArrayList<>();
        for (ProposalWrapper proposal : StaticProposalContext.getProposalClient()
                .getMemberProposals(userId)) {
            final Optional<IProposalContestPhaseAttribute> ribbonAttributeOpt =
                    getLatestRibbonAttribute(new ProposalWrapper(proposal));
            if (ribbonAttributeOpt.isPresent()) {
                final IProposalContestPhaseAttribute ribbonAttribute = ribbonAttributeOpt.get();
                final IContestPhaseRibbonType ribbonType = StaticContestContext.getContestClient()
                        .getContestPhaseRibbonType(ribbonAttribute.getNumericValue());

                if (ribbonType.getRibbon() > 0) {
                    final ContestPhaseWrapper phase = StaticContestContext.getContestClient()
                            .getContestPhase(ribbonAttribute.getContestPhaseId());
                    badges.add(new Badge(ribbonType, new ProposalWrapper(proposal), proposal.getName(),
                            phase.getContest()));
                }
            }
        }
        return badges;
    }

    private Optional<IProposalContestPhaseAttribute> getLatestRibbonAttribute(
            ProposalWrapper proposal) {
        List<Long> phasesForProposal = StaticProposalContext.getProposalPhaseClient()
                .getContestPhasesForProposal(proposal.getId());
        return phasesForProposal.stream()
                .map(phaseId -> getRibbonAttribute(proposal.getId(), phaseId))
                .filter(Objects::nonNull)
                .max(Comparator.comparing(phase -> phase.getStartDate()));
    }

    private IProposalContestPhaseAttribute getRibbonAttribute(long proposalId, long phaseId) {
        return StaticProposalContext.getProposalPhaseClient()
                .getProposalContestPhaseAttribute(proposalId, phaseId,
                        ProposalContestPhaseAttributeKeys.RIBBON);
    }

    public List<Badge> getBadges() {
        return badges;
    }

    @Override
    public String toString() {
        return badges.toString();
    }
}
