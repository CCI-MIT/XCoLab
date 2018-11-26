package org.xcolab.client.proposals.pojo.evaluation.members;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalSupporter extends AbstractProposalSupporter implements Serializable {

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
