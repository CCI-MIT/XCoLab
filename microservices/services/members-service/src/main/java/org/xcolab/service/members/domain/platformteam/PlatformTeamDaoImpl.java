package org.xcolab.service.members.domain.platformteam;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.PlatformTeam;
import org.xcolab.model.tables.pojos.User;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.PLATFORM_TEAM;
import static org.xcolab.model.Tables.PLATFORM_TEAM_MEMBER;
import static org.xcolab.model.Tables.USER;

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
                .where(PLATFORM_TEAM.ID.eq(teamId))
                .fetchOne();
        if (memberRecord == null) {
            return Optional.empty();
        }
        return Optional.of(memberRecord.into(PlatformTeam.class));
    }

    @Override
    public PlatformTeam updateOrInsertPlatformTeam(PlatformTeam team) {
        this.dslContext.insertInto(PLATFORM_TEAM, PLATFORM_TEAM.ID, PLATFORM_TEAM.NAME)
                .values(team.getId(), team.getName())
                .onDuplicateKeyUpdate()
                .set(PLATFORM_TEAM.NAME, team.getName())
                .execute();
        return getPlatformTeam(team.getId()).orElse(null);
    }

    @Override
    public PlatformTeam createPlatformTeam(String name) {
        Long teamId = this.dslContext.insertInto(PLATFORM_TEAM)
                .set(PLATFORM_TEAM.NAME, name)
                .returning(PLATFORM_TEAM.ID, PLATFORM_TEAM.NAME)
                .fetchOne()
                .getId();

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
                        .on(PLATFORM_TEAM.ID.eq(PLATFORM_TEAM_MEMBER.TEAM_ID)))
                .where(PLATFORM_TEAM_MEMBER.USER_ID.eq(userId))
                .fetchInto(PlatformTeam.class);
    }

    @Override
    public List<User> getTeamUsers(long teamId) {
        return dslContext.select(USER.fields())
                .from(USER.join(PLATFORM_TEAM_MEMBER)
                        .on(USER.ID.eq(PLATFORM_TEAM_MEMBER.USER_ID)))
                .where(PLATFORM_TEAM_MEMBER.TEAM_ID.eq(teamId))
                .fetchInto(User.class);
    }

    @Override
    public int delete(long teamId) {
        return this.dslContext.deleteFrom(PLATFORM_TEAM)
                .where(PLATFORM_TEAM.ID.eq(teamId))
                .execute();
    }

    @Override
    public int addUser(long teamId, long userId) {
        return dslContext.insertInto(PLATFORM_TEAM_MEMBER)
                .set(PLATFORM_TEAM_MEMBER.TEAM_ID, teamId)
                .set(PLATFORM_TEAM_MEMBER.USER_ID, userId)
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Override
    public int removeUser(long teamId, long userId) {
        return dslContext.deleteFrom(PLATFORM_TEAM_MEMBER)
                .where(PLATFORM_TEAM_MEMBER.TEAM_ID.eq(teamId))
                .and(PLATFORM_TEAM_MEMBER.USER_ID.eq(userId))
                .execute();
    }
}
