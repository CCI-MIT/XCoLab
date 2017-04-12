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
        return client.createUsersGroups(userId, groupId);
    }

    public static UsersGroups createUsersGroups(UsersGroups usersGroups) {
        return client.createUsersGroups(usersGroups);
    }

    public static void deleteUsersGroups(Long userId, Long groupId) {
        client.deleteUsersGroups(userId, groupId);
    }


    public static List<UsersGroups> getUserGroupsByUserIdGroupId(Long userId, Long groupId) {
        return client.getUserGroupsByUserIdGroupId(userId, groupId);
    }

    public static boolean isUserInGroups(Long userId, Long groupId) {
        return client.isUserInGroups(userId, groupId);
    }

    public static UsersGroupsClient getClient() {
        return client;
    }
}
