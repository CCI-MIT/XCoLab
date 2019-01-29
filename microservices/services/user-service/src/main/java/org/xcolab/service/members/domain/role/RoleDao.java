package org.xcolab.service.members.domain.role;

import org.xcolab.client.user.pojo.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getUserRoles(Long userId);

    List<Role> getUserRolesInContest(Long userId, Long contestId);

    void assignUserRole(Long userId, Long roleId);

    boolean memberHasRole(Long userId, Long roleId);

    boolean deleteUserRole(long userId, long roleId);
}
