package org.xcolab.client.proposals.pojo;

import org.xcolab.client.admin.pojo.ContestType;

import java.util.ArrayList;
import java.util.List;

public class ContestTypeProposal {

    private final ContestType contestType;
    private final List<Proposal> proposals;

    public ContestTypeProposal(ContestType contestType) {
        this(contestType, new ArrayList<Proposal>());
    }

    public ContestTypeProposal(ContestType contestType, List<Proposal> proposals) {
        this.contestType = contestType;
        this.proposals = proposals;
    }

    public ContestType getContestType() {
        return contestType;
    }

    public List<Proposal> getProposals() {
        return proposals;
    }
}
