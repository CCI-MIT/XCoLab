package org.xcolab.client.members;

import org.xcolab.client.members.pojo.UsersGroups;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public class UsersGroupsClientUtil {

    private static final RestService membersService = new RestService("members-service");

    private static final UsersGroupsClient usersGroupsClient = UsersGroupsClient.fromService(membersService);


    public static UsersGroups createUsersGroups(Long userId, Long groupId) {
        return usersGroupsClient.createUsersGroups(userId,groupId);
    }

    public static UsersGroups createUsersGroups(UsersGroups usersGroups) {
        return usersGroupsClient.createUsersGroups(usersGroups);
    }

    public static void deleteUsersGroups(Long userId, Long groupId) {
         usersGroupsClient.deleteUsersGroups(userId,groupId);
    }


    public static List<UsersGroups> getUserGroupsByUserIdGroupId(Long userId, Long groupId) {
        return usersGroupsClient.getUserGroupsByUserIdGroupId(userId,groupId);
    }

    public static boolean isUserInGroups(Long userId, Long groupId) {
        return usersGroupsClient.isUserInGroups(userId,groupId);
    }
}
