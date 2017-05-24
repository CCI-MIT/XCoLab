package org.xcolab.client.proposals.helpers;

import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;

import java.util.List;

//TODO: functionality is duplicated in the service
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
    protected List<ProposalAttribute> getAttributes() {
        return proposalAttributeClient.getAllProposalAttributes(proposal.getProposalId(), version);
    }

    @Override
    protected boolean isNewRankedHigher(ProposalAttribute oldAttribute,
            ProposalAttribute newAttribute) {
        return oldAttribute.getVersion() < newAttribute.getVersion();
    }
}
