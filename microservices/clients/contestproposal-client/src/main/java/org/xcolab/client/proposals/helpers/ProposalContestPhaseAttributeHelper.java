package org.xcolab.client.proposals.helpers;

import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;

import java.util.List;

public class ProposalContestPhaseAttributeHelper {

    private final Long proposalId;
    private Long contestPhasePK;
    private List<ProposalContestPhaseAttribute> proposalContestPhaseAttributes;

    public ProposalContestPhaseAttributeHelper(Proposal proposal, ContestPhase contestPhase) {
        this.proposalId = proposal.getId();
        if (contestPhase != null) {
            this.contestPhasePK = contestPhase.getId();
                proposalContestPhaseAttributes = ProposalPhaseClientUtil
                        .getAllProposalContestPhaseProposalAttributes(contestPhasePK, proposalId);
        }
    }

    public long getAttributeLongValue(String attributeName, long additionalId, long defaultVal) {
        ProposalContestPhaseAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getNumericValue();
    }

    public String getAttributeStringValue(String attributeName, long additionalId, String defaultVal)  {
        ProposalContestPhaseAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getStringValue();
    }

    public ProposalContestPhaseAttribute getAttributeOrNull(String attributeName, long additionalId) {
        if (proposalContestPhaseAttributes != null) {
            for (ProposalContestPhaseAttribute attr: proposalContestPhaseAttributes) {
                if (attr.getName().equals(attributeName) && attr.getAdditionalId() == additionalId) {
                    return attr;
                }
            }
        }
        return null;
    }

    public ProposalContestPhaseAttribute getAttributeOrCreate(
            String attributeName, long additionalId) {
        ProposalContestPhaseAttribute attribute = getAttributeOrNull(attributeName, additionalId);
        if (attribute != null) {
                return attribute;
            }

            attribute = new ProposalContestPhaseAttribute();
            attribute.setProposalId(proposalId);
            attribute.setContestPhaseId(contestPhasePK);
            attribute.setName(attributeName);
            attribute = ProposalPhaseClientUtil.createProposalContestPhaseAttribute(attribute);

        return attribute;
    }
}
