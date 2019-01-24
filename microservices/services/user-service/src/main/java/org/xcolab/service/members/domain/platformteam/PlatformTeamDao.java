package org.xcolab.service.members.domain.platformteam;

import org.xcolab.client.user.pojo.IPlatformTeam;
import org.xcolab.client.user.pojo.IUser;

import java.util.List;
import java.util.Optional;

public interface PlatformTeamDao {

    List<IPlatformTeam> getPlatformTeams();

    Optional<IPlatformTeam> getPlatformTeam(long teamId);

    IPlatformTeam updateOrInsertPlatformTeam(IPlatformTeam team);

    IPlatformTeam createPlatformTeam(String name);

    List<IPlatformTeam> getUserTeams(long userId);

    List<IUser> getTeamUsers(long teamId);

    int delete(long teamId);

    int addUser(long teamId, long userId);

    int removeUser(long teamId, long userId);
}
