package org.xcolab.client.members;

import org.xcolab.client.members.pojo.UsersGroups;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public class UsersGroupsClientUtil {

    private static final RestService membersService = new RestService(CoLabService.MEMBER,
            ServiceRequestUtils.getNamespace());

    private static final UsersGroupsClient client = UsersGroupsClient.fromService(membersService);


    public static UsersGroups createUsersGroups(Long userId, Long groupId) {
        return client.addMemberToGroup(userId, groupId);
    }

    public static void deleteUsersGroups(Long userId, Long groupId) {
        client.removeMemberFromGroup(userId, groupId);
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
