package org.xcolab.client.members;

import org.xcolab.client.members.pojo.UsersGroups;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersGroupsClient {



    private final RestService membersService;

    private  final RestResource1<UsersGroups,Long> usersGroupsResource;

    private static final Map<RestService, UsersGroupsClient> instances = new HashMap<>();

    public UsersGroupsClient(RestService membersService){
        this.membersService = membersService;
        usersGroupsResource = new RestResource1<>(membersService,
                "usersGroups", UsersGroups.TYPES);

    }

    public  UsersGroups createUsersGroups(Long userId, Long groupId) {
        UsersGroups ug = new UsersGroups();
        ug.setUserId(userId);
        ug.setGroupId(groupId);
        return usersGroupsResource.create(ug).execute();
    }

    public  UsersGroups createUsersGroups(UsersGroups usersGroups) {
        return usersGroupsResource.create(usersGroups).execute();
    }

    public  void deleteUsersGroups(Long userId, Long groupId) {
         usersGroupsResource.service("deleteUsersGroups",Boolean.class)
                .queryParam("userId", userId)
                .queryParam("groupId", groupId)
                .delete();
    }


    public  List<UsersGroups> getUserGroupsByUserIdGroupId(Long userId, Long groupId) {
        return usersGroupsResource.list()
                .optionalQueryParam("userId", userId)
                .optionalQueryParam("groupId", groupId)
                .execute();
    }

    public  boolean isUserInGroups(Long userId, Long groupId) {
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

    public static UsersGroupsClient fromService(RestService contestService) {
        UsersGroupsClient client = instances.get(contestService);
        if (client == null) {
            client = new UsersGroupsClient(contestService);
            instances.put(contestService, client);
        }
        return client;
    }

}
