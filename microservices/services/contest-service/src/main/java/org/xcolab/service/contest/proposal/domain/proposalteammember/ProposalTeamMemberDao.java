package org.xcolab.service.contest.proposal.domain.proposalteammember;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalTeamMember;

import java.util.List;

public interface ProposalTeamMemberDao {

    void addUserToTeam(long proposalId, long userId);

    List<ProposalTeamMember> findByProposalId(long proposalId);

    List<ProposalTeamMember> findByUserId(long userId);

    boolean exists(long proposalId, long userId);

    boolean delete(long proposalId, long userId);

    boolean deleteByProposalId(long proposalId);

    boolean deleteByUserId(long userId);
}
