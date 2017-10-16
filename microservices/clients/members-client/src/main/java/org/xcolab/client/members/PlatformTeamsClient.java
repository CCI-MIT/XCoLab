package org.xcolab.client.members;

import org.xcolab.client.members.pojo.PlatformTeam;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public class PlatformTeamsClient {

    private static final RestService platformTeamService =
            new RestService(CoLabService.MEMBER, ServiceRequestUtils.getNamespace());

    private static final RestResource<PlatformTeam, Long> platformTeamResource =
            new RestResource1<>(platformTeamService, "platformteams", PlatformTeam.TYPES);

    private PlatformTeamsClient() {
    }

    public static List<PlatformTeam> listAllPlatformTeams() {
        return platformTeamResource.list()
                .addRange(0, Integer.MAX_VALUE)
//                .withCache(CacheName.MEMBER_LIST)
                .execute();
    }
}
