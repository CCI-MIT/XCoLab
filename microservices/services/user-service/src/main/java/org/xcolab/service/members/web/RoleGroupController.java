package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.user.IPermissionClient;
import org.xcolab.client.user.exceptions.RoleGroupNotFoundException;
import org.xcolab.client.user.pojo.IRole;
import org.xcolab.client.user.pojo.IRoleGroup;
import org.xcolab.client.user.pojo.wrapper.RoleWrapper;
import org.xcolab.service.members.domain.rolegroup.RoleGroupDao;
import org.xcolab.service.members.service.rolegroup.RoleGroupService;

import java.util.List;

@RestController
public class RoleGroupController implements IPermissionClient {

    @Autowired
    private RoleGroupDao roleGroupDao;

    @Autowired
    private RoleGroupService roleGroupService;


    @RequestMapping(value = "/roleGroups", method = RequestMethod.POST)
    public IRoleGroup create(@RequestBody IRoleGroup roleGroup) {
        return roleGroupDao.create(roleGroup);
    }

    @RequestMapping(value = "/roleGroups/{roleGroupId}/roles", method = RequestMethod.GET)
    public List<RoleWrapper> getRolesInGroup(@PathVariable("roleGroupId") Long roleGroupId)
            throws RoleGroupNotFoundException {
        if (roleGroupId != null) {
            return roleGroupService.getAllRolesInGroup(roleGroupId);
        } else {
            throw new RoleGroupNotFoundException("No Role group found for: " + roleGroupId);
        }
    }

    @RequestMapping(value = "/roleGroups/{roleGroupId}/roles", method = RequestMethod.POST)
    public String addRoleToRoleGroup(@PathVariable Long roleGroupId, @RequestParam Long roleId) {
        roleGroupService.addRoleToGroup(roleGroupId, roleId);
        return "Role added to role group successfully";
    }

    @RequestMapping(value = "/roleGroups/{roleGroupId}/roles/{roleId}",
            method = RequestMethod.DELETE)
    public String deleteRoleFromRoleGroup(@PathVariable("roleGroupId") Long roleGroupId,
            @PathVariable("roleId") Long roleId) throws RoleGroupNotFoundException {
        roleGroupService.removeRoleFromGroup(roleGroupId, roleId);
        return "Role deleted from role group successfully";
    }

}
