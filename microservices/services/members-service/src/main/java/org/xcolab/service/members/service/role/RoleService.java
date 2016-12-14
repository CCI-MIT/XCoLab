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

    public List<Role_> getMemberRoles(Long memberId) {
        return this.roleDao.getMemberRoles(memberId);
    }

    public List<Role_> getMemberRolesInContest(Long memberId, Long contestId) {
        return this.roleDao.getMemberRolesInContest(memberId, contestId);
    }

    public boolean assignMemberRole(Long memberId, Long roleId) {
        if(!memberHasRole(memberId,roleId)) {
            this.roleDao.assignMemberRole(memberId, roleId);
        }
        return true;
    }

    public boolean memberHasRole(Long memberId, Long roleId){
        return this.roleDao.memberHasRole(memberId,roleId);
    }
}
