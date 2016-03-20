package org.xcolab.domain;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Table;
import org.jooq.TableField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.User_;
import org.xcolab.model.tables.records.User_Record;

import java.util.List;

import static org.jooq.impl.DSL.max;
import static org.jooq.impl.DSL.sum;
import static org.xcolab.model.Tables.POINTS;
import static org.xcolab.model.Tables.ROLES_CATEGORY;
import static org.xcolab.model.Tables.SOCIAL_ACTIVITY;
import static org.xcolab.model.Tables.USERS_ROLES;
import static org.xcolab.model.Tables.USER_;

@Repository
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<User_> listAllMembersSortByScreenName(int startRecord, int limitRecord, String filter,
            boolean isAscOrder) {
        return listAllMembersSortByField(startRecord, limitRecord, filter, USER_.SCREEN_NAME, isAscOrder);
    }

    @Override
    public List<User_> listAllMembersSortByMemberSince(int startRecord, int limitRecord, String filter,
            boolean isAscOrder) {
        return listAllMembersSortByField(startRecord, limitRecord, filter, USER_.CREATE_DATE, isAscOrder);
    }

    private List<User_> listAllMembersSortByField(int startRecord, int limitRecord, String filter,
            TableField<User_Record, ?> field, boolean isAscOrder) {
        if (!filter.isEmpty()) {
            return this.dslContext.select()
                    .from(USER_)
                    .join(USERS_ROLES).on(USER_.USER_ID.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(USER_.SCREEN_NAME.contains(filter))
                    .or(USER_.FIRST_NAME.contains(filter))
                    .or(USER_.LAST_NAME.contains(filter))
                    .and(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%"))
                    .orderBy((isAscOrder ? (field.asc()) : (field.desc())))
                    .limit(startRecord, limitRecord).fetchInto(User_.class);

        } else {
            return this.dslContext.select()
                    .from(USER_)
                    .join(USERS_ROLES).on(USER_.USER_ID.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%"))
                    .orderBy((isAscOrder ? (field.asc()) : (field.desc())))
                    .limit(startRecord, limitRecord).fetchInto(User_.class);
        }
    }

    @Override
    public List<User_> listAllMembersSortByActivityCount(int startRecord, int limitRecord, String filter,
            boolean isAscOrder) {

        Field<Object> activityCount =
                this.dslContext.selectCount()
                        .from(SOCIAL_ACTIVITY)
                        .where(SOCIAL_ACTIVITY.USER_ID.equal(USER_.USER_ID))
                        .asField("activityCount");

        return this.dslContext
                .select(USER_.fields())
                .select(activityCount)
                .from(USER_)
                .join(USERS_ROLES).on(USER_.USER_ID.equal(USERS_ROLES.USER_ID))
                .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                .where(USER_.SCREEN_NAME.contains(filter))
                .or(USER_.FIRST_NAME.contains(filter))
                .or(USER_.LAST_NAME.contains(filter))
                .and(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%"))
                .orderBy((isAscOrder ? (activityCount.asc()) : (activityCount.desc())))
                .limit(startRecord, limitRecord).fetchInto(User_.class);

    }

    @Override
    public List<User_> listAllMembersSortByRoleName(int startRecord, int limitRecord, String filter,
            boolean isAscOrder) {

        Field<Long> userIdOriginalRoleSelect = USERS_ROLES.USER_ID.as("userIdOrdinalSelect");
        Field<Integer> roleOrdinalRoleSelect = max(ROLES_CATEGORY.ROLE_ORDINAL).as("roleOrdinalSelect");
        Table<Record2<Long, Integer>> originalRoleSelect =
                this.dslContext.select(userIdOriginalRoleSelect, roleOrdinalRoleSelect)
                        .from(USERS_ROLES)
                        .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                        .where(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%"))
                        .groupBy(USERS_ROLES.USER_ID).asTable("originalRoleSelect");

        org.xcolab.model.tables.User_ user = USER_.as("user");
        return this.dslContext.
                select(user.fields())
                .from(user)
                .join(originalRoleSelect).on(user.USER_ID.eq(userIdOriginalRoleSelect))
                .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ORDINAL.equal(roleOrdinalRoleSelect))
                .where(user.SCREEN_NAME.contains(filter))
                .or(user.FIRST_NAME.contains(filter))
                .or(user.LAST_NAME.contains(filter))
                .orderBy((isAscOrder ? (roleOrdinalRoleSelect.asc()) : (roleOrdinalRoleSelect.desc())))
                .limit(startRecord, limitRecord).fetchInto(User_.class);

    }

    @Override
    public List<User_> listAllMembersSortByPoint(int startRecord, int limitRecord, String filter,
            boolean isAscOrder) {

        Field<Object> points =
                this.dslContext.select(sum(POINTS.MATERIALIZED_POINTS))
                        .from(POINTS)
                        .where(POINTS.USER_ID.equal(USER_.USER_ID))
                        .asField("points");

        return this.dslContext
                .select(USER_.fields())
                .select(points)
                .from(USER_)
                .join(USERS_ROLES).on(USER_.USER_ID.equal(USERS_ROLES.USER_ID))
                .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                .where(USER_.SCREEN_NAME.contains(filter))
                .or(USER_.FIRST_NAME.contains(filter))
                .or(USER_.LAST_NAME.contains(filter))
                .and(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%"))
                .orderBy((isAscOrder ? (points.asc()) : (points.desc())))
                .limit(startRecord, limitRecord).fetchInto(User_.class);

    }
}
