package org.xcolab.client.proposals.pojo.proposals;

import org.springframework.util.Assert;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseRibbonType;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;


public class ProposalRibbon {

    private final ContestPhaseRibbonType contestPhaseRibbonType;

    public ProposalRibbon(Proposal proposal) {
        Assert.notNull(proposal, "Proposal is required");
        this.contestPhaseRibbonType = fetchRibbonType(proposal);
    }

    private ContestPhaseRibbonType fetchRibbonType(Proposal proposal) {
        final Long proposalId = proposal.getId();
        if (proposalId == null || proposalId == 0) {
            return null;
        }

        final ContestClient contestClient = ContestClientUtil.getClient();
        final ProposalPhaseClient proposalPhaseClient =
                ProposalPhaseClientUtil.getClient();

        ContestPhase contestPhase = proposal.getContestPhase();

        ProposalContestPhaseAttribute ribbonAttribute =
                proposalPhaseClient.getProposalContestPhaseAttribute(
                        proposalId, contestPhase.getId(),
                        ProposalContestPhaseAttributeKeys.RIBBON);
        if (ribbonAttribute != null) {
            long typeId = ribbonAttribute.getNumericValue();
            if (typeId >= 0) {
                return contestClient.getContestPhaseRibbonType(typeId);
            }
        }
        return null;
    }

    public int getRibbon() {
        if (contestPhaseRibbonType != null) {
            return contestPhaseRibbonType.getRibbon();
        }
        return 0;
    }

    public long getRibbonId() {
        if (contestPhaseRibbonType != null) {
            return contestPhaseRibbonType.getId();
        }
        return 0L;
    }

    public int getSortOrder() {
        if (contestPhaseRibbonType != null) {
            return contestPhaseRibbonType.getSortOrder();
        }
        return Integer.MAX_VALUE;
    }

    public String getRibbonTitle() {
        if (contestPhaseRibbonType != null) {
            return contestPhaseRibbonType.getTitle();
        }
        return "";
    }

    public String getRibbonText() {
        if (contestPhaseRibbonType != null && contestPhaseRibbonType.getShowText()) {
            return contestPhaseRibbonType.getHoverText();
        }
        return "";
    }
}
