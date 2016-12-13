package org.xcolab.portlets.userprofile.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseRibbonType;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.portlets.userprofile.entity.Badge;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BadgeBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger _log = LoggerFactory.getLogger(BadgeBean.class);
    private final long userId;
    private final List<Badge> badges;

    public BadgeBean(long userId) {
        this.userId = userId;
        badges = new ArrayList<>();
        fetchBadges();
    }

    private void fetchBadges() {
        for(Proposal p : ProposalClientUtil.getMemberProposals(userId)) {
            try {
                ContestPhaseRibbonType ribbon = getRibbonType(p);
                int proposalRibbon = (ribbon == null) ? -1 : ribbon.getRibbon();
                if (proposalRibbon > 0) {
                    String badgeText = ribbon.getHoverText();
                    Contest contest = ProposalClientUtil.getCurrentContestForProposal(p.getProposalId());
                    String proposalTitle = ProposalAttributeClientUtil
                            .getProposalAttribute(p.getProposalId(), ProposalAttributeKeys.NAME, 0L)
                            .getStringValue();
                    badges.add(new Badge(proposalRibbon, badgeText, p, proposalTitle, contest));
                }
            } catch (ContestNotFoundException e) {
                _log.warn("Could not add badge to user profile view for userId: {} and proposalId: {}",
                        p.getProposalId(), e);
            }
        }
    }

    private ContestPhaseRibbonType getRibbonType(Proposal p) {
        ContestPhaseRibbonType contestPhaseRibbonType = null;
        List<Long> phasesForProposal = ProposalPhaseClientUtil.getContestPhasesForProposal(p.getProposalId());

        Date phaseStartDate = p.getCreateDate();
        for (Long phaseId : phasesForProposal) {
            final ProposalContestPhaseAttribute ribbonAttribute =
                    ProposalPhaseClientUtil.getProposalContestPhaseAttribute(p.getProposalId(),
                            phaseId, ProposalContestPhaseAttributeKeys.RIBBON);
            if (ribbonAttribute != null) {
                long typeId = ribbonAttribute.getNumericValue();

                ContestPhase contestPhase = ContestClientUtil.getContestPhase(phaseId);

                //if there are several phases with ribbons, the latest takes precedence
                if (!phaseStartDate.after(contestPhase.getPhaseStartDateDt())) {
                    contestPhaseRibbonType = ContestClientUtil.getContestPhaseRibbonType(typeId);
                    phaseStartDate = contestPhase.getPhaseStartDateDt();
                }
            }
        }

        return contestPhaseRibbonType;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    @Override
    public String toString() {
        return badges.toString();
    }
}