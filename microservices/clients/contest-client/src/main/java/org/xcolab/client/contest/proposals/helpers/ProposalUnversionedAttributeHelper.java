package org.xcolab.client.contest.proposals.helpers;

import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttribute;
import org.xcolab.client.contest.proposals.IProposalAttributeClient;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttributeHelperDataDto;

import java.util.Map;

public class ProposalUnversionedAttributeHelper extends AttributeHelper<ProposalUnversionedAttribute> {

    private final ProposalWrapper proposal;
    private final IProposalAttributeClient proposalAttributeClient;

    public ProposalUnversionedAttributeHelper(ProposalWrapper proposal,
            IProposalAttributeClient proposalAttributeClient) {
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
