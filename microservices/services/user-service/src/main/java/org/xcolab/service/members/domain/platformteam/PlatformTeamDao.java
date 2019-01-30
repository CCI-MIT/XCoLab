package org.xcolab.service.members.domain.platformteam;

import org.xcolab.client.user.pojo.wrapper.PlatformTeamWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.util.List;
import java.util.Optional;

public interface PlatformTeamDao {

    List<PlatformTeamWrapper> getPlatformTeams();

    Optional<PlatformTeamWrapper> getPlatformTeam(long teamId);

    PlatformTeamWrapper updateOrInsertPlatformTeam(PlatformTeamWrapper team);

    PlatformTeamWrapper createPlatformTeam(String name);

    List<PlatformTeamWrapper> getUserTeams(long userId);

    List<UserWrapper> getTeamUsers(long teamId);

    int delete(long teamId);

    int addUser(long teamId, long userId);

    int removeUser(long teamId, long userId);
}
