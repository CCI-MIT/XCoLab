package org.xcolab.service.members.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.user.pojo.wrapper.RoleWrapper;
import org.xcolab.service.members.domain.role.RoleDao;

import java.util.List;

@Service
public class RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public List<RoleWrapper> getUserRoles(Long userId) {
        return this.roleDao.getUserRoles(userId);
    }

    public List<RoleWrapper> getUserRolesInContest(Long userId, Long contestId) {
        return this.roleDao.getUserRolesInContest(userId, contestId);
    }

    public boolean assignUserRole(long userId, long roleId) {
        if(!memberHasRole(userId,roleId)) {
            this.roleDao.assignUserRole(userId, roleId);
        }
        return true;
    }

    public boolean deleteUserRole(long userId, long roleId) {
        this.roleDao.deleteUserRole(userId, roleId);
        return true;
    }

    public boolean memberHasRole(Long userId, Long roleId){
        return this.roleDao.memberHasRole(userId,roleId);
    }

    public boolean isAdmin(long userId) {
        return roleDao.memberHasRole(userId, 10118L);
    }
}
