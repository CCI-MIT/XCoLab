package org.xcolab.client.members;

import org.xcolab.client.members.pojo.UsersGroups;
import org.xcolab.commons.http.client.enums.ServiceNamespace;

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

    public static List<UsersGroups> getUserGroupsByMemberId(long memberId) {
        return client.getUserGroupsByMemberId(memberId);
    }

    public static UsersGroups addMemberToGroup(long memberId, long groupId) {
        return client.addMemberToGroup(memberId, groupId);
    }

    public static void removeMemberFromGroup(long memberId, long groupId) {
        client.removeMemberFromGroup(memberId, groupId);
    }

    public static List<UsersGroups> getUserGroupsByGroupId(long groupId) {
        return client.getUserGroupsByGroupId(groupId);
    }

    public static boolean isMemberInGroup(long memberId, long groupId) {
        return client.isMemberInGroup(memberId, groupId);
    }

    public static boolean isUserInGroups(Long userId, Long groupId) {
        return client.isMemberInGroup(userId, groupId);
    }

    public static UsersGroupsClient getClient() {
        return client;
    }
}
