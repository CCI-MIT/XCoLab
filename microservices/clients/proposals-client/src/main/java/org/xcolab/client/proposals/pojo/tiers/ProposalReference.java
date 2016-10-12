package org.xcolab.client.proposals.pojo.tiers;

import org.xcolab.util.http.client.RestService;

public class ProposalReference extends AbstractProposalReference {

    public ProposalReference() {}

    public ProposalReference(ProposalReference value) {
        super(value);
    }

    public ProposalReference(
            Long proposalid,
            Long subproposalid,
            Long sectionattributeid
    ) {
        super(proposalid, subproposalid, sectionattributeid);
    }

    public ProposalReference(AbstractProposalReference abstractProposalReference,
            RestService restService) {
        super(abstractProposalReference);
    }
}
