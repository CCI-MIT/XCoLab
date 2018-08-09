package org.xcolab.client.proposals.pojo.evaluation.judges;


import org.xcolab.util.http.client.enums.ServiceNamespace;

public class ProposalRatingValue extends AbstractProposalRatingValue {

    public ProposalRatingValue() {}

    public ProposalRatingValue(ProposalRatingValue value) {
        super(value);
    }

    public ProposalRatingValue(
        Long   id,
        Long   ratingtypeid,
        Long   value,
        String name,
        String description
    ) {
        super(id, ratingtypeid, value, name, description);
    }

    public ProposalRatingValue(AbstractProposalRatingValue abstractProposalRatingValue,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalRatingValue);
    }
}
