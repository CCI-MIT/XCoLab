package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.Group_;

import org.xcolab.service.proposal.domain.group.GroupDao;
import org.xcolab.service.contest.exceptions.NotFoundException;

@RestController
public class GroupController {

    private final GroupDao groupDao;

    @Autowired
    public GroupController(GroupDao groupDao){
        this.groupDao = groupDao;
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public Group_ createGroup_(@RequestBody Group_ group) {
        return this.groupDao.create(group);
    }

    @RequestMapping(value = "/groups/{groupId}", method = RequestMethod.PUT)
    public boolean updateGroup_(@RequestBody Group_ group,
            @PathVariable("groupId") Long groupId) throws NotFoundException {

        if (groupId == null || groupId == 0) {
            throw new NotFoundException("No Group_ with id " + groupId);
        } else {
            return groupDao.update(group);
        }
    }
}
