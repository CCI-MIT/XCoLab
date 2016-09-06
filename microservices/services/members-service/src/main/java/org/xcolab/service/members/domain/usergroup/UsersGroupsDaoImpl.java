package org.xcolab.service.members.domain.usergroup;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.UsersGroups;
import org.xcolab.model.tables.records.UsersGroupsRecord;
import org.xcolab.service.members.exceptions.NotFoundException;

import java.util.List;

import static org.xcolab.model.Tables.USERS_GROUPS;

@Repository
public class UsersGroupsDaoImpl implements UsersGroupsDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public UsersGroups create(UsersGroups usersGroups) {

        this.dslContext.insertInto(USERS_GROUPS)
                .set(USERS_GROUPS.USER_ID, usersGroups.getUserId())
                .set(USERS_GROUPS.GROUP_ID, usersGroups.getGroupId())
                .execute();

        return usersGroups;

    }

    @Override
    public List<UsersGroups> findByGiven(Long userId, Long groupId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(USERS_GROUPS).getQuery();

        if (userId != null) {
            query.addConditions(USERS_GROUPS.USER_ID.eq(userId));
        }
        if (groupId != null) {
            query.addConditions(USERS_GROUPS.GROUP_ID.eq(groupId));
        }
        return query.fetchInto(UsersGroups.class);
    }


}
