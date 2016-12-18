package org.xcolab.service.members.domain.role;

import org.xcolab.model.tables.pojos.Role_;

import java.util.List;

public interface RoleDao {
    List<Role_> getMemberRoles(Long memberId);

    List<Role_> getMemberRolesInContest(Long memberId, Long contestId);

    void assignMemberRole(Long memberId, Long roleId);

    boolean memberHasRole(Long memberId, Long roleId);
}
