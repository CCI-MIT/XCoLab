package org.xcolab.client.proposals.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.sql.Timestamp;
import java.util.List;

public class ProposalVersion extends AbstractProposalVersion {

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

    public ProposalVersion(AbstractProposalVersion abstractProposalVersion,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalVersion);
        proposalPhaseClient = ProposalPhaseClient.fromNamespace(serviceNamespace);
    }

    public long getContestPhaseId() {
        return proposalPhaseClient
                .getProposal2PhaseByProposalIdVersion(getProposalId(), getVersion())
                .getContestPhaseId();
    }
}
