package org.xcolab.client.members;

import org.xcolab.client.members.pojo.UsersGroups;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.List;

public class UsersGroupsClientUtil {

    private static final UsersGroupsClient client = UsersGroupsClient.fromNamespace(
            ServiceNamespace.instance());


    public static UsersGroups createUsersGroups(Long userId, Long groupId) {
        return client.addMemberToGroup(userId, groupId);
    }

    public static void deleteUsersGroups(Long userId, Long groupId) {
        client.removeMemberFromGroup(userId, groupId);
    }

    public static boolean deleteGroups(List<Long> groupIds) {
        return client.deleteGroups(groupIds);
    }

    public static List<UsersGroups> getUserGroupsByuserId(long userId) {
        return client.getUserGroupsByuserId(userId);
    }

    public static UsersGroups addMemberToGroup(long userId, long groupId) {
        return client.addMemberToGroup(userId, groupId);
    }

    public static void removeMemberFromGroup(long userId, long groupId) {
        client.removeMemberFromGroup(userId, groupId);
    }

    public static List<UsersGroups> getUserGroupsByGroupId(long groupId) {
        return client.getUserGroupsByGroupId(groupId);
    }

    public static boolean isMemberInGroup(long userId, long groupId) {
        return client.isMemberInGroup(userId, groupId);
    }

    public static boolean isUserInGroups(Long userId, Long groupId) {
        return client.isMemberInGroup(userId, groupId);
    }

    public static UsersGroupsClient getClient() {
        return client;
    }
}
