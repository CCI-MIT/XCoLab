package org.xcolab.client.members;

import org.xcolab.client.members.pojo.UsersGroups;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public class UsersGroupsClient {

    private static final RestService usersGroupsService = new RestService("members-service");

    private static final RestResource<UsersGroups> usersGroupsResource = new RestResource<>(usersGroupsService,
            "usersGroups", UsersGroups.TYPES);

    public static UsersGroups createUsersGroups(UsersGroups usersGroups) {
        return usersGroupsResource.create(usersGroups).execute();
    }

    public static void deleteUsersGroups(Long userId, Long groupId) {
         usersGroupsResource.service("deleteUsersGroups",Boolean.class)
                .queryParam("userId", userId)
                .queryParam("groupId", groupId)
                .delete();
    }


    public static List<UsersGroups> getUserGroupsByUserIdGroupId(Long userId, Long groupId) {
        return usersGroupsResource.list()
                .optionalQueryParam("userId", userId)
                .optionalQueryParam("groupId", groupId)
                .execute();
    }

}
