package org.xcolab.client.proposals.helpers;


import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.pojo.ContestPhase;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalContestPhaseAttribute;

import java.util.List;

/**
 * Utility class to retrieve ProposalContestPhaseAttributes in various formats
 * TODO: use new ProposalContestPhaseAttributeLocalServiceUtil methods!
 */
public class ProposalContestPhaseAttributeHelper {
    //private final static Log _log = LogFactoryUtil.getLog(ProposalContestPhaseAttributeHelper.class);

    private final Long proposalId;
    private Long contestPhasePK;
    private List<ProposalContestPhaseAttribute> proposalContestPhaseAttributes;

    public ProposalContestPhaseAttributeHelper(Proposal proposal, ContestPhase contestPhase) {
        this.proposalId = proposal.getProposalId();
        if (contestPhase != null) {
            this.contestPhasePK = contestPhase.getContestPhasePK();
                proposalContestPhaseAttributes = ProposalsClient.getAllProposalContestPhaseProposalAttributes(contestPhasePK, proposalId);
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
        ProposalContestPhaseAttribute attribute = null;
            attribute = getAttributeOrNull(attributeName, additionalId);
            if (attribute != null) {
                return attribute;
            }

            attribute = new ProposalContestPhaseAttribute();
            attribute.setProposalId(proposalId);
            attribute.setContestPhaseId(contestPhasePK);
            attribute.setName(attributeName);
            ProposalsClient.createProposalContestPhaseAttribute(attribute);

        return attribute;
    }
}
