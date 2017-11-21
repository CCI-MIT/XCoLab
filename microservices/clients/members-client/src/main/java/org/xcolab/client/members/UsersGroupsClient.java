package org.xcolab.client.members;

import org.xcolab.client.members.pojo.UsersGroups;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersGroupsClient {

    private static final Map<ServiceNamespace, UsersGroupsClient> instances = new HashMap<>();

    private final RestResource1<UsersGroups, Long> usersGroupsResource;

    private UsersGroupsClient(ServiceNamespace serviceNamespace) {
        usersGroupsResource = new RestResource1<>(UserResource.USER_GROUP, UsersGroups.TYPES,
                serviceNamespace);
    }

    public static UsersGroupsClient fromNamespace(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, UsersGroupsClient::new);
    }

    public UsersGroups addMemberToGroup(long memberId, long groupId) {
        UsersGroups ug = new UsersGroups();
        ug.setUserId(memberId);
        ug.setGroupId(groupId);
        return usersGroupsResource.create(ug).execute();
    }

    public void removeMemberFromGroup(long memberId, long groupId) {
        usersGroupsResource.service("deleteUsersGroups", Boolean.class)
                .queryParam("userId", memberId)
                .queryParam("groupId", groupId)
                .delete();
    }

    public List<UsersGroups> getUserGroupsByGroupId(long groupId) {
        return usersGroupsResource.list()
                .queryParam("groupId", groupId)
                .execute();
    }

    public List<UsersGroups> getUserGroupsByMemberId(long memberId) {
        return usersGroupsResource.list()
                .queryParam("userId", memberId)
                .execute();
    }

    public boolean isMemberInGroup(long memberId, long groupId) {
        List<UsersGroups> list = usersGroupsResource.list()
                .queryParam("userId", memberId)
                .queryParam("groupId", groupId)
                .execute();
        return list != null && !list.isEmpty();
    }

}
