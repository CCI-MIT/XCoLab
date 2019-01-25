package org.xcolab.client.contest.pojo.wrapper;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalVersion;
import org.xcolab.client.contest.proposals.ProposalPhaseClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;

import java.sql.Timestamp;

public class ProposalVersionWrapper extends ProposalVersion {

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
