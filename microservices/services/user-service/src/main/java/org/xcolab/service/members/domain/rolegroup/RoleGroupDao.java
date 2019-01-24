package org.xcolab.service.members.domain.rolegroup;

import org.xcolab.client.user.pojo.IRole;
import org.xcolab.client.user.pojo.IRoleGroup;

import java.util.List;

public interface RoleGroupDao {

    IRoleGroup create(IRoleGroup roleGroup);

    void addRoleToGroup(Long roleGroupId, Long roleId);

    void removeRoleFromGroup(Long roleGroupId, Long roleId);

    List<IRole> getAllRolesInGroup(Long groupId);

    boolean groupHasRole(Long roleGroupId, Long groupId);
}
