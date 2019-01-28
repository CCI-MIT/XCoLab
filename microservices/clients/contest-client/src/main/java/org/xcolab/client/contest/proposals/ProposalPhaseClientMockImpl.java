package org.xcolab.client.contest.proposals;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class ProposalPhaseClientMockImpl implements IProposalPhaseClient {

    @Override
    public List<IProposal2Phase> getProposal2Phases(Long proposalId, Long contestPhaseId,
            Integer version) {
        return Collections.emptyList();
    }

    @Override
    public void createProposal2Phase(IProposal2Phase proposal2Phase) {

    }

    @Override
    public boolean updateProposal2Phase(IProposal2Phase proposal2Phase) {
        return false;
    }

    @Override
    public boolean deleteProposal2Phase(IProposal2Phase proposal2Phase) {
        return false;
    }

    @Override
    public int getProposalCountForActiveContestPhase(Long proposal2PhaseId) {
        return 0;
    }

    @Override
    public boolean promoteProposal(Long proposalId, Long activePhaseForContest,
            Long currentProposalContestPhase) {
        return false;
    }

    @Override
    public List<IProposalContestPhaseAttribute> getProposalContestPhaseAttributes(
            Long contestPhaseId, Long proposalId, String name) {
        return Collections.emptyList();
    }

    @Override
    public IProposalContestPhaseAttribute getProposalContestPhaseAttribute(Long contestPhaseId,
            Long proposalId, String name) {
        return null;
    }

    @Override
    public boolean updateProposalContestPhaseAttribute(
            IProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return false;
    }

    @Override
    public IProposalContestPhaseAttribute createProposalContestPhaseAttribute(
            IProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        return null;
    }

    @Override
    public boolean deleteProposalContestPhaseAttribute(Long id) {
        return false;
    }

    @Override
    public List<Long> getContestPhasesForProposal(Long proposalId) {
        return Collections.emptyList();
    }
}
