package org.xcolab.service.members.domain.rolegroup;

import org.xcolab.model.tables.pojos.RoleGroup;
import org.xcolab.model.tables.pojos.Role_;

import java.util.List;

public interface RoleGroupDao {

    RoleGroup create(RoleGroup roleGroup);

    void addRoleToGroup(Long roleGroupId, Long roleId);

    void removeRoleFromGroup(Long roleGroupId, Long roleId);

    List<Role_> getAllRolesInGroup(Long groupId);

    boolean groupHasRole(Long roleGroupId, Long groupId);
}
