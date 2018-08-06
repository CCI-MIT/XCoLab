package org.xcolab.client.proposals.pojo;

import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.sql.Timestamp;

public class ProposalVersion extends AbstractProposalVersion {

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
