package org.xcolab.client.members;

import org.xcolab.client.members.pojo.StaffMember;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public class StaffMemberClient {
    private static final RestService memberService = new RestService(CoLabService.MEMBER);

    private static final RestResource<StaffMember, Long> staffMemberResource = new RestResource1<>(memberService,
            "staffMembers", StaffMember.TYPES);

    public static List<StaffMember> getStaffMembersByCategoryId(long categoryId) {
        return staffMemberResource.list()
                .queryParam("categoryId", categoryId)
                .queryParam("limitRecord", Integer.MAX_VALUE) // since not all members are retrieved by default (20)
                .withCache(CacheKeys.withClass(StaffMember.class)
                        .withParameter("categoryId", categoryId).asList(), CacheRetention.LONG)
                .execute();
    }
}
