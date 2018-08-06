package org.xcolab.service.proposal.helper;

import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.model.tables.pojos.Proposal;

import java.util.List;

public class ProposalContestPhaseAttributeHelper {

    private final Long proposalId;
    private Long contestPhaseId;
    private List<ProposalContestPhaseAttribute> proposalContestPhaseAttributes;

    public ProposalContestPhaseAttributeHelper(Proposal proposal, ContestPhase contestPhase) {
        this.proposalId = proposal.getId();
        if (contestPhase != null) {
            this.contestPhaseId = contestPhase.getId();
                proposalContestPhaseAttributes = ProposalPhaseClientUtil
                        .getAllProposalContestPhaseProposalAttributes(contestPhaseId, proposalId);
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
            attribute.setContestPhaseId(contestPhaseId);
            attribute.setName(attributeName);
            ProposalPhaseClientUtil.createProposalContestPhaseAttribute(attribute);

        return attribute;
    }
}
