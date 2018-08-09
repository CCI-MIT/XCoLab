package org.xcolab.client.proposals.pojo.evaluation.judges;

import org.xcolab.util.http.client.enums.ServiceNamespace;

public class ProposalRatingType extends AbstractProposalRatingType {

    public ProposalRatingType() {}

    public ProposalRatingType(ProposalRatingType value) {
        super(value);
    }

    public ProposalRatingType(
            Long id,
            String label,
            String description,
            Integer judgetype,
            Boolean isactive
    ) {
        super(id, label, description, judgetype, isactive);
    }

    public ProposalRatingType(AbstractProposalRatingType abstractProposalRatingType,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalRatingType);
    }
}
