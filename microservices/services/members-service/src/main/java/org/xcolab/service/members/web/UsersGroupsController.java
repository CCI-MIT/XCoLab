package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xcolab.model.tables.pojos.Users_Groups;
import org.xcolab.service.members.domain.usergroup.UsersGroupsDao;

import java.util.List;

@RestController
public class UsersGroupsController {

    @Autowired
    UsersGroupsDao usersGroupsDao;

    @RequestMapping(value = "/usersGroups", method = RequestMethod.POST)
    public Users_Groups createUsersGroups(@RequestBody Users_Groups usersGroups) {
        return this.usersGroupsDao.create(usersGroups);
    }

    @RequestMapping(value = "/usersGroups", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Users_Groups> getUsersGroups(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long groupId
    ) {
        return usersGroupsDao.findByGiven(userId, groupId);
    }

    @RequestMapping(value = "/usersGroups/deleteUsersGroups", method = {RequestMethod.DELETE})
    public Boolean deleteUsersGroups(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long groupId
    ) {
        return usersGroupsDao.delete(userId, groupId)> 0;
    }



}
