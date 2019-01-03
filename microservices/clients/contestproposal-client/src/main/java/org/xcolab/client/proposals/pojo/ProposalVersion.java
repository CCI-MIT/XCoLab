package org.xcolab.client.proposals.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalVersion extends AbstractProposalVersion implements Serializable {

    public static final TypeProvider<ProposalVersion> TYPES =
            new TypeProvider<>(ProposalVersion.class,
                    new ParameterizedTypeReference<List<ProposalVersion>>() {});

    private final ProposalPhaseClient proposalPhaseClient;

    public ProposalVersion() {
        proposalPhaseClient = ProposalPhaseClientUtil.getClient();
    }

    public ProposalVersion(ProposalVersion value) {
        super(value);
        proposalPhaseClient = ProposalPhaseClientUtil.getClient();
    }

    public ProposalVersion(Long proposalid, Integer version, Long authorUserId,
            Timestamp createdAt, String updatetype, Long updateadditionalid) {
        super(proposalid, version, authorUserId, createdAt, updatetype, updateadditionalid);
        proposalPhaseClient = ProposalPhaseClientUtil.getClient();
    }

    public ProposalVersion(AbstractProposalVersion abstractProposalVersion) {
        super(abstractProposalVersion);
        proposalPhaseClient = ProposalPhaseClientUtil.getClient();
    }

    public long getContestPhaseId() {
        return proposalPhaseClient
                .getProposal2PhaseByProposalIdVersion(getProposalId(), getVersion())
                .getContestPhaseId();
    }
}
