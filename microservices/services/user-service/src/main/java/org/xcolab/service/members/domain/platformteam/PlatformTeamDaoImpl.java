package org.xcolab.service.members.domain.platformteam;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.user.pojo.wrapper.PlatformTeamWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

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
    public List<PlatformTeamWrapper> getPlatformTeams() {
        return dslContext.select(PLATFORM_TEAM.fields())
                .from(PLATFORM_TEAM)
                .fetchInto(PlatformTeamWrapper.class);
    }

    @Override
    public Optional<PlatformTeamWrapper> getPlatformTeam(long teamId) {
        final Record memberRecord = dslContext.select()
                .from(PLATFORM_TEAM)
                .where(PLATFORM_TEAM.ID.eq(teamId))
                .fetchOne();
        if (memberRecord == null) {
            return Optional.empty();
        }
        return Optional.of(memberRecord.into(PlatformTeamWrapper.class));
    }

    @Override
    public PlatformTeamWrapper updateOrInsertPlatformTeam(PlatformTeamWrapper team) {
        this.dslContext.insertInto(PLATFORM_TEAM, PLATFORM_TEAM.ID, PLATFORM_TEAM.NAME)
                .values(team.getId(), team.getName())
                .onDuplicateKeyUpdate()
                .set(PLATFORM_TEAM.NAME, team.getName())
                .execute();
        return getPlatformTeam(team.getId()).orElse(null);
    }

    @Override
    public PlatformTeamWrapper createPlatformTeam(String name) {
        Long teamId = this.dslContext.insertInto(PLATFORM_TEAM)
                .set(PLATFORM_TEAM.NAME, name)
                .returning(PLATFORM_TEAM.ID, PLATFORM_TEAM.NAME)
                .fetchOne()
                .getId();

        if (teamId != null) {
            return new PlatformTeamWrapper(teamId, name);
        } else {
            return null;
        }
    }

    @Override
    public List<PlatformTeamWrapper> getUserTeams(long userId) {
        return dslContext.select(PLATFORM_TEAM.fields())
                .from(PLATFORM_TEAM.join(PLATFORM_TEAM_MEMBER)
                        .on(PLATFORM_TEAM.ID.eq(PLATFORM_TEAM_MEMBER.TEAM_ID)))
                .where(PLATFORM_TEAM_MEMBER.USER_ID.eq(userId))
                .fetchInto(PlatformTeamWrapper.class);
    }

    @Override
    public List<UserWrapper> getTeamUsers(long teamId) {
        return dslContext.select(USER.fields())
                .from(USER.join(PLATFORM_TEAM_MEMBER)
                        .on(USER.ID.eq(PLATFORM_TEAM_MEMBER.USER_ID)))
                .where(PLATFORM_TEAM_MEMBER.TEAM_ID.eq(teamId))
                .fetchInto(UserWrapper.class);
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
