package org.xcolab.service.members.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.service.members.domain.role.RoleDao;
import org.xcolab.model.tables.pojos.Role_;

import java.util.List;

@Service
public class RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public List<Role_> getMemberRoles(Long userId) {
        return this.roleDao.getMemberRoles(userId);
    }

    public List<Role_> getMemberRolesInContest(Long userId, Long contestId) {
        return this.roleDao.getMemberRolesInContest(userId, contestId);
    }

    public boolean assignMemberRole(long userId, long roleId) {
        if(!memberHasRole(userId,roleId)) {
            this.roleDao.assignMemberRole(userId, roleId);
        }
        return true;
    }

    public boolean deleteMemberRole(long userId, long roleId) {
        this.roleDao.deleteMemberRole(userId, roleId);
        return true;
    }

    public boolean memberHasRole(Long userId, Long roleId){
        return this.roleDao.memberHasRole(userId,roleId);
    }

    public boolean isAdmin(long userId) {
        return roleDao.memberHasRole(userId, 10118L);
    }
}
