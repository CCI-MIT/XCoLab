package org.xcolab.client.members;

import org.xcolab.client.members.pojo.StaffMember;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public class StaffMemberClient {
    private static final RestService memberService = new RestService("members-service");

    private static final RestResource<StaffMember, Long> staffMemberResource = new RestResource1<>(memberService,
            "staffMembers", StaffMember.TYPES);

    public static List<StaffMember> getStaffMembersByCategoryId(long categoryId) {
        return staffMemberResource.list()
                .queryParam("categoryId", categoryId)
                .execute();
    }
}
