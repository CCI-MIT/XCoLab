package org.xcolab.service.members.domain.usergroup;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Users_Groups;

import java.util.List;

import static org.xcolab.model.Tables.USERS_GROUPS;

@Repository
public class UsersGroupsDaoImpl implements UsersGroupsDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public Users_Groups create(Users_Groups usersGroups) {

        this.dslContext.insertInto(USERS_GROUPS)
                .set(USERS_GROUPS.USER_ID, usersGroups.getUserId())
                .set(USERS_GROUPS.GROUP_ID, usersGroups.getGroupId())
                .execute();

        return usersGroups;

    }

    @Override
    public boolean exists(long userId, long groupId) {
        return dslContext.fetchExists(DSL.select()
                .from(USERS_GROUPS)
                .where(USERS_GROUPS.USER_ID.eq(userId)
                        .and(USERS_GROUPS.GROUP_ID.eq(groupId))));
    }

    @Override
    public List<Users_Groups> findByGiven(Long userId, Long groupId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(USERS_GROUPS).getQuery();

        if (userId != null) {
            query.addConditions(USERS_GROUPS.USER_ID.eq(userId));
        }
        if (groupId != null) {
            query.addConditions(USERS_GROUPS.GROUP_ID.eq(groupId));
        }
        return query.fetchInto(Users_Groups.class);
    }

    @Override
    public int delete(Long userId, Long groupId) {

        return this.dslContext.deleteFrom(USERS_GROUPS)
                .where(USERS_GROUPS.USER_ID.eq(userId))
                .and(USERS_GROUPS.GROUP_ID.eq(groupId))
                .execute();
    }

}
