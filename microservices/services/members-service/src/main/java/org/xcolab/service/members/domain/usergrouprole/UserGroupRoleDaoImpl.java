package org.xcolab.service.members.domain.usergrouprole;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.UserGroupRole;

import java.util.List;

import static org.xcolab.model.Tables.USER_GROUP_ROLE;

@Repository
public class UserGroupRoleDaoImpl implements UserGroupRoleDao {

    @Autowired
    private DSLContext dslContext;

    public UserGroupRole create(UserGroupRole userGroupRole) {

        this.dslContext.insertInto(USER_GROUP_ROLE)
                .set(USER_GROUP_ROLE.USER_ID, userGroupRole.getUserId())
                .set(USER_GROUP_ROLE.GROUP_ID, userGroupRole.getGroupId())
                .set(USER_GROUP_ROLE.ROLE_ID, userGroupRole.getRoleId())
                .execute();

        return userGroupRole;

    }


    public List<UserGroupRole> findByGiven(Long userId, Long groupId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(USER_GROUP_ROLE).getQuery();

        if (userId != null) {
            query.addConditions(USER_GROUP_ROLE.USER_ID.eq(userId));
        }
        if (groupId != null) {
            query.addConditions(USER_GROUP_ROLE.GROUP_ID.eq(groupId));
        }
        return query.fetchInto(UserGroupRole.class);
    }
}
