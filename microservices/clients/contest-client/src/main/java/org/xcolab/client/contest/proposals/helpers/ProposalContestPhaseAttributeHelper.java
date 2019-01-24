package org.xcolab.client.contest.proposals.helpers;

import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;

import java.util.List;

public class ProposalContestPhaseAttributeHelper {

    private final Long proposalId;
    private Long contestPhaseId;
    private List<IProposalContestPhaseAttribute> proposalContestPhaseAttributes;

    public ProposalContestPhaseAttributeHelper(ProposalWrapper proposal, ContestPhaseWrapper contestPhase) {
        this.proposalId = proposal.getId();
        if (contestPhase != null) {
            this.contestPhaseId = contestPhase.getId();
                proposalContestPhaseAttributes = StaticProposalContext.getProposalPhaseClient()
                        .getAllProposalContestPhaseProposalAttributes(contestPhaseId, proposalId);
        }
    }

    public long getAttributeLongValue(String attributeName, long additionalId, long defaultVal) {
        IProposalContestPhaseAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getNumericValue();
    }

    public String getAttributeStringValue(String attributeName, long additionalId, String defaultVal)  {
        IProposalContestPhaseAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getStringValue();
    }

    public IProposalContestPhaseAttribute getAttributeOrNull(String attributeName, long additionalId) {
        if (proposalContestPhaseAttributes != null) {
            for (IProposalContestPhaseAttribute attr: proposalContestPhaseAttributes) {
                if (attr.getName().equals(attributeName) && attr.getAdditionalId() == additionalId) {
                    return attr;
                }
            }
        }
        return null;
    }

    public IProposalContestPhaseAttribute getAttributeOrCreate(
            String attributeName, long additionalId) {
        IProposalContestPhaseAttribute attribute = getAttributeOrNull(attributeName, additionalId);
        if (attribute != null) {
                return attribute;
            }

            attribute = new ProposalContestPhaseAttribute();
            attribute.setProposalId(proposalId);
            attribute.setContestPhaseId(contestPhaseId);
            attribute.setName(attributeName);
            attribute = StaticProposalContext.getProposalPhaseClient()
                    .createProposalContestPhaseAttribute(attribute);

        return attribute;
    }
}
