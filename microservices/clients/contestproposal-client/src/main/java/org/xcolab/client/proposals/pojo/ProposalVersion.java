package org.xcolab.client.proposals.pojo;

import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.util.http.client.RestService;

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

    public ProposalVersion(Long proposalid, Integer version, Long authorid,
            Timestamp createdate, String updatetype, Long updateadditionalid) {
        super(proposalid, version, authorid, createdate, updatetype, updateadditionalid);
        proposalPhaseClient = ProposalPhaseClientUtil.getClient();
    }

    public ProposalVersion(AbstractProposalVersion abstractProposalVersion,
            RestService restService) {
        super(abstractProposalVersion);
        proposalPhaseClient = ProposalPhaseClient.fromService(restService);
    }

    public long getContestPhaseId() {
        return proposalPhaseClient
                .getProposal2PhaseByProposalIdVersion(getProposalId(), getVersion())
                .getContestPhaseId();
    }
}
