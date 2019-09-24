package org.xcolab.client.contest.proposals.helpers;

import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttributeHelperDataDto;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalAttributeClient;

import java.util.Map;

public class ProposalAttributeHelper extends AttributeHelper<ProposalAttribute> {

    private final ProposalWrapper proposal;
    private final int version;

    private final IProposalAttributeClient proposalAttributeClient;

    public ProposalAttributeHelper(ProposalWrapper proposal, int version, IProposalAttributeClient proposalAttributeClient) {
        this.proposal = proposal;
        this.version = version;
        this.proposalAttributeClient = proposalAttributeClient;
    }

    public ProposalAttributeHelper(ProposalWrapper proposal, IProposalAttributeClient proposalAttributeClient) {
        this(proposal, proposal.getCurrentVersion(), proposalAttributeClient);
    }

    @Override
    protected Map<String, Map<Long, ProposalAttribute>> loadAttributeData() {
        final ProposalAttributeHelperDataDto data = proposalAttributeClient
                .getProposalAttributeHelperData(proposal.getId(), version);
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
