package org.xcolab.service.members.domain.platformteam;

import org.xcolab.client.user.pojo.PlatformTeam;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.util.List;
import java.util.Optional;

public interface PlatformTeamDao {

    List<PlatformTeam> getPlatformTeams();

    Optional<PlatformTeam> getPlatformTeam(long teamId);

    PlatformTeam updateOrInsertPlatformTeam(PlatformTeam team);

    PlatformTeam createPlatformTeam(String name);

    List<PlatformTeam> getUserTeams(long userId);

    List<UserWrapper> getTeamUsers(long teamId);

    int delete(long teamId);

    int addUser(long teamId, long userId);

    int removeUser(long teamId, long userId);
}
