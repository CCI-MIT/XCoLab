package org.xcolab.client.contest.pojo.wrapper;

import org.xcolab.client.admin.pojo.ContestType;

import java.util.ArrayList;
import java.util.List;

public class ContestTypeProposal {

    private final ContestType contestType;
    private final List<ProposalWrapper> proposals;

    public ContestTypeProposal(ContestType contestType) {
        this(contestType, new ArrayList<>());
    }

    public ContestTypeProposal(ContestType contestType, List<ProposalWrapper> proposals) {
        this.contestType = contestType;
        this.proposals = proposals;
    }

    public ContestType getContestType() {
        return contestType;
    }

    public List<ProposalWrapper> getProposals() {
        return proposals;
    }
}
