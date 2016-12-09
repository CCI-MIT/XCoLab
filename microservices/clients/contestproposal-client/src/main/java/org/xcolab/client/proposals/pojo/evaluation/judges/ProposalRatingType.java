package org.xcolab.client.proposals.pojo.evaluation.judges;

import org.xcolab.util.http.client.RestService;

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
            RestService restService) {
        super(abstractProposalRatingType);
    }
}
