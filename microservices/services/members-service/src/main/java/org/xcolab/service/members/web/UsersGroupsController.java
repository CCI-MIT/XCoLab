package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.Users_Groups;
import org.xcolab.service.members.domain.usergroup.UsersGroupsDao;
import org.xcolab.service.members.exceptions.ConflictException;

import java.util.List;

@RestController
public class UsersGroupsController {

    private final UsersGroupsDao usersGroupsDao;

    @Autowired
    public UsersGroupsController(UsersGroupsDao usersGroupsDao) {
        Assert.notNull(usersGroupsDao, "UsersGroupsDao bean is required");
        this.usersGroupsDao = usersGroupsDao;
    }

    @RequestMapping(value = "/usersGroups", method = RequestMethod.POST)
    public Users_Groups createUsersGroups(@RequestBody Users_Groups usersGroups)
            throws ConflictException {
        if (usersGroupsDao.exists(usersGroups.getUserId(), usersGroups.getGroupId())) {
            throw new ConflictException();
        }
        return this.usersGroupsDao.create(usersGroups);
    }

    @RequestMapping(value = "/usersGroups", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Users_Groups> getUsersGroups(@RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long groupId) {
        return usersGroupsDao.findByGiven(userId, groupId);
    }

    @RequestMapping(value = "/usersGroups/deleteUsersGroups", method = {RequestMethod.DELETE})
    public Boolean deleteUsersGroups(@RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long groupId) {
        return usersGroupsDao.delete(userId, groupId)> 0;
    }



}
