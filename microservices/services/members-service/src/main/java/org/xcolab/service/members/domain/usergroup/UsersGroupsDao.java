package org.xcolab.service.members.domain.usergroup;


import org.xcolab.model.tables.pojos.Users_Groups;

import java.util.List;

public interface UsersGroupsDao {
    List<Users_Groups> findByGiven(Long userId, Long groupId);
    Users_Groups create(Users_Groups usersGroups);
    int delete(Long userId, Long groupId);
}
