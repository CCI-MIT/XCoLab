package org.xcolab.service.members.domain.role;

import org.xcolab.model.tables.pojos.Role_;

import java.util.List;

public interface RoleDao {
    List<Role_> getMemberRoles(Long userId);

    List<Role_> getMemberRolesInContest(Long userId, Long contestId);

    void assignMemberRole(Long userId, Long roleId);

    boolean memberHasRole(Long userId, Long roleId);

    boolean deleteMemberRole(long userId, long roleId);
}
