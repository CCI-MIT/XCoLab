package org.xcolab.view.pages.profile.beans;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.ContestPhaseRibbonType;
import org.xcolab.client.contest.proposals.ProposalClientUtil;
import org.xcolab.client.contest.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.contest.pojo.Proposal;
import org.xcolab.client.contest.pojo.ProposalContestPhaseAttribute;
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
        for (Proposal proposal : ProposalClientUtil.getMemberProposals(userId)) {
            final Optional<ProposalContestPhaseAttribute> ribbonAttributeOpt =
                    getLatestRibbonAttribute(proposal);
            if (ribbonAttributeOpt.isPresent()) {
                final ProposalContestPhaseAttribute ribbonAttribute = ribbonAttributeOpt.get();
                final ContestPhaseRibbonType ribbonType = ContestClientUtil
                        .getContestPhaseRibbonType(ribbonAttribute.getNumericValue());

                if (ribbonType.getRibbon() > 0) {
                    final ContestPhase phase = ContestClientUtil.getContestPhase(
                            ribbonAttribute.getContestPhaseId());
                    badges.add(new Badge(ribbonType, proposal, proposal.getName(),
                            phase.getContest()));
                }
            }
        }
        return badges;
    }

    private Optional<ProposalContestPhaseAttribute> getLatestRibbonAttribute(Proposal proposal) {
        List<Long> phasesForProposal = ProposalPhaseClientUtil.getContestPhasesForProposal(
                proposal.getId());
        return phasesForProposal.stream()
                .map(phaseId -> getRibbonAttribute(proposal.getId(), phaseId))
                .filter(Objects::nonNull)
                .max(Comparator.comparing(ProposalContestPhaseAttribute::getStartDate));
    }

    private ProposalContestPhaseAttribute getRibbonAttribute(long proposalId, long phaseId) {
        return ProposalPhaseClientUtil.getProposalContestPhaseAttribute(proposalId, phaseId,
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
