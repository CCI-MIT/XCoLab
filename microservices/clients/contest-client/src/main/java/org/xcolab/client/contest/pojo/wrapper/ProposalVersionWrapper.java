package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalVersion;
import org.xcolab.client.contest.proposals.ProposalPhaseClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalVersionWrapper extends ProposalVersion implements Serializable {

    public static final TypeProvider<ProposalVersionWrapper> TYPES =
            new TypeProvider<>(ProposalVersionWrapper.class,
                    new ParameterizedTypeReference<List<ProposalVersionWrapper>>() {});

    private final ProposalPhaseClient proposalPhaseClient;

    public ProposalVersionWrapper() {
        proposalPhaseClient = StaticProposalContext.getProposalPhaseClient();
    }

    public ProposalVersionWrapper(ProposalVersionWrapper value) {
        super(value);
        proposalPhaseClient = StaticProposalContext.getProposalPhaseClient();
    }

    public ProposalVersionWrapper(Long proposalid, Integer version, Long authorUserId,
            Timestamp createdAt, String updatetype, Long updateadditionalid) {
        super(proposalid, version, authorUserId, createdAt, updatetype, updateadditionalid);
        proposalPhaseClient = StaticProposalContext.getProposalPhaseClient();
    }

    public ProposalVersionWrapper(ProposalVersion abstractProposalVersion) {
        super(abstractProposalVersion);
        proposalPhaseClient = StaticProposalContext.getProposalPhaseClient();
    }

    public long getContestPhaseId() {
        return proposalPhaseClient
                .getProposal2PhaseByProposalIdVersion(getProposalId(), getVersion())
                .getContestPhaseId();
    }
}
