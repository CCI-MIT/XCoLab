package org.xcolab.client.members;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.PlatformTeam;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public class PlatformTeamsClient {

    private static final RestResource<PlatformTeam, Long> platformTeamResource =
            new RestResource1<>(UserResource.PLATFORM_TEAM, PlatformTeam.TYPES);

    private PlatformTeamsClient() {
    }

    public static List<PlatformTeam> listAllPlatformTeams() {
        return platformTeamResource.list().addRange(0, Integer.MAX_VALUE)
                .withCache(CacheName.PLATFORM_TEAM).execute();
    }

    public static PlatformTeam getPlatformTeam(long teamId) throws EntityNotFoundException {
        return platformTeamResource.get(teamId)
                .withCache(CacheName.PLATFORM_TEAM)
                .executeChecked();
    }

    public static PlatformTeam createPlatformTeam(String name) {
        PlatformTeam team = new PlatformTeam();
        team.setName(name);
        team = platformTeamResource.create(team).execute();
        ServiceRequestUtils.clearCache(CacheName.PLATFORM_TEAM);
        return team;
    }

    public static boolean deletePlatformTeam(PlatformTeam team) {
        boolean result = platformTeamResource.delete(team.getId_())
                .execute();
        ServiceRequestUtils.clearCache(CacheName.PLATFORM_TEAM);
        return result;
    }

    public static boolean updatePlatformTeam(PlatformTeam team) {
        ServiceRequestUtils.clearCache(CacheName.PLATFORM_TEAM);
        return platformTeamResource.update(team, team.getId_())
                .cacheName(CacheName.PLATFORM_TEAM)
                .execute();
    }

    public static List<Member> getTeamMembers(PlatformTeam team) {
        return platformTeamResource
                .service(team.getId_(), "members", Member.TYPES.getTypeReference())
                .getList();
    }

    public static List<PlatformTeam> getTeams(Member member) {
        return platformTeamResource.list()
                .queryParam("userId", member.getId_())
                .withCache(CacheName.PLATFORM_TEAM)
                .execute();
    }

    public static boolean addMember(PlatformTeam team, Member member) {
        ServiceRequestUtils.clearCache(CacheName.PLATFORM_TEAM);
        return platformTeamResource
                .service(team.getId_(), "members/" + member.getId_(), Boolean.class)
                .put();
    }

    public static boolean removeMember(PlatformTeam team, Member member) {
        ServiceRequestUtils.clearCache(CacheName.PLATFORM_TEAM);
        return platformTeamResource
                .service(team.getId_(), "members/" + member.getId_(), Boolean.class)
                .delete();
    }


}
