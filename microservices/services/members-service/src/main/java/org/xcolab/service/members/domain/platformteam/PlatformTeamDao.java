package org.xcolab.service.members.domain.platformteam;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.pojos.PlatformTeam;

import java.util.List;
import java.util.Optional;

public interface PlatformTeamDao {
    List<PlatformTeam> getPlatformTeams();
    Optional<PlatformTeam> getPlatformTeam(long teamId);
    void createPlatformTeam(String name);
    List<PlatformTeam> getUserTeams(long userId);
    List<Member> getTeamMembers(long teamId);
    int delete(long teamId);
    int addMember(long teamId, long memberId);
    int removeMember(long teamId, long memberId);
}
