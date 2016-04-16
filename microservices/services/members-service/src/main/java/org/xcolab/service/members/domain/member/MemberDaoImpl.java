package org.xcolab.service.members.domain.member;

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

import static org.jooq.impl.DSL.countDistinct;
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
    public List<User_> listMembersSortByScreenName(int startRecord, int limitRecord, String filter,
                                                   boolean isAscOrder) {
        return listMembersSortByField(startRecord, limitRecord, filter, USER_.SCREEN_NAME, isAscOrder);
    }

    private List<User_> listMembersSortByField(int startRecord, int limitRecord, String filter,
                                               TableField<User_Record, ?> field, boolean isAscOrder) {
        if (filter != null && !filter.isEmpty()) {
            return this.dslContext.selectDistinct()
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
            return this.dslContext.selectDistinct()
                    .from(USER_)
                    .join(USERS_ROLES).on(USER_.USER_ID.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%"))
                    .orderBy((isAscOrder ? (field.asc()) : (field.desc())))
                    .limit(startRecord, limitRecord).fetchInto(User_.class);
        }
    }

    @Override
    public List<User_> listMembersSortByMemberSince(int startRecord, int limitRecord, String filter,
                                                    boolean isAscOrder) {
        return listMembersSortByField(startRecord, limitRecord, filter, USER_.CREATE_DATE, isAscOrder);
    }

    @Override
    public User_ getMember(long memberId) {
        return dslContext.select()
                .from(USER_)
                .where(USER_.USER_ID.eq(memberId))
                .fetchOne().into(User_.class);
    }

    @Override
    public List<User_> listMembersSortByActivityCount(int startRecord, int limitRecord, String filter,
                                                      boolean isAscOrder) {
        Field<Object> activityCount = this.dslContext.selectCount()
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
    public List<User_> listMembersSortByActivityCountFilteredByCategory(int startRecord, int limitRecord,
                                                                        String filter, boolean isAscOrder,
                                                                        String roleName) {
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
                .and(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                .orderBy((isAscOrder ? (activityCount.asc()) : (activityCount.desc())))
                .limit(startRecord, limitRecord).fetchInto(User_.class);
    }

    @Override
    public List<User_> listMembersSortByRoleName(int startRecord, int limitRecord, String filter,
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

        org.xcolab.model.tables.User_ user = USER_.as("user");
        return this.dslContext
                .selectDistinct(user.fields())
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
    public List<User_> listMembersSortByRoleNameFilteredByCategory(int startRecord, int limitRecord,
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

        org.xcolab.model.tables.User_ user = USER_.as("user");
        return this.dslContext
                .selectDistinct(user.fields())
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
    public List<User_> listMembersSortByPoint(int startRecord, int limitRecord, String filter,
                                              boolean isAscOrder) {

        Field<Object> points =
                this.dslContext.select(sum(POINTS.MATERIALIZED_POINTS))
                        .from(POINTS)
                        .where(POINTS.USER_ID.equal(USER_.USER_ID))
                        .asField("points");

        return this.dslContext
                .selectDistinct(USER_.fields())
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

    @Override
    public List<User_> listMembersSortByPointFilteredByCategory(int startRecord, int limitRecord,
                                                                String filter, boolean isAscOrder,
                                                                String roleName) {
        Field<Object> points = this.dslContext
                .select(sum(POINTS.MATERIALIZED_POINTS))
                .from(POINTS)
                .where(POINTS.USER_ID.equal(USER_.USER_ID))
                .asField("points");

        return this.dslContext
                .selectDistinct(USER_.fields())
                .select(points)
                .from(USER_)
                .join(USERS_ROLES).on(USER_.USER_ID.equal(USERS_ROLES.USER_ID))
                .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                .where(USER_.SCREEN_NAME.contains(filter))
                .or(USER_.FIRST_NAME.contains(filter))
                .or(USER_.LAST_NAME.contains(filter))
                .and(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                .orderBy((isAscOrder ? (points.asc()) : (points.desc())))
                .limit(startRecord, limitRecord).fetchInto(User_.class);
    }

    @Override
    public Integer countMembers(String filter) {
        if (filter != null && !filter.isEmpty()) {
            return this.dslContext.select(countDistinct(USER_.USER_ID))
                    .from(USER_)
                    .join(USERS_ROLES).on(USER_.USER_ID.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(USER_.SCREEN_NAME.contains(filter))
                    .or(USER_.FIRST_NAME.contains(filter))
                    .or(USER_.LAST_NAME.contains(filter))
                    .and(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%")).fetchOne(0, Integer.class);
        } else {
            return this.dslContext.select(countDistinct(USER_.USER_ID))
                    .from(USER_)
                    .join(USERS_ROLES).on(USER_.USER_ID.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(ROLES_CATEGORY.CATEGORY_NAME.notLike("%Staff%")).fetchOne(0, Integer.class);
        }
    }

    @Override
    public Integer countMembersFilteredByCategory(String filter, String roleName) {
        if (filter != null && !filter.isEmpty()) {
            return this.dslContext.select(countDistinct(USER_.USER_ID))
                    .from(USER_)
                    .join(USERS_ROLES).on(USER_.USER_ID.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(USER_.SCREEN_NAME.contains(filter))
                    .or(USER_.FIRST_NAME.contains(filter))
                    .or(USER_.LAST_NAME.contains(filter))
                    .and(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                    .fetchOne(0, Integer.class);

        } else {
            return this.dslContext.select(countDistinct(USER_.USER_ID))
                    .from(USER_)
                    .join(USERS_ROLES).on(USER_.USER_ID.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                    .fetchOne(0, Integer.class);
        }
    }

    @Override
    public List<User_> listMembersSortByScreenNameFilteredByCategory(int startRecord,
                                                                     int limitRecord, String filter,
                                                                     boolean isAscOrder,
                                                                     String roleName) {
        return listMembersSortByFieldFilteredByCategory(startRecord, limitRecord, filter,
                USER_.SCREEN_NAME, isAscOrder, roleName);
    }

    private List<User_> listMembersSortByFieldFilteredByCategory(int startRecord, int limitRecord,
                                                                 String filter,
                                                                 TableField<User_Record, ?> field,
                                                                 boolean isAscOrder, String roleName) {
        if (filter != null && !filter.isEmpty()) {
            return this.dslContext.selectDistinct()
                    .from(USER_)
                    .join(USERS_ROLES).on(USER_.USER_ID.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(USER_.SCREEN_NAME.contains(filter))
                    .or(USER_.FIRST_NAME.contains(filter))
                    .or(USER_.LAST_NAME.contains(filter))
                    .and(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                    .orderBy((isAscOrder ? (field.asc()) : (field.desc())))
                    .limit(startRecord, limitRecord).fetchInto(User_.class);
        } else {
            return this.dslContext.selectDistinct()
                    .from(USER_)
                    .join(USERS_ROLES).on(USER_.USER_ID.equal(USERS_ROLES.USER_ID))
                    .join(ROLES_CATEGORY).on(ROLES_CATEGORY.ROLE_ID.equal(USERS_ROLES.ROLE_ID))
                    .where(ROLES_CATEGORY.CATEGORY_NAME.like("%" + roleName + "%"))
                    .orderBy((isAscOrder ? (field.asc()) : (field.desc())))
                    .limit(startRecord, limitRecord).fetchInto(User_.class);
        }
    }

    public User_ getMember(Long userId) {
        return this.dslContext.selectDistinct()
                .from(USER_)
                .where(USER_.USER_ID.equal(userId)).fetchAny().into(User_.class);

    }

    public void updateMember(User_ user) {

        this.dslContext.update(USER_)
                .set(USER_.UUID_, user.getUuid_())
                //.set(USER_.USER_ID ,user.getUserId())
                .set(USER_.COMPANY_ID, user.getCompanyId())
                .set(USER_.CREATE_DATE, user.getCreateDate())
                .set(USER_.MODIFIED_DATE, user.getModifiedDate())
                .set(USER_.DEFAULT_USER, user.getDefaultUser())
                .set(USER_.CONTACT_ID, user.getContactId())
                .set(USER_.PASSWORD_, user.getPassword_())
                .set(USER_.PASSWORD_ENCRYPTED, user.getPasswordEncrypted())
                .set(USER_.PASSWORD_RESET, user.getPasswordReset())
                .set(USER_.PASSWORD_MODIFIED_DATE, user.getPasswordModifiedDate())
                .set(USER_.REMINDER_QUERY_QUESTION, user.getReminderQueryQuestion())
                .set(USER_.REMINDER_QUERY_ANSWER, user.getReminderQueryAnswer())
                .set(USER_.GRACE_LOGIN_COUNT, user.getGraceLoginCount())
                .set(USER_.SCREEN_NAME, user.getScreenName())
                .set(USER_.EMAIL_ADDRESS, user.getEmailAddress())
                .set(USER_.OPEN_ID, user.getOpenId())
                .set(USER_.PORTRAIT_ID, user.getPortraitId())
                .set(USER_.LANGUAGE_ID, user.getLanguageId())
                .set(USER_.TIME_ZONE_ID, user.getTimeZoneId())
                .set(USER_.GREETING, user.getGreeting())
                .set(USER_.COMMENTS, user.getComments())
                .set(USER_.FIRST_NAME, user.getFirstName())
                .set(USER_.MIDDLE_NAME, user.getMiddleName())
                .set(USER_.LAST_NAME, user.getLastName())
                .set(USER_.JOB_TITLE, user.getJobTitle())
                .set(USER_.LOGIN_DATE, user.getLoginDate())
                .set(USER_.LOGIN_IP, user.getLoginIP())
                .set(USER_.LAST_LOGIN_DATE, user.getLastLoginDate())
                .set(USER_.LAST_LOGIN_IP, user.getLastLoginIP())
                .set(USER_.LAST_FAILED_LOGIN_DATE, user.getLastFailedLoginDate())
                .set(USER_.FAILED_LOGIN_ATTEMPTS, user.getFailedLoginAttempts())
                .set(USER_.LOCKOUT, user.getLockout())
                .set(USER_.LOCKOUT_DATE, user.getLockoutDate())
                .set(USER_.AGREED_TO_TERMS_OF_USE, user.getAgreedToTermsOfUse())
                .set(USER_.SOCIAL_CONTRIBUTION_EQUITY, user.getSocialContributionEquity())
                .set(USER_.SOCIAL_PARTICIPATION_EQUITY, user.getSocialParticipationEquity())
                .set(USER_.SOCIAL_PERSONAL_EQUITY, user.getSocialPersonalEquity())
                .set(USER_.FACEBOOK_ID, user.getFacebookId())
                .set(USER_.DIGEST, user.getDigest())
                .set(USER_.EMAIL_ADDRESS_VERIFIED, user.getEmailAddressVerified())
                .set(USER_.STATUS, user.getStatus())
                .set(USER_.LDAP_SERVER_ID, user.getLdapServerId())
                .where(USER_.USER_ID.equal(user.getUserId()));
    }

    @Override
    public boolean isScreenNameTaken(String screenName) {
        return dslContext.selectCount()
                .from(USER_)
                .where(USER_.SCREEN_NAME.eq(screenName))
                .fetchOne(0, Integer.class) > 0;
    }

    @Override
    public boolean isEmailUsed(String email) {
        return dslContext.selectCount()
                .from(USER_)
                .where(USER_.EMAIL_ADDRESS.eq(email))
                .fetchOne(0, Integer.class) > 0;
    }

    @Override
    public List<User_> listMembersSortByMemberSinceFilteredByCategory(int startRecord, int limitRecord,
                                                                      String filter, boolean isAscOrder, String roleName) {
        return listMembersSortByFieldFilteredByCategory(startRecord, limitRecord, filter,
                USER_.CREATE_DATE, isAscOrder, roleName);
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