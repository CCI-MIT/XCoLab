package org.xcolab.service.contest.domain.contestteammember;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ContestTeamMember;
import org.xcolab.model.tables.records.ContestTeamMemberRecord;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.CONTEST_TEAM_MEMBER;

@Repository
public class ContestTeamMemberDaoImpl implements ContestTeamMemberDao{
    @Autowired
    private DSLContext dslContext;

    public ContestTeamMember create(ContestTeamMember contestTeamMember) {

        ContestTeamMemberRecord ret = this.dslContext.insertInto(CONTEST_TEAM_MEMBER)
                .set(CONTEST_TEAM_MEMBER.CONTEST_ID, contestTeamMember.getContestId())
                .set(CONTEST_TEAM_MEMBER.USER_ID, contestTeamMember.getUserId())
                .set(CONTEST_TEAM_MEMBER.ROLE_ID, contestTeamMember.getRoleId())
                .returning(CONTEST_TEAM_MEMBER.ID_)
                .fetchOne();
        if (ret != null) {
            contestTeamMember.setId_(ret.getValue(CONTEST_TEAM_MEMBER.ID_));
            return contestTeamMember;
        } else {
            return null;
        }

    }

    public ContestTeamMember get(Long id_) throws NotFoundException{

        final Record record =  this.dslContext.selectFrom(CONTEST_TEAM_MEMBER)
                .where(CONTEST_TEAM_MEMBER.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ContestTeamMember with id " + id_ + " does not exist");
        }
        return record.into(ContestTeamMember.class);

    }

    public boolean update(ContestTeamMember contestTeamMember) {
        return dslContext.update(CONTEST_TEAM_MEMBER)
                .set(CONTEST_TEAM_MEMBER.CONTEST_ID, contestTeamMember.getContestId())
                .set(CONTEST_TEAM_MEMBER.USER_ID, contestTeamMember.getUserId())
                .set(CONTEST_TEAM_MEMBER.ROLE_ID, contestTeamMember.getRoleId())
                .where(CONTEST_TEAM_MEMBER.ID_.eq(contestTeamMember.getId_()))
                .execute() > 0;
    }

    @Override
    public List<ContestTeamMember> findByGiven(Long contestId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(CONTEST_TEAM_MEMBER).getQuery();

        if (contestId != null) {
            query.addConditions(CONTEST_TEAM_MEMBER.CONTEST_ID.eq(contestId));
        }
        return query.fetchInto(ContestTeamMember.class);
    }

    @Override
    public int delete(Long id_) {
        return dslContext.deleteFrom(CONTEST_TEAM_MEMBER)
                .where(CONTEST_TEAM_MEMBER.ID_.eq(id_))
                .execute();
    }
}
