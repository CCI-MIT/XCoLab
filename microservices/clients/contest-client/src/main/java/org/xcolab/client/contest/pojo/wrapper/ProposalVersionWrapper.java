package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalVersion;
import org.xcolab.client.contest.proposals.IProposalPhaseClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProposalVersionWrapper extends ProposalVersion implements Serializable {

    private final IProposalPhaseClient proposalPhaseClient;

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

    @JsonIgnore
    public long getContestPhaseId() {
        return proposalPhaseClient
                .getProposal2PhaseByProposalIdVersion(getProposalId(), getVersion())
                .getContestPhaseId();
    }
}
