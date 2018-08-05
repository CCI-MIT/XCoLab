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

    public UsersGroups addMemberToGroup(long userId, long groupId) {
        UsersGroups ug = new UsersGroups();
        ug.setUserId(userId);
        ug.setGroupId(groupId);
        return usersGroupsResource.create(ug).execute();
    }

    public void removeMemberFromGroup(long userId, long groupId) {
        usersGroupsResource.collectionService("deleteUsersGroups", Boolean.class)
                .queryParam("userId", userId)
                .queryParam("groupId", groupId)
                .delete();
    }

    public boolean deleteGroups(List<Long> groupIds) {
        return usersGroupsResource.collectionService("batchDelete", Boolean.class)
                .post(groupIds);
    }

    public List<UsersGroups> getUserGroupsByGroupId(long groupId) {
        return usersGroupsResource.list()
                .queryParam("groupId", groupId)
                .execute();
    }

    public List<UsersGroups> getUserGroupsByuserId(long userId) {
        return usersGroupsResource.list()
                .queryParam("userId", userId)
                .execute();
    }

    public boolean isMemberInGroup(long userId, long groupId) {
        List<UsersGroups> list = usersGroupsResource.list()
                .queryParam("userId", userId)
                .queryParam("groupId", groupId)
                .execute();
        return list != null && !list.isEmpty();
    }

}
