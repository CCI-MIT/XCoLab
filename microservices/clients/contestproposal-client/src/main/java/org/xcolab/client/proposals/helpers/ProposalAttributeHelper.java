package org.xcolab.client.proposals.helpers;

import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttributeHelperDataDto;

import java.util.Map;

public class ProposalAttributeHelper extends AttributeHelper<ProposalAttribute> {

    private final Proposal proposal;
    private final int version;

    private final ProposalAttributeClient proposalAttributeClient;

    public ProposalAttributeHelper(Proposal proposal, int version, ProposalAttributeClient proposalAttributeClient) {
        this.proposal = proposal;
        this.version = version;
        this.proposalAttributeClient = proposalAttributeClient;
    }

    public ProposalAttributeHelper(Proposal proposal, ProposalAttributeClient proposalAttributeClient) {
        this(proposal, proposal.getCurrentVersion(), proposalAttributeClient);
    }

    @Override
    protected Map<String, Map<Long, ProposalAttribute>> loadAttributeData() {
        final ProposalAttributeHelperDataDto data = proposalAttributeClient
                .getProposalAttributeHelperData(proposal.getProposalId(), version);
        return data.getAttributesByNameAndAdditionalId();
    }

    @Override
    protected boolean isNewRankedHigher(ProposalAttribute oldAttribute,
            ProposalAttribute newAttribute) {
        if (oldAttribute == null || newAttribute == null) {
            throw new IllegalArgumentException("Attributes cannot be null (old, new): "
                    + oldAttribute + ", " + newAttribute);
        }
        return oldAttribute.getVersion() < newAttribute.getVersion();
    }
}
