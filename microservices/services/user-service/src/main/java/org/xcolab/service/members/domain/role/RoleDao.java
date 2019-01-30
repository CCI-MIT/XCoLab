package org.xcolab.service.members.domain.role;

import org.xcolab.client.user.pojo.wrapper.RoleWrapper;

import java.util.List;

public interface RoleDao {
    List<RoleWrapper> getUserRoles(Long userId);

    List<RoleWrapper> getUserRolesInContest(Long userId, Long contestId);

    void assignUserRole(Long userId, Long roleId);

    boolean memberHasRole(Long userId, Long roleId);

    boolean deleteUserRole(long userId, long roleId);
}
