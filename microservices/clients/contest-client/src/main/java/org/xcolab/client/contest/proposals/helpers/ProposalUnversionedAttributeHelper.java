package org.xcolab.client.contest.proposals.helpers;

import org.xcolab.client.contest.proposals.ProposalAttributeClient;
import org.xcolab.client.contest.pojo.Proposal;
import org.xcolab.client.contest.pojo.ProposalUnversionedAttribute;
import org.xcolab.client.contest.pojo.ProposalUnversionedAttributeHelperDataDto;

import java.util.Map;

public class ProposalUnversionedAttributeHelper extends AttributeHelper<ProposalUnversionedAttribute> {

    private final Proposal proposal;
    private final ProposalAttributeClient proposalAttributeClient;

    public ProposalUnversionedAttributeHelper(Proposal proposal,
            ProposalAttributeClient proposalAttributeClient) {
        this.proposal = proposal;
        this.proposalAttributeClient = proposalAttributeClient;
    }

    @Override
    protected Map<String, Map<Long, ProposalUnversionedAttribute>> loadAttributeData() {
        final ProposalUnversionedAttributeHelperDataDto data = proposalAttributeClient
                .getProposalUnversionedAttributeHelperData(proposal.getId());
        return data.getAttributesByNameAndAdditionalId();
    }

    @Override
    protected boolean isNewRankedHigher(ProposalUnversionedAttribute oldAttribute,
            ProposalUnversionedAttribute newAttribute) {
        //there should be no collisions
        return true;
    }
}
