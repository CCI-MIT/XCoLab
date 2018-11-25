package org.xcolab.client.proposals.pojo.evaluation.members;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.sql.Timestamp;
import java.util.List;

public class ProposalSupporter extends AbstractProposalSupporter {

    public static final TypeProvider<ProposalSupporter> TYPES =
            new TypeProvider<>(ProposalSupporter.class,
                    new ParameterizedTypeReference<List<ProposalSupporter>>() {});

    public ProposalSupporter() {}

    public ProposalSupporter(ProposalSupporter value) {
        super(value);
    }

    public ProposalSupporter(
            Long proposalid,
            Long userid,
            Timestamp createdAt
    ) {
        super(proposalid, userid, createdAt);
    }

    public ProposalSupporter(AbstractProposalSupporter abstractProposalSupporter) {
        super(abstractProposalSupporter);
    }
}
