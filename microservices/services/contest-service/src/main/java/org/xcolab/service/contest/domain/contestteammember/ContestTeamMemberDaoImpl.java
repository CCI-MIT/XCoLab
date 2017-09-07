package org.xcolab.service.contest.domain.contestteammember;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.pojos.ContestTeamMember;
import org.xcolab.model.tables.records.ContestTeamMemberRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.CONTEST;
import static org.xcolab.model.Tables.CONTEST_TEAM_MEMBER;

@Repository
public class ContestTeamMemberDaoImpl implements ContestTeamMemberDao{

    private final DSLContext dslContext;

    @Autowired
    public ContestTeamMemberDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }

    @Override
    public ContestTeamMember create(ContestTeamMember contestTeamMember) {

        ContestTeamMemberRecord ret = this.dslContext.insertInto(CONTEST_TEAM_MEMBER)
                .set(CONTEST_TEAM_MEMBER.CONTEST_ID, contestTeamMember.getContestId())
                .set(CONTEST_TEAM_MEMBER.USER_ID, contestTeamMember.getUserId())
                .set(CONTEST_TEAM_MEMBER.ROLE_ID, contestTeamMember.getRoleId())
                .returning(CONTEST_TEAM_MEMBER.ID_)
                .fetchOne();
        if (ret == null) {
            throw new IllegalStateException("Could not retrieve inserted id");
        }
        contestTeamMember.setId_(ret.getValue(CONTEST_TEAM_MEMBER.ID_));
        return contestTeamMember;
    }

    @Override
    public Optional<ContestTeamMember> get(Long id_) throws NotFoundException{

        final Record record =  this.dslContext.selectFrom(CONTEST_TEAM_MEMBER)
                .where(CONTEST_TEAM_MEMBER.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ContestTeamMember.class));

    }

    @Override
    public boolean exists(Long id_) {
        return dslContext.selectCount()
                .from(CONTEST_TEAM_MEMBER)
                .where(CONTEST_TEAM_MEMBER.ID_.eq(id_))
                .fetchOne().into(Integer.class) > 0;

    }
    @Override
    public ContestTeamMember findOneBy(Long memberId, Long contestId, Long roleId) {

        final Record record =  this.dslContext.selectFrom(CONTEST_TEAM_MEMBER)
                .where(CONTEST_TEAM_MEMBER.USER_ID.eq(memberId))
                .and(CONTEST_TEAM_MEMBER.CONTEST_ID.eq(contestId))
                .and(CONTEST_TEAM_MEMBER.ROLE_ID.eq(roleId))
                .fetchOne();

        if (record == null) {
            return null;
        }
        return record.into(ContestTeamMember.class);
    }

    @Override
    public boolean update(ContestTeamMember contestTeamMember) {
        return dslContext.update(CONTEST_TEAM_MEMBER)
                .set(CONTEST_TEAM_MEMBER.CONTEST_ID, contestTeamMember.getContestId())
                .set(CONTEST_TEAM_MEMBER.USER_ID, contestTeamMember.getUserId())
                .set(CONTEST_TEAM_MEMBER.ROLE_ID, contestTeamMember.getRoleId())
                .where(CONTEST_TEAM_MEMBER.ID_.eq(contestTeamMember.getId_()))
                .execute() > 0;
    }

    @Override
    public List<ContestTeamMember> findByGiven(Long memberId, Long contestId, Long roleId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_TEAM_MEMBER).getQuery();

        if (memberId != null) {
            query.addConditions(CONTEST_TEAM_MEMBER.USER_ID.eq(memberId));
        }
        if (contestId != null) {
            query.addConditions(CONTEST_TEAM_MEMBER.CONTEST_ID.eq(contestId));
        }
        if (roleId != null) {
            query.addConditions(CONTEST_TEAM_MEMBER.ROLE_ID.eq(roleId));
        }
        return query.fetchInto(ContestTeamMember.class);
    }

    @Override
    public List<ContestTeamMember> findContestYear(Long categoryId, Long contestYear) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_TEAM_MEMBER).getQuery();
        query.addJoin(CONTEST,CONTEST.CONTEST_PK.eq(CONTEST_TEAM_MEMBER.CONTEST_ID));

        query.addConditions(CONTEST.CONTEST_YEAR.eq(contestYear));
        query.addConditions(CONTEST.CONTEST_PRIVATE.eq(false));

        query.addConditions(CONTEST_TEAM_MEMBER.ROLE_ID.eq(categoryId));

        return query.fetchInto(ContestTeamMember.class);
    }

    @Override
    public boolean delete(Long id_) {
        return dslContext.deleteFrom(CONTEST_TEAM_MEMBER)
                .where(CONTEST_TEAM_MEMBER.ID_.eq(id_))
                .execute() > 0;
    }
}
