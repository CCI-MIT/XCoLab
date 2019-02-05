package org.xcolab.client.contest.proposals;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.contest.pojo.IProposalSupporter;
import org.xcolab.client.contest.pojo.IProposalVote;
import org.xcolab.client.contest.pojo.wrapper.SupportedProposal;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class ProposalMemberRatingClientMockImpl implements IProposalMemberRatingClient {

    @Override
    public List<IProposalSupporter> getProposalSupporters(Long proposalId, Long userId) {
        return Collections.emptyList();
    }

    @Override
    public List<IProposalSupporter> getSupportedProposals(Long userId) {
        return Collections.emptyList();
    }

    @Override
    public int getProposalSupportersCount(Long proposalId) {
        return 0;
    }

    @Override
    public boolean isMemberProposalSupporter(Long proposalId, Long userId) {
        return false;
    }

    @Override
    public IProposalSupporter createProposalSupporter(IProposalSupporter proposalSupporter) {
        return null;
    }

    @Override
    public boolean deleteProposalSupporter(Long proposalId, Long userId) {
        return false;
    }

    @Override
    public int countProposalVotes(Long contestPhaseId, Long proposalId, Long userId,
            Boolean isValidOverride) {
        return 0;
    }

    @Override
    public boolean hasUserVoted(Long contestPhaseId, Long proposalId, Long userId) {
        return false;
    }

    @Override
    public List<IProposalVote> getProposalVotes(Long contestPhaseId, Long proposalId, Long userId) {
        return Collections.emptyList();
    }

    @Override
    public boolean updateProposalVote(IProposalVote proposalVote) {
        return false;
    }

    @Override
    public boolean deleteProposalVote(Long proposalId, Long contestPhaseId, Long userId) {
        return false;
    }

    @Override
    public IProposalVote createProposalVote(IProposalVote proposalVote) {
        return null;
    }

    @Override
    public IProposalVote getProposalVoteByProposalIdUserId(Long proposalId, Long userId) {
        return null;
    }
}
