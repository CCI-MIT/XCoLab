package org.xcolab.service.contest.proposal.domain.proposalteammember;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalTeamMember;

import java.util.List;

import static org.xcolab.model.Tables.PROPOSAL_TEAM_MEMBER;

@Repository
public class ProposalTeamMemberDaoImpl implements ProposalTeamMemberDao {

    private final DSLContext dslContext;

    @Autowired
    public ProposalTeamMemberDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void addUserToTeam(long proposalId, long userId) {
        dslContext.insertInto(PROPOSAL_TEAM_MEMBER)
                .set(PROPOSAL_TEAM_MEMBER.PROPOSAL_ID, proposalId)
                .set(PROPOSAL_TEAM_MEMBER.USER_ID, userId)
                .execute();
    }

    @Override
    public List<ProposalTeamMember> findByProposalId(long proposalId) {
        return dslContext.select().from(PROPOSAL_TEAM_MEMBER)
                .where(PROPOSAL_TEAM_MEMBER.PROPOSAL_ID.eq(proposalId))
                .fetch().into(ProposalTeamMember.class);
    }

    @Override
    public List<ProposalTeamMember> findByUserId(long userId) {
        return dslContext.select().from(PROPOSAL_TEAM_MEMBER)
                .where(PROPOSAL_TEAM_MEMBER.USER_ID.eq(userId))
                .fetch().into(ProposalTeamMember.class);
    }

    @Override
    public boolean exists(long proposalId, long userId) {
        return dslContext.fetchExists(DSL.selectFrom(PROPOSAL_TEAM_MEMBER)
                .where(PROPOSAL_TEAM_MEMBER.PROPOSAL_ID.eq(proposalId)
                        .and(PROPOSAL_TEAM_MEMBER.USER_ID.eq(userId))));
    }

    @Override
    public boolean delete(long proposalId, long userId) {
        return dslContext.deleteFrom(PROPOSAL_TEAM_MEMBER)
                .where(PROPOSAL_TEAM_MEMBER.PROPOSAL_ID.eq(proposalId)
                        .and(PROPOSAL_TEAM_MEMBER.USER_ID.eq(userId)))
                .execute() > 0;
    }

    @Override
    public boolean deleteByProposalId(long proposalId) {
        return dslContext.deleteFrom(PROPOSAL_TEAM_MEMBER)
                .where(PROPOSAL_TEAM_MEMBER.PROPOSAL_ID.eq(proposalId))
                .execute() > 0;
    }

    @Override
    public boolean deleteByUserId(long userId) {
        return dslContext.deleteFrom(PROPOSAL_TEAM_MEMBER)
                .where(PROPOSAL_TEAM_MEMBER.PROPOSAL_ID.eq(userId))
                .execute() > 0;
    }
}
