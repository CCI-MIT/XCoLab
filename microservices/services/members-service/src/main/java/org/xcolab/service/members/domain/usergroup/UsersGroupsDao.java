package org.xcolab.service.members.domain.usergroup;

import org.xcolab.model.tables.pojos.UsersGroups;

import java.util.List;

public interface UsersGroupsDao {
    List<UsersGroups> findByGiven(Long userId, Long groupId);
    UsersGroups create(UsersGroups usersGroups);
}
