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
        return dslContext.select(PLATFORM_TEAM.fields())
                .from(PLATFORM_TEAM)
                .fetchInto(PlatformTeam.class);
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
    public PlatformTeam updateOrInsertPlatformTeam(PlatformTeam team) {
        this.dslContext.insertInto(PLATFORM_TEAM, PLATFORM_TEAM.ID_, PLATFORM_TEAM.NAME)
                .values(team.getId_(), team.getName())
                .onDuplicateKeyUpdate()
                .set(PLATFORM_TEAM.NAME, team.getName())
                .execute();
        return getPlatformTeam(team.getId_()).orElse(null);
    }

    @Override
    public PlatformTeam createPlatformTeam(String name) {
        Long teamId = this.dslContext.insertInto(PLATFORM_TEAM)
                .set(PLATFORM_TEAM.NAME, name)
                .returning(PLATFORM_TEAM.ID_, PLATFORM_TEAM.NAME)
                .fetchOne()
                .getId_();

        if (teamId != null) {
            return new PlatformTeam(teamId, name);
        } else {
            return null;
        }
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

    @Override
    public int delete(long teamId) {
        return this.dslContext.deleteFrom(PLATFORM_TEAM)
                .where(PLATFORM_TEAM.ID_.eq(teamId))
                .execute();
    }

    @Override
    public int addMember(long teamId, long memberId) {
        return dslContext.insertInto(PLATFORM_TEAM_MEMBER)
                .set(PLATFORM_TEAM_MEMBER.TEAM_ID, teamId)
                .set(PLATFORM_TEAM_MEMBER.USER_ID, memberId)
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Override
    public int removeMember(long teamId, long memberId) {
        return dslContext.deleteFrom(PLATFORM_TEAM_MEMBER)
                .where(PLATFORM_TEAM_MEMBER.TEAM_ID.eq(teamId))
                .and(PLATFORM_TEAM_MEMBER.USER_ID.eq(memberId))
                .execute();
    }
}
