package org.xcolab.client.contest.proposals;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.contest.pojo.IProposalMoveHistory;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class ProposalMoveClientMockImpl implements IProposalMoveClient {

    @Override
    public List<IProposalMoveHistory> getProposalMoveHistories(Long sourceProposalId,
            Long sourceContestId, Long targetProposalId, Long targetContestId) {
        return Collections.emptyList();
    }

    @Override
    public IProposalMoveHistory createProposalMoveHistory(
            IProposalMoveHistory proposalMoveHistory) {
        return null;
    }
}
