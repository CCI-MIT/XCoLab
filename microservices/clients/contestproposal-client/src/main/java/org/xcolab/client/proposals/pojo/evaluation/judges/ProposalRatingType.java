package org.xcolab.client.proposals.pojo.evaluation.judges;

import org.xcolab.commons.http.client.enums.ServiceNamespace;

public class ProposalRatingType extends AbstractProposalRatingType {

    public ProposalRatingType() {}

    public ProposalRatingType(ProposalRatingType value) {
        super(value);
    }

    public ProposalRatingType(
            Long id_,
            String label,
            String description,
            Integer judgetype,
            Boolean isactive
    ) {
        super(id_, label, description, judgetype, isactive);
    }

    public ProposalRatingType(AbstractProposalRatingType abstractProposalRatingType,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalRatingType);
    }
}
