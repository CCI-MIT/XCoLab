package org.xcolab.service.members.domain.usergrouprole;

import org.xcolab.model.tables.pojos.UserGroupRole;

import java.util.List;

public interface UserGroupRoleDao {
    List<UserGroupRole> findByGiven(Long userId, Long groupId);
    UserGroupRole create(UserGroupRole userGroupRole);
}
