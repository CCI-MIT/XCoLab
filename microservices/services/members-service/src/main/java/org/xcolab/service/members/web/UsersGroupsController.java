package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xcolab.model.tables.pojos.UsersGroups;
import org.xcolab.service.members.domain.usergroup.UsersGroupsDao;

import java.util.List;

@RestController
public class UsersGroupsController {

    @Autowired
    UsersGroupsDao usersGroupsDao;

    @RequestMapping(value = "/usersGroups", method = RequestMethod.POST)
    public UsersGroups createUsersGroups(@RequestBody UsersGroups usersGroups) {
        return this.usersGroupsDao.create(usersGroups);
    }

    @RequestMapping(value = "/usersGroups", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<UsersGroups> getUsersGroupss(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long groupId
    ) {
        return usersGroupsDao.findByGiven(userId, groupId);
    }

}
