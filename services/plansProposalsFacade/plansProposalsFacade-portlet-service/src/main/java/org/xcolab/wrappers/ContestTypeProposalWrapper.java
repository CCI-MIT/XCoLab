package org.xcolab.wrappers;



import org.xcolab.client.contest.pojo.ContestType;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper to access a contest type and the user's proposals contained in it.
 */
public class ContestTypeProposalWrapper {

    private final ContestType contestType;
    private final List<BaseProposalWrapper> proposals;

    public ContestTypeProposalWrapper(ContestType contestType) {
        this(contestType, new ArrayList<BaseProposalWrapper>());
    }

    public ContestTypeProposalWrapper(ContestType contestType, List<BaseProposalWrapper> proposals) {
        this.contestType = contestType;
        this.proposals = proposals;
    }

    public ContestType getContestType() {
        return contestType;
    }

    public List<BaseProposalWrapper> getProposals() {
        return proposals;
    }
}
