package org.xcolab.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.domain.role.RoleDao;
import org.xcolab.model.tables.pojos.Role_;

import java.util.List;

@Service
public class RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleService(RoleDao roleDao){

        this.roleDao = roleDao;
    }

    public List<Role_> getMemberRoles(Long memberId){
        return this.roleDao.getMemberRoles(memberId);
    }
}
