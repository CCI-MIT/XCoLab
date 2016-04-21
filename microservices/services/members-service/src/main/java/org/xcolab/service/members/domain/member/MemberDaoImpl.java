package org.xcolab.service.members.domain.member;

import static org.jooq.impl.DSL.countDistinct;
import static org.jooq.impl.DSL.max;
import static org.jooq.impl.DSL.sum;
import static org.xcolab.model.Tables.MEMBER;
import static org.xcolab.model.Tables.POINTS;
import static org.xcolab.model.Tables.ROLES_CATEGORY;
import static org.xcolab.model.Tables.SOCIAL_ACTIVITY;
import static org.xcolab.model.Tables.USERS_ROLES;
import static org.xcolab.model.Tables.USER_;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Table;
import org.jooq.TableField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.records.MemberRecord;
import org.xcolab.service.members.exceptions.NotFoundException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<Member> listMembersSortByScreenName(int startRecord, int limitRecord, String filter,
                                                   boolean isAscOrder) {
        return listMembersSortByField(startRecord, limitRecord, filter, MEMBER.SCREEN_NAME, isAscOrder);
    }

    private List<Member> listMembersSortByField(int startRecord, int limitRecord, String filter,
                                               TableField<MemberRecord, ?> field, boolean isAscOrder) {
        if (filter != null && !filter.isEmpty()) {
            return this.dslContext.selectDistinct()
                    .from(MEMBER)
                    .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(MEMBER.SCREEN_NAME.contains(filter))
                    .or(MEMBER.FIRST_NAME.contains(filter))
                    .or(MEMBER.LAST_NAME.contains(filter))
                    .and(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%"))
                    .orderBy((isAscOrder ? (field.asc()) : (field.desc())))
                    .limit(startRecord, limitRecord).fetchInto(Member.class);

        } else {
            return this.dslContext.selectDistinct()
                    .from(USER_)
                    .join(USERS_ROLES).on(USER_.USER_ID.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%"))
                    .orderBy((isAscOrder ? (field.asc()) : (field.desc())))
                    .limit(startRecord, limitRecord).fetchInto(Member.class);
        }
    }

    @Override
    public List<Member> listMembersSortByMemberSince(int startRecord, int limitRecord, String filter,
                                                    boolean isAscOrder) {
        return listMembersSortByField(startRecord, limitRecord, filter, MEMBER.CREATE_DATE, isAscOrder);
    }

    @Override
    public Member getMember(long memberId) throws NotFoundException {
        final Record memberRecord = dslContext.select()
                .from(MEMBER)
                .where(MEMBER.ID_.eq(memberId))
                .fetchOne();
        if (memberRecord == null) {
            throw new NotFoundException("Member with id " + memberId + " does not exist");
        }
        return memberRecord.into(Member.class);
    }

    @Override
    public List<Member> listMembersSortByActivityCount(int startRecord, int limitRecord, String filter,
                                                      boolean isAscOrder) {
        Field<Object> activityCount = this.dslContext.selectCount()
                .from(SOCIAL_ACTIVITY)
                .where(SOCIAL_ACTIVITY.USER_ID.equal(USER_.USER_ID))
                .asField("activityCount");
        return this.dslContext
                .select(MEMBER.fields())
                .select(activityCount)
                .from(MEMBER)
                .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                .where(MEMBER.SCREEN_NAME.contains(filter))
                .or(MEMBER.FIRST_NAME.contains(filter))
                .or(MEMBER.LAST_NAME.contains(filter))
                .and(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%"))
                .orderBy((isAscOrder ? (activityCount.asc()) : (activityCount.desc())))
                .limit(startRecord, limitRecord).fetchInto(Member.class);
    }

    @Override
    public List<Member> listMembersSortByActivityCountFilteredByCategory(int startRecord, int limitRecord,
                                                                        String filter, boolean isAscOrder,
                                                                        String roleName) {
        Field<Object> activityCount =
                this.dslContext.selectCount()
                        .from(SOCIAL_ACTIVITY)
                        .where(SOCIAL_ACTIVITY.USER_ID.equal(MEMBER.ID_))
                        .asField("activityCount");
        return this.dslContext
                .select(MEMBER.fields())
                .select(activityCount)
                .from(MEMBER)
                .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                .where(MEMBER.SCREEN_NAME.contains(filter))
                .or(MEMBER.FIRST_NAME.contains(filter))
                .or(MEMBER.LAST_NAME.contains(filter))
                .and(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                .orderBy((isAscOrder ? (activityCount.asc()) : (activityCount.desc())))
                .limit(startRecord, limitRecord).fetchInto(Member.class);
    }

    @Override
    public List<Member> listMembersSortByRoleName(int startRecord, int limitRecord, String filter,
                                                 boolean isAscOrder) {

        Field<Long> userIdOriginalRoleSelect = USERS_ROLES.USER_ID.as("userIdOrdinalSelect");
        Field<Integer> roleOrdinalRoleSelect = max(ROLES_CATEGORY.ROLE_ORDINAL)
                .as("roleOrdinalSelect");
        Table<Record2<Long, Integer>> originalRoleSelect = this.dslContext
                .select(userIdOriginalRoleSelect, roleOrdinalRoleSelect)
                .from(USERS_ROLES)
                .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                .where(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%"))
                .groupBy(USERS_ROLES.USER_ID).asTable("originalRoleSelect");

        org.xcolab.model.tables.MemberTable member = MEMBER.as("member");
        return this.dslContext
                .selectDistinct(member.fields())
                .from(member)
                .join(originalRoleSelect).on(member.ID_.eq(userIdOriginalRoleSelect))
                .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ORDINAL.equal(roleOrdinalRoleSelect))
                .where(member.SCREEN_NAME.contains(filter))
                .or(member.FIRST_NAME.contains(filter))
                .or(member.LAST_NAME.contains(filter))
                .orderBy((isAscOrder ? (roleOrdinalRoleSelect.asc()) : (roleOrdinalRoleSelect.desc())))
                .limit(startRecord, limitRecord).fetchInto(Member.class);
    }

    @Override
    public List<Member> listMembersSortByRoleNameFilteredByCategory(int startRecord, int limitRecord,
                                                                   String filter, boolean isAscOrder,
                                                                   String roleName) {

        Field<Long> userIdOriginalRoleSelect = USERS_ROLES.USER_ID.as("userIdOrdinalSelect");
        Field<Integer> roleOrdinalRoleSelect = max(ROLES_CATEGORY.ROLE_ORDINAL)
                .as("roleOrdinalSelect");
        Table<Record2<Long, Integer>> originalRoleSelect = this.dslContext
                .select(userIdOriginalRoleSelect, roleOrdinalRoleSelect)
                .from(USERS_ROLES)
                .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                .where(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                .groupBy(USERS_ROLES.USER_ID).asTable("originalRoleSelect");

        org.xcolab.model.tables.MemberTable member = MEMBER.as("member");
        return this.dslContext
                .selectDistinct(member.fields())
                .from(member)
                .join(originalRoleSelect).on(member.ID_.eq(userIdOriginalRoleSelect))
                .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ORDINAL.equal(roleOrdinalRoleSelect))
                .where(member.SCREEN_NAME.contains(filter))
                .or(member.FIRST_NAME.contains(filter))
                .or(member.LAST_NAME.contains(filter))
                .orderBy((isAscOrder ? (roleOrdinalRoleSelect.asc()) : (roleOrdinalRoleSelect.desc())))
                .limit(startRecord, limitRecord).fetchInto(Member.class);
    }

    @Override
    public List<Member> listMembersSortByPoint(int startRecord, int limitRecord, String filter,
                                              boolean isAscOrder) {

        Field<Object> points =
                this.dslContext.select(sum(POINTS.MATERIALIZED_POINTS))
                        .from(POINTS)
                        .where(POINTS.USER_ID.equal(MEMBER.ID_))
                        .asField("points");

        return this.dslContext
                .selectDistinct(MEMBER.fields())
                .select(points)
                .from(MEMBER)
                .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                .where(MEMBER.SCREEN_NAME.contains(filter))
                .or(MEMBER.FIRST_NAME.contains(filter))
                .or(MEMBER.LAST_NAME.contains(filter))
                .and(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%"))
                .orderBy((isAscOrder ? (points.asc()) : (points.desc())))
                .limit(startRecord, limitRecord).fetchInto(Member.class);

    }

    @Override
    public List<Member> listMembersSortByPointFilteredByCategory(int startRecord, int limitRecord,
                                                                String filter, boolean isAscOrder,
                                                                String roleName) {
        Field<Object> points = this.dslContext
                .select(sum(POINTS.MATERIALIZED_POINTS))
                .from(POINTS)
                .where(POINTS.USER_ID.equal(MEMBER.ID_))
                .asField("points");

        return this.dslContext
                .selectDistinct(MEMBER.fields())
                .select(points)
                .from(MEMBER)
                .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                .where(MEMBER.SCREEN_NAME.contains(filter))
                .or(MEMBER.FIRST_NAME.contains(filter))
                .or(MEMBER.LAST_NAME.contains(filter))
                .and(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                .orderBy((isAscOrder ? (points.asc()) : (points.desc())))
                .limit(startRecord, limitRecord).fetchInto(Member.class);
    }

    @Override
    public Integer countMembers(String filter) {
        if (filter != null && !filter.isEmpty()) {
            return this.dslContext.select(countDistinct(MEMBER.ID_))
                    .from(MEMBER)
                    .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(MEMBER.SCREEN_NAME.contains(filter))
                    .or(MEMBER.FIRST_NAME.contains(filter))
                    .or(MEMBER.LAST_NAME.contains(filter))
                    .and(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%")).fetchOne(0, Integer.class);
        } else {
            return this.dslContext.select(countDistinct(MEMBER.ID_))
                    .from(MEMBER)
                    .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%")).fetchOne(0, Integer.class);
        }
    }

    @Override
    public Integer countMembersFilteredByCategory(String filter, String roleName) {
        if (filter != null && !filter.isEmpty()) {
            return this.dslContext.select(countDistinct(MEMBER.ID_))
                    .from(MEMBER)
                    .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(MEMBER.SCREEN_NAME.contains(filter))
                    .or(MEMBER.FIRST_NAME.contains(filter))
                    .or(MEMBER.LAST_NAME.contains(filter))
                    .and(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                    .fetchOne(0, Integer.class);

        } else {
            return this.dslContext.select(countDistinct(MEMBER.ID_))
                    .from(MEMBER)
                    .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                    .fetchOne(0, Integer.class);
        }
    }

    @Override
    public List<Member> listMembersSortByScreenNameFilteredByCategory(int startRecord,
                                                                     int limitRecord, String filter,
                                                                     boolean isAscOrder,
                                                                     String roleName) {
        return listMembersSortByFieldFilteredByCategory(startRecord, limitRecord, filter,
                MEMBER.SCREEN_NAME, isAscOrder, roleName);
    }

    private List<Member> listMembersSortByFieldFilteredByCategory(int startRecord, int limitRecord,
                                                                 String filter,
                                                                 TableField<MemberRecord, ?> field,
                                                                 boolean isAscOrder, String roleName) {
        if (filter != null && !filter.isEmpty()) {
            return this.dslContext.selectDistinct()
                    .from(MEMBER)
                    .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(MEMBER.SCREEN_NAME.contains(filter))
                    .or(MEMBER.FIRST_NAME.contains(filter))
                    .or(MEMBER.LAST_NAME.contains(filter))
                    .and(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                    .orderBy((isAscOrder ? (field.asc()) : (field.desc())))
                    .limit(startRecord, limitRecord).fetchInto(Member.class);
        } else {
            return this.dslContext.selectDistinct()
                    .from(MEMBER)
                    .join(USERS_ROLES).on(MEMBER.ID_.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                    .orderBy((isAscOrder ? (field.asc()) : (field.desc())))
                    .limit(startRecord, limitRecord).fetchInto(Member.class);
        }
    }

    @Override
    public Member findOneByScreenName(String screenName) {
        return dslContext.select()
                .from(MEMBER)
                .where(MEMBER.SCREEN_NAME.eq(screenName))
                .fetchOne().into(Member.class);
    }

    @Override
    public Member findOneByEmail(String email) {
        return dslContext.select()
                .from(MEMBER)
                .where(MEMBER.EMAIL_ADDRESS.eq(email))
                .fetchOne().into(Member.class);
    }

    @Override
    public void updateMember(Member member) {

        this.dslContext.update(MEMBER)
                .set(MEMBER.CREATE_DATE, member.getCreateDate())
                .set(MEMBER.MODIFIED_DATE, member.getModifiedDate())
                .set(MEMBER.HASHED_PASSWORD, member.getHashedPassword())
                .set(MEMBER.PASSWORD_MODIFIED_DATE, member.getPasswordModifiedDate())
                .set(MEMBER.SCREEN_NAME, member.getScreenName())
                .set(MEMBER.EMAIL_ADDRESS, member.getEmailAddress())
                .set(MEMBER.OPEN_ID, member.getOpenId())
                .set(MEMBER.FIRST_NAME, member.getFirstName())
                .set(MEMBER.LAST_NAME, member.getLastName())
                .set(MEMBER.LOGIN_DATE, member.getLoginDate())
                .set(MEMBER.LOGIN_IP, member.getLoginIP())
                .set(MEMBER.FACEBOOK_ID, member.getFacebookId())
                .where(MEMBER.ID_.equal(member.getId_()))
                .execute();
    }

    @Override
    public void createMember(String screenName, String hashedPassword, String email, String firstName, String lastName,
            String shortBio, String country, Long facebookId, String openId, Long liferayUserId) {
        this.dslContext.insertInto(MEMBER)
                .set(MEMBER.ID_, liferayUserId)
                .set(MEMBER.SCREEN_NAME, screenName)
                .set(MEMBER.HASHED_PASSWORD, hashedPassword)
                .set(MEMBER.EMAIL_ADDRESS, email)
                .set(MEMBER.FIRST_NAME, firstName)
                .set(MEMBER.LAST_NAME, lastName)
                .set(MEMBER.FACEBOOK_ID, facebookId)
                .set(MEMBER.OPEN_ID, openId)
                .set(MEMBER.SHORT_BIO, shortBio)
                .set(MEMBER.COUNTRY, country)
                .set(MEMBER.CREATE_DATE, new Timestamp(Instant.now().toEpochMilli()))
                .set(MEMBER.MODIFIED_DATE, new Timestamp(Instant.now().toEpochMilli()))
                .execute();
    }

    @Override
    public boolean isScreenNameTaken(String screenName) {
        return dslContext.selectCount()
                .from(MEMBER)
                .where(MEMBER.SCREEN_NAME.eq(screenName))
                .fetchOne(0, Integer.class) > 0;
    }

    @Override
    public boolean isEmailUsed(String email) {
        return dslContext.selectCount()
                .from(MEMBER)
                .where(MEMBER.EMAIL_ADDRESS.eq(email))
                .fetchOne(0, Integer.class) > 0;
    }

    @Override
    public List<Member> listMembersSortByMemberSinceFilteredByCategory(int startRecord, int limitRecord,
                                                                      String filter, boolean isAscOrder, String roleName) {
        return listMembersSortByFieldFilteredByCategory(startRecord, limitRecord, filter,
                MEMBER.CREATE_DATE, isAscOrder, roleName);
    }

    @Override
    public Integer getMemberMaterializedPoints(Long memberId) {
        return this.dslContext.select(sum(POINTS.MATERIALIZED_POINTS))
                .from(POINTS).where(POINTS.USER_ID.equal(memberId)).fetchOne(0, Integer.class);
    }

    @Override
    public Integer getMemberActivityCount(Long memberId) {
        return this.dslContext.selectCount()
                .from(SOCIAL_ACTIVITY).where(SOCIAL_ACTIVITY.USER_ID.equal(memberId))
                .fetchOne(0, Integer.class);
    }
}