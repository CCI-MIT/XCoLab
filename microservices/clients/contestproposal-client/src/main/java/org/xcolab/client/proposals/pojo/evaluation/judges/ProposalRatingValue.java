package org.xcolab.client.proposals.pojo.evaluation.judges;

import org.xcolab.util.http.client.RestService;

public class ProposalRatingValue extends AbstractProposalRatingValue {

    public ProposalRatingValue() {}

    public ProposalRatingValue(ProposalRatingValue value) {
        super(value);
    }

    public ProposalRatingValue(
        Long   id_,
        Long   ratingtypeid,
        Long   value,
        String name,
        String description
    ) {
        super(id_, ratingtypeid, value, name, description);
    }

    public ProposalRatingValue(AbstractProposalRatingValue abstractProposalRatingValue,
            RestService restService) {
        super(abstractProposalRatingValue);
    }
}
