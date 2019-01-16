package org.xcolab.service.members.domain.role;

import org.xcolab.client.user.pojo.IRole;

import java.util.List;

public interface RoleDao {
    List<IRole> getUserRoles(Long userId);

    List<IRole> getUserRolesInContest(Long userId, Long contestId);

    void assignUserRole(Long userId, Long roleId);

    boolean memberHasRole(Long userId, Long roleId);

    boolean deleteUserRole(long userId, long roleId);
}
