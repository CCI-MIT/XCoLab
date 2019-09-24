package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.util.Assert;

import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.IContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalRibbon implements Serializable {

    private final IContestPhaseRibbonType contestPhaseRibbonType;

    public ProposalRibbon(ProposalWrapper proposal) {
        Assert.notNull(proposal, "Proposal is required");
        this.contestPhaseRibbonType = fetchRibbonType(proposal);
    }

    private IContestPhaseRibbonType fetchRibbonType(ProposalWrapper proposal) {
        final Long proposalId = proposal.getId();
        if (proposalId == null || proposalId == 0) {
            return null;
        }

        final IContestClient contestClient = StaticContestContext.getContestClient();

        ContestPhaseWrapper contestPhase = proposal.getContestPhase();

        IProposalContestPhaseAttribute ribbonAttribute =
                StaticProposalContext.getProposalPhaseClient().getProposalContestPhaseAttribute(
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
        if (contestPhaseRibbonType != null && contestPhaseRibbonType.isShowText()) {
            return contestPhaseRibbonType.getHoverText();
        }
        return "";
    }
}
