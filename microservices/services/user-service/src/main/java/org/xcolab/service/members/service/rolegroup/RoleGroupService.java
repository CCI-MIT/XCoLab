package org.xcolab.service.members.service.rolegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.user.exceptions.RoleGroupNotFoundException;
import org.xcolab.client.user.pojo.IRole;
import org.xcolab.client.user.pojo.wrapper.RoleWrapper;
import org.xcolab.service.members.domain.rolegroup.RoleGroupDao;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

@Service
public class RoleGroupService {

    private final RoleGroupDao roleGroupDao;

    @Autowired
    public RoleGroupService(RoleGroupDao roleGroupDao) {
        this.roleGroupDao = roleGroupDao;
    }

    public void addRoleToGroup(Long roleGroupId, Long roleId) {
        if(!this.roleGroupDao.groupHasRole(roleGroupId, roleId)){
            this.roleGroupDao.addRoleToGroup(roleGroupId, roleId);
        }
    }

    public void removeRoleFromGroup(Long roleGroupId, Long roleId) throws RoleGroupNotFoundException {

        if(!this.roleGroupDao.groupHasRole(roleGroupId, roleId)){
            this.roleGroupDao.addRoleToGroup(roleGroupId, roleId);
        } else {
            throw new RoleGroupNotFoundException("RoleGroup does not have role with id " + roleId );
        }
    }

    public List<RoleWrapper> getAllRolesInGroup(Long groupId) {
        return this.roleGroupDao.getAllRolesInGroup(groupId);
    }

}
