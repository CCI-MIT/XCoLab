package org.xcolab.service.contest.proposal.domain.proposalteammember;

import org.xcolab.client.contest.pojo.wrapper.ProposalTeamMemberWrapper;

import java.util.List;

public interface ProposalTeamMemberDao {

    void addUserToTeam(long proposalId, long userId);

    List<ProposalTeamMemberWrapper> findByProposalId(long proposalId);

    List<ProposalTeamMemberWrapper> findByUserId(long userId);

    boolean exists(long proposalId, long userId);

    boolean delete(long proposalId, long userId);

    boolean deleteByProposalId(long proposalId);

    boolean deleteByUserId(long userId);
}
