package org.xcolab.view.pages.profile.beans;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseRibbonType;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.view.pages.profile.entity.Badge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class BadgeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<Badge> badges = new ArrayList<>();

    public BadgeBean(long userId) {
        populateBadges(userId);
    }

    private void populateBadges(long userId) {
        for (Proposal proposal : ProposalClientUtil.getMemberProposals(userId)) {
            getRibbonAttribute(proposal)
                    .ifPresent(ribbonAttribute -> {
                        final ContestPhaseRibbonType ribbonType = ContestClientUtil
                                .getContestPhaseRibbonType(ribbonAttribute.getNumericValue());

                        if (ribbonType.getRibbon() > 0) {
                            final ContestPhase phase = ContestClientUtil.getContestPhase(
                                    ribbonAttribute.getContestPhaseId());
                            badges.add(new Badge(ribbonType, proposal, proposal.getName(),
                                    phase.getContest()));
                        }
                    });
        }
    }

    private Optional<ProposalContestPhaseAttribute> getRibbonAttribute(Proposal proposal) {
        List<Long> phasesForProposal = ProposalPhaseClientUtil.getContestPhasesForProposal(
                proposal.getProposalId());
        return phasesForProposal.stream()
                .map(getRibbonAttributeFunction(proposal.getProposalId()))
                .filter(Objects::nonNull)
                .max(getStartDateComparator());
    }

    private Comparator<ProposalContestPhaseAttribute> getStartDateComparator() {
        return Comparator.comparing(attribute -> ContestClientUtil
                .getContestPhase(attribute.getContestPhaseId()).getPhaseStartDateDt());
    }

    private Function<Long, ProposalContestPhaseAttribute> getRibbonAttributeFunction(
            Long proposalId) {
        return phaseId -> ProposalPhaseClientUtil
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
