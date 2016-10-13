package org.xcolab.client.members;

import org.xcolab.client.members.pojo.UsersGroups;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public class UsersGroupsClient {

    private static final RestService usersGroupsService = new RestService("members-service");

    private static final RestResource1<UsersGroups,Long> usersGroupsResource = new RestResource1<>(usersGroupsService,
            "usersGroups", UsersGroups.TYPES);

    public static UsersGroups createUsersGroups(Long userId, Long groupId) {
        UsersGroups ug = new UsersGroups();
        ug.setUserId(userId);
        ug.setGroupId(groupId);
        return usersGroupsResource.create(ug).execute();
    }

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

    public static boolean isUserInGroups(Long userId, Long groupId) {
        List<UsersGroups> list =  usersGroupsResource.list()
                .optionalQueryParam("userId", userId)
                .optionalQueryParam("groupId", groupId)
                .execute();
        if(list != null && list.size() >0){
            return true;
        }else{
            return false;
        }
    }

}
