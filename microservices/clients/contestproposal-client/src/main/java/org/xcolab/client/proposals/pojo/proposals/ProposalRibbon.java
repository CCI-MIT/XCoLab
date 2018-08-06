package org.xcolab.client.proposals.pojo.proposals;

import org.springframework.util.Assert;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseRibbonType;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.http.client.enums.ServiceNamespace;


public class ProposalRibbon {

    private final ContestPhaseRibbonType contestPhaseRibbonType;

    public ProposalRibbon(Proposal proposal, ServiceNamespace proposalService) {
        Assert.notNull(proposal, "Proposal is required");
        if (proposalService != null) {
            this.contestPhaseRibbonType = fetchRibbonType(proposal, proposalService);
        } else {
            this.contestPhaseRibbonType = null;
        }
    }

    private ContestPhaseRibbonType fetchRibbonType(Proposal proposal,
            ServiceNamespace serviceNamespace) {
        final Long proposalId = proposal.getId();
        if (proposalId == null || proposalId == 0) {
            return null;
        }

        final ContestClient contestClient = ContestClient.fromNamespace(serviceNamespace);
        final ProposalPhaseClient proposalPhaseClient =
                ProposalPhaseClient.fromNamespace(serviceNamespace);

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
