package org.xcolab.service.members.domain.platformteam;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.pojos.PlatformTeam;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.MEMBER;
import static org.xcolab.model.Tables.PLATFORM_TEAM;
import static org.xcolab.model.Tables.PLATFORM_TEAM_MEMBER;

@Repository
public class PlatformTeamDaoImpl implements PlatformTeamDao {

    private final DSLContext dslContext;

    @Autowired
    public PlatformTeamDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<PlatformTeam> getPlatformTeams() {
        final SelectQuery<Record> query = dslContext.select(PLATFORM_TEAM.fields())
                .from(PLATFORM_TEAM)
                .getQuery();
        return query.fetchInto(PlatformTeam.class);
    }

    @Override
    public Optional<PlatformTeam> getPlatformTeam(long teamId) {
        final Record memberRecord = dslContext.select()
                .from(PLATFORM_TEAM)
                .where(PLATFORM_TEAM.ID_.eq(teamId))
                .fetchOne();
        if (memberRecord == null) {
            return Optional.empty();
        }
        return Optional.of(memberRecord.into(PlatformTeam.class));
    }

    @Override
    public void createPlatformTeam(String name) {
        this.dslContext.insertInto(PLATFORM_TEAM)
                .set(PLATFORM_TEAM.NAME, name)
                .execute();
    }

    @Override
    public List<PlatformTeam> getUserTeams(long userId) {
        return dslContext.select(PLATFORM_TEAM.fields())
                .from(PLATFORM_TEAM.join(PLATFORM_TEAM_MEMBER)
                        .on(PLATFORM_TEAM.ID_.eq(PLATFORM_TEAM_MEMBER.TEAM_ID)))
                .where(PLATFORM_TEAM_MEMBER.USER_ID.eq(userId))
                .fetchInto(PlatformTeam.class);
    }

    @Override
    public List<Member> getTeamMembers(long teamId) {
        return dslContext.select(MEMBER.fields())
                .from(MEMBER.join(PLATFORM_TEAM_MEMBER)
                        .on(MEMBER.ID_.eq(PLATFORM_TEAM_MEMBER.USER_ID)))
                .where(PLATFORM_TEAM_MEMBER.TEAM_ID.eq(teamId))
                .fetchInto(Member.class);
    }
}
