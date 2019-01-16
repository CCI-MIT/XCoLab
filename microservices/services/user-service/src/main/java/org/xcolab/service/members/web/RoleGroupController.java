package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.RoleGroup;
import org.xcolab.model.tables.pojos.Role;
import org.xcolab.service.members.domain.rolegroup.RoleGroupDao;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.members.service.rolegroup.RoleGroupService;

import java.util.List;

@RestController
public class RoleGroupController {

    @Autowired
    private RoleGroupDao roleGroupDao;

    @Autowired
    private RoleGroupService roleGroupService;


    @RequestMapping(value = "/roleGroups", method = RequestMethod.POST)
    public RoleGroup create(@RequestBody RoleGroup roleGroup) {
        return roleGroupDao.create(roleGroup);
    }

    @RequestMapping(value = "/roleGroups/{roleGroupId}/roles", method = RequestMethod.GET)
    public List<Role> getRolesInGroup(@PathVariable("roleGroupId") Long roleGroupId)
            throws NotFoundException {
        if (roleGroupId != null) {
            return roleGroupService.getAllRolesInGroup(roleGroupId);
        } else {
            throw new NotFoundException("No message id given");
        }
    }

    @RequestMapping(value = "/roleGroups/{roleGroupId}/roles", method = RequestMethod.POST)
    public String addRoleToRoleGroup(@PathVariable Long roleGroupId, @RequestParam Long roleId) {
        roleGroupService.addRoleToGroup(roleGroupId, roleId);
        return "Role added to role group successfully";
    }

    @RequestMapping(value = "/roleGroups/{roleGroupId}/roles/{roleId}", method = RequestMethod.DELETE)
    public String deleteRoleFromRoleGroup(@PathVariable("roleGroupId") Long roleGroupId,
                                          @PathVariable("roleId") Long roleId) throws NotFoundException {
        roleGroupService.removeRoleFromGroup(roleGroupId, roleId);
        return "Role deleted from role group successfully";
    }

}
